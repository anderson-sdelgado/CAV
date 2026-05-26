package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.equip

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetNroEquip
import br.com.usinasantafe.cav.domain.usecases.card.SetEquip
import br.com.usinasantafe.cav.domain.usecases.common.HasNroEquip
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableEquip
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.Args.TYPE_ARG
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

data class EquipStateUpdate(
    val option: Option = Option.INSERT,
    val type: Type = Type.MAIN,
    val nroEquip: String = "",
    override val status: UiStatusStateUpdate = UiStatusStateUpdate()
) : UiStateWithStatusUpdate<EquipStateUpdate> {

    override fun copyWithStatus(status: UiStatusStateUpdate): EquipStateUpdate =
        copy(status = status)

}

@HiltViewModel
class EquipViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private val getNroEquip: GetNroEquip,
    private val updateTableEquip: UpdateTableEquip,
    private val hasNroEquip: HasNroEquip,
    private val setEquip: SetEquip
) : ViewModel() {

    private val option: Int = saveStateHandle[OPTION_ARG]!!
    private val type: Int = saveStateHandle[TYPE_ARG]!!

    private val _uiState = MutableStateFlow(EquipStateUpdate())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: EquipStateUpdate.() -> EquipStateUpdate) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                option = Option.entries[this@EquipViewModel.option],
                type = Type.entries[this@EquipViewModel.type]
            )
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            getNroEquip(state.option, state.type).getOrThrow()
        }
            .onSuccess { updateState { copy(nroEquip = it) } }
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    fun onTextField(text: String, typeButton: TypeButton) {
        when (typeButton) {
            TypeButton.NUMERIC -> updateState { copy(nroEquip = addTextField(nroEquip, text)) }
            TypeButton.CLEAN -> updateState { copy(nroEquip = clearTextField(nroEquip)) }
            TypeButton.OK -> set()
            TypeButton.UPDATE -> {
                viewModelScope.launch { updateAllDatabase().collect { _uiState.value = it } }
            }
        }
    }

    private fun set() = viewModelScope.launch {
        runCatching {
            if (state.nroEquip.isBlank()) {
                updateState { withFailure(getClassAndMethod(), Errors.FIELD_EMPTY) }
                return@launch
            }
            val check = hasNroEquip(state.nroEquip).getOrThrow()
            if (check) setEquip(state.option, state.type, state.nroEquip).getOrThrow()
            check
        }
            .onSuccessUpdateCheckAccess(::updateState)
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    suspend fun updateAllDatabase(): Flow<EquipStateUpdate> =
        executeUpdateSteps(
            steps = listOf(updateTableEquip(sizeUpdate())),
            getState = { _uiState.value },
            getStatus = { it.status },
            copyStateWithStatus = { state, status -> state.copy(status = status) },
            classAndMethod = getClassAndMethod(),
        )

}