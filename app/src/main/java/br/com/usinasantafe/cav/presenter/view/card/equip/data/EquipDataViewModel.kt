package br.com.usinasantafe.cav.presenter.view.card.equip.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.DeleteEquipSec
import br.com.usinasantafe.cav.domain.usecases.card.GetDescEquip
import br.com.usinasantafe.cav.domain.usecases.card.GetDetail
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import br.com.usinasantafe.cav.utils.onSuccessStateAccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EquipDataState(
    val flowNote: FlowNote = FlowNote.EQUIP,
    val idMain: Int = 0,
    val idSecondary: Int = 0,
    val equip: String = "",
    val detail: String = "",
    val flagDialogCheck: Boolean = false,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<EquipDataState> {

    override fun copyWithStatus(status: UiStatusState): EquipDataState =
        copy(status = status)

}

@HiltViewModel
class EquipDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDescEquip: GetDescEquip,
    private val getDetail: GetDetail,
    private val deleteEquipSec: DeleteEquipSec
) : ViewModel() {

    private val flowNote: Int = savedStateHandle[FLOW_NOTE_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!
    private val idSecondary: Int = savedStateHandle[ID_SECONDARY_ARG]!!

    private val _uiState = MutableStateFlow(EquipDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: EquipDataState.() -> EquipDataState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    init {
        updateState {
            copy(
                flowNote = FlowNote.entries[this@EquipDataViewModel.flowNote],
                idMain = this@EquipDataViewModel.idMain,
                idSecondary = this@EquipDataViewModel.idSecondary
            )
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val descEquip = getDescEquip(state.flowNote, state.idMain, state.idSecondary).getOrThrow()
            val detail = getDetail(Option.EDIT, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
            Pair(descEquip, detail)
        }
            .onSuccess { (equip, detail) ->
                updateState {
                    copy(
                        equip = equip,
                        detail = detail,
                        status = status.copy(flagFailure = false)
                    )
                }
            }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun delete() = viewModelScope.launch {
        runCatching {
            deleteEquipSec( state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }
}