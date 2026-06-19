package br.com.usinasantafe.cav.presenter.view.card.equip.equip

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetNroEquip
import br.com.usinasantafe.cav.domain.usecases.card.SetEquip
import br.com.usinasantafe.cav.domain.usecases.common.HasNroEquip
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableEquip
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
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

data class EquipStateUpdate(
    val option: Option = Option.INSERT,
    val flowNote: FlowNote = FlowNote.EQUIP,
    val idMain: Int = 0,
    val idSecondary: Int = 0,
    val text: String = "",
    override val status: UiStatusStateUpdate = UiStatusStateUpdate()
) : UiStateWithStatusUpdate<EquipStateUpdate> {

    override fun copyWithStatus(status: UiStatusStateUpdate): EquipStateUpdate =
        copy(status = status)

}

@HiltViewModel
class EquipViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getNroEquip: GetNroEquip,
    private val updateTableEquip: UpdateTableEquip,
    private val hasNroEquip: HasNroEquip,
    private val setEquip: SetEquip
) : ViewModel() {

    private val option: Int = savedStateHandle[OPTION_ARG]!!
    private val flow: Int = savedStateHandle[FLOW_NOTE_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!
    private val idSecondary: Int = savedStateHandle[ID_SECONDARY_ARG]!!

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
                flowNote = FlowNote.entries[this@EquipViewModel.flow],
                idMain = this@EquipViewModel.idMain,
                idSecondary = this@EquipViewModel.idSecondary
            )
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            getNroEquip(state.option, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccess { updateState { copy(text = it) } }
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    fun onTextField(text: String, typeButton: TypeButton) {
        when (typeButton) {
            TypeButton.NUMERIC -> updateState { copy(text = addTextField(this.text, text)) }
            TypeButton.CLEAN -> updateState { copy(text = clearTextField(this.text)) }
            TypeButton.OK -> set()
            TypeButton.UPDATE -> {
                viewModelScope.launch { updateAllDatabase().collect { _uiState.value = it } }
            }
        }
    }

    private fun set() = viewModelScope.launch {
        if (state.text.isBlank()) {
            updateState { withFailure(getClassAndMethod(), Errors.FIELD_EMPTY) }
            return@launch
        }
        hasNroEquip(state.text)
            .mapCatching { check ->
                if (check) setEquip(state.text, state.option, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
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