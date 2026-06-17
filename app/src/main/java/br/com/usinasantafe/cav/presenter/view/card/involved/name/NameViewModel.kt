package br.com.usinasantafe.cav.presenter.view.card.involved.name

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetName
import br.com.usinasantafe.cav.domain.usecases.card.SetName
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import br.com.usinasantafe.cav.utils.onSuccessStateAccess
import br.com.usinasantafe.cav.utils.withFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NameState(
    val option: Option = Option.INSERT,
    val flowNote: FlowNote = FlowNote.EQUIP,
    val idMain: Int = 0,
    val idSecondary: Int = 0,
    val text: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<NameState> {

    override fun copyWithStatus(status: UiStatusState): NameState =
        copy(status = status)

}

@HiltViewModel
class NameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getName: GetName,
    private val setName: SetName
) : ViewModel() {

    private val option: Int = savedStateHandle[OPTION_ARG]!!
    private val flowNote: Int = savedStateHandle[FLOW_NOTE_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!
    private val idSecondary: Int = savedStateHandle[ID_SECONDARY_ARG]!!

    private val _uiState = MutableStateFlow(NameState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: NameState.() -> NameState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                option = Option.entries[this@NameViewModel.option],
                flowNote = FlowNote.entries[this@NameViewModel.flowNote],
                idMain = this@NameViewModel.idMain,
                idSecondary = this@NameViewModel.idSecondary
            )
        }
    }

    fun onTextChanged(text: String) {
        _uiState.update {
            it.copy(text = text)
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            getName(state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccess { updateState { copy(text = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun set() = viewModelScope.launch {
        runCatching {
            if(state.text.isBlank()) {
                updateState { withFailure(getClassAndMethod(), Errors.FIELD_EMPTY) }
                return@launch
            }
            setName(state.text, state.option, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}