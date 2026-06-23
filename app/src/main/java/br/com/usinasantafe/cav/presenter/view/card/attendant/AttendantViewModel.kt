package br.com.usinasantafe.cav.presenter.view.card.attendant

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetRegAttendant
import br.com.usinasantafe.cav.domain.usecases.common.HasRegColab
import br.com.usinasantafe.cav.domain.usecases.card.SetRegAttendant
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableColab
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.theme.addTextField
import br.com.usinasantafe.cav.presenter.theme.clearTextField
import br.com.usinasantafe.cav.utils.UiStateWithStatusUpdate
import br.com.usinasantafe.cav.utils.UiStatusStateUpdate
import br.com.usinasantafe.cav.utils.executeUpdateSteps
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureUpdate
import br.com.usinasantafe.cav.utils.onSuccessUpdateCheckAccess
import br.com.usinasantafe.cav.utils.sizeUpdate
import br.com.usinasantafe.cav.utils.withFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AttendantStateUpdate(
    val option: Option = Option.INSERT,
    val regColab: String = "",
    override val status: UiStatusStateUpdate = UiStatusStateUpdate()
) : UiStateWithStatusUpdate<AttendantStateUpdate> {

    override fun copyWithStatus(status: UiStatusStateUpdate): AttendantStateUpdate =
        copy(status = status)

}

@HiltViewModel
class AttendantViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private val updateTableColab: UpdateTableColab,
    private val hasRegColab: HasRegColab,
    private val setRegAttendant: SetRegAttendant,
    private val getRegAttendant: GetRegAttendant
) : ViewModel() {

    private val option: Int = saveStateHandle[OPTION_ARG]!!

    private val _uiState = MutableStateFlow(AttendantStateUpdate())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: AttendantStateUpdate.() -> AttendantStateUpdate) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init { updateState { copy(option = Option.entries[this@AttendantViewModel.option]) } }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            getRegAttendant().getOrThrow()?.toString() ?: ""
        }
            .onSuccess{ updateState { copy( regColab = it) } }
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    fun onTextField(text: String, typeButton: TypeButton) {
        when (typeButton) {
            TypeButton.NUMERIC -> updateState { copy(regColab = addTextField(regColab, text)) }
            TypeButton.CLEAN -> updateState { copy(regColab = clearTextField(regColab)) }
            TypeButton.OK -> set()
            TypeButton.UPDATE -> {
                viewModelScope.launch { updateAllDatabase().collect { _uiState.value = it } }
            }
        }
    }

    private fun set() = viewModelScope.launch {
        runCatching {
            if (state.regColab.isBlank()) {
                updateState { withFailure(getClassAndMethod(), Errors.FIELD_EMPTY) }
                return@launch
            }
            val check = hasRegColab(state.regColab).getOrThrow()
            if (check) setRegAttendant(state.regColab).getOrThrow()
            check
        }
            .onSuccessUpdateCheckAccess(::updateState)
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    suspend fun updateAllDatabase(): Flow<AttendantStateUpdate> =
        executeUpdateSteps(
            steps = listOf(updateTableColab(sizeUpdate())),
            getState = { _uiState.value },
            getStatus = { it.status },
            copyStateWithStatus = { state, status -> state.copy(status = status) },
            classAndMethod = getClassAndMethod(),
        )

}