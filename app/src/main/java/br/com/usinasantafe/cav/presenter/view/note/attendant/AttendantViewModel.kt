package br.com.usinasantafe.cav.presenter.view.note.attendant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.common.HasRegColab
import br.com.usinasantafe.cav.domain.usecases.note.SetRegAttendant
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableColab
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.theme.addTextField
import br.com.usinasantafe.cav.presenter.theme.clearTextField
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.executeUpdateSteps
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureUpdate
import br.com.usinasantafe.cav.utils.sizeUpdate
import br.com.usinasantafe.cav.utils.withFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AttendantState(
    val regColab: String = "",
    val flagAccess: Boolean = false,
    override val status: UpdateStatusState = UpdateStatusState()
) : UiStateWithStatus<AttendantState> {

    override fun copyWithStatus(status: UpdateStatusState): AttendantState =
        copy(status = status)

}


@HiltViewModel
class AttendantViewModel @Inject constructor(
    private val updateTableColab: UpdateTableColab,
    private val hasRegColab: HasRegColab,
    private val setRegAttendant: SetRegAttendant
) : ViewModel() {

    private val _uiState = MutableStateFlow(AttendantState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: AttendantState.() -> AttendantState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun setTextField(text: String, typeButton: TypeButton) {
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
            val check = hasRegColab(uiState.value.regColab).getOrThrow()
            if (check) setRegAttendant(uiState.value.regColab).getOrThrow()
            check
        }
            .onSuccess {
                updateState {
                    copy(
                        flagAccess = it,
                        status = status.copy(
                            flagDialog = !it,
                            flagFailure = !it,
                            errors = Errors.INVALID
                        )
                    )
                }
            }
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    suspend fun updateAllDatabase(): Flow<AttendantState> =
        executeUpdateSteps(
            steps = listOf(updateTableColab(sizeUpdate())),
            getState = { _uiState.value },
            getStatus = { it.status },
            copyStateWithStatus = { state, status -> state.copy(status = status) },
            classAndMethod = getClassAndMethod(),
        )

}