package br.com.usinasantafe.cav.presenter.view.card.external.address

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetAddress
import br.com.usinasantafe.cav.domain.usecases.card.SetAddress
import br.com.usinasantafe.cav.lib.FlowNote
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

data class AddressState(
    val flowNote: FlowNote = FlowNote.INVOLVED_EXTERNAL,
    val idMain: Int = 0,
    val idSecondary: Int = 0,
    val text: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<AddressState> {

    override fun copyWithStatus(status: UiStatusState): AddressState =
        copy(status = status)

}

@HiltViewModel
class AddressViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAddress: GetAddress,
    private val setAddress: SetAddress
) : ViewModel() {

    private val flowNote: Int = savedStateHandle[FLOW_NOTE_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!
    private val idSecondary: Int = savedStateHandle[ID_SECONDARY_ARG]!!

    private val _uiState = MutableStateFlow(AddressState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: AddressState.() -> AddressState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                flowNote = FlowNote.entries[this@AddressViewModel.flowNote],
                idMain = this@AddressViewModel.idMain,
                idSecondary = this@AddressViewModel.idSecondary
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
            getAddress(state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccess { updateState { copy(text = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun set() = viewModelScope.launch {
        runCatching {
            setAddress(state.text, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }
}