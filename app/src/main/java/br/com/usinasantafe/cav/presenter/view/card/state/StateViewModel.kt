package br.com.usinasantafe.cav.presenter.view.card.state

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetState
import br.com.usinasantafe.cav.domain.usecases.card.SetState
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
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

data class StateState(
    val option: Option = Option.INSERT,
    val flowNote: FlowNote = FlowNote.COLAB,
    val stateSelection: State = State.UNHARMED,
    val idMain: Int = 0,
    val idSecondary: Int = 0,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<StateState> {

    override fun copyWithStatus(status: UiStatusState): StateState =
        copy(status = status)

}

@HiltViewModel
class StateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getState: GetState,
    private val setState: SetState
) : ViewModel() {

    private val option: Int = savedStateHandle[OPTION_ARG]!!
    private val flowNote: Int = savedStateHandle[FLOW_NOTE_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!
    private val idSecondary: Int = savedStateHandle[ID_SECONDARY_ARG]!!

    private val _uiState = MutableStateFlow(StateState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: StateState.() -> StateState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                option = Option.entries[this@StateViewModel.option],
                flowNote = FlowNote.entries[this@StateViewModel.flowNote],
                idMain = this@StateViewModel.idMain,
                idSecondary = this@StateViewModel.idSecondary,
            )
        }
    }

    fun onSelection(state: State) = updateState { copy(stateSelection = state) }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            if (state.option == Option.INSERT) return@launch
            getState(state.option, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccess { updateState { copy(stateSelection = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun set() = viewModelScope.launch {
        runCatching {
            setState(state.stateSelection, state.option, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}