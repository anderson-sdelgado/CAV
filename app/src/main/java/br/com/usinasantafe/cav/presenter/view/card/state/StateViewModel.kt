package br.com.usinasantafe.cav.presenter.view.card.state

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetStateColab
import br.com.usinasantafe.cav.domain.usecases.card.SetStateColab
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.Args.TYPE_ARG
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import br.com.usinasantafe.cav.utils.onSuccessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StateState(
    val option: Option = Option.INSERT,
    val type: Type = Type.MAIN,
    val idSelection: Int = 1,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<StateState> {

    override fun copyWithStatus(status: UiStatusState): StateState =
        copy(status = status)

}

@HiltViewModel
class StateViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private val getStateColab: GetStateColab,
    private val setStateColab: SetStateColab
) : ViewModel() {

    private val option: Int = saveStateHandle[OPTION_ARG]!!
    private val type: Int = saveStateHandle[TYPE_ARG]!!

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
                type = Type.entries[this@StateViewModel.type]
            )
        }
    }

    fun onSelection(id: Int) = updateState { copy(idSelection = id) }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            getStateColab(state.option, state.type).getOrThrow()
        }
            .onSuccess { updateState { copy(idSelection = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun set() = viewModelScope.launch {
        runCatching {
            setStateColab(state.option, state.type, state.idSelection).getOrThrow()
        }
            .onSuccessState(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}