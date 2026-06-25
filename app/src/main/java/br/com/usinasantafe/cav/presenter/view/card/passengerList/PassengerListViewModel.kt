package br.com.usinasantafe.cav.presenter.view.card.passengerList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.DeletePassenger
import br.com.usinasantafe.cav.domain.usecases.card.ListPassenger
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PassengerListState(
    val list: List<ItemListScreenModel> = emptyList(),
    val flowNote: FlowNote = FlowNote.COLAB,
    val idMain: Int = 0,
    val idSelection: Int = 0,
    val flagDialogCheck: Boolean = false,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<PassengerListState> {

    override fun copyWithStatus(status: UiStatusState): PassengerListState =
        copy(status = status)

}

@HiltViewModel
class PassengerListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val listPassenger: ListPassenger,
    private val deletePassenger: DeletePassenger
) : ViewModel() {

    private val flowNote: Int = savedStateHandle[FLOW_NOTE_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!

    private val _uiState = MutableStateFlow(PassengerListState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: PassengerListState.() -> PassengerListState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    fun onSelectionDelete(id: Int) = updateState { copy(flagDialogCheck = true, idSelection = id) }

    init {
        updateState {
            copy(
                flowNote = FlowNote.entries[this@PassengerListViewModel.flowNote],
                idMain = this@PassengerListViewModel.idMain,
            )
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            listPassenger(state.flowNote, state.idMain).getOrThrow()
        }
            .onSuccess { updateState { copy(list = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun delete() = viewModelScope.launch {
        runCatching {
            deletePassenger( state.flowNote, state.idMain, state.idSelection).getOrThrow()
        }
            .onSuccess { recoverData() }
            .onFailureState(getClassAndMethod(), ::updateState)
    }
}