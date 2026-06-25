package br.com.usinasantafe.cav.presenter.view.card.involved.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.DeletePassenger
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
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

data class InvolvedDataState(
    val flowNote: FlowNote = FlowNote.COLAB,
    val idMain: Int = 0,
    val idSecondary: Int = 0,
    val document: String = "",
    val name: String = "",
    val state: String = "",
    val phone: String = "",
    val address: String = "",
    val detail: String = "",
    val flagDialogCheck: Boolean = false,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<InvolvedDataState> {

    override fun copyWithStatus(status: UiStatusState): InvolvedDataState =
        copy(status = status)

}

@HiltViewModel
class InvolvedDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val deletePassenger: DeletePassenger
) : ViewModel() {

    private val flowNote: Int = savedStateHandle[FLOW_NOTE_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!
    private val idSecondary: Int = savedStateHandle[ID_SECONDARY_ARG]!!

    private val _uiState = MutableStateFlow(InvolvedDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: InvolvedDataState.() -> InvolvedDataState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    init {
        updateState {
            copy(
                flowNote = FlowNote.entries[this@InvolvedDataViewModel.flowNote],
                idMain = this@InvolvedDataViewModel.idMain,
                idSecondary = this@InvolvedDataViewModel.idSecondary
            )
        }
    }

    fun recoverData() = viewModelScope.launch {

    }

    fun delete() = viewModelScope.launch {
        runCatching {
            deletePassenger(state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccess { recoverData() }
            .onFailureState(getClassAndMethod(), ::updateState)
    }
}