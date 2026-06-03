package br.com.usinasantafe.cav.presenter.view.card.involved.phone

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetPhone
import br.com.usinasantafe.cav.domain.usecases.card.SetPhone
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
import br.com.usinasantafe.cav.presenter.theme.clearTextField
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

data class PhoneState(
    val flowNote: FlowNote = FlowNote.INVOLVED,
    val idMain: Int = 0,
    val idSecondary: Int = 0,
    val text: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<PhoneState> {

    override fun copyWithStatus(status: UiStatusState): PhoneState =
        copy(status = status)

}

@HiltViewModel
class PhoneViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPhone: GetPhone,
    private val setPhone: SetPhone
) : ViewModel() {

    private val flowNote: Int = savedStateHandle[FLOW_NOTE_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!
    private val idSecondary: Int = savedStateHandle[ID_SECONDARY_ARG]!!

    private val _uiState = MutableStateFlow(PhoneState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: PhoneState.() -> PhoneState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                flowNote = FlowNote.entries[this@PhoneViewModel.flowNote],
                idMain = this@PhoneViewModel.idMain,
                idSecondary = this@PhoneViewModel.idSecondary
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
            getPhone(state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccess { updateState { copy(text = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun onTextField(text: String, typeButton: TypeButton) {
        when (typeButton) {
            TypeButton.NUMERIC -> updateState { copy(text = inputPhone(text)) }
            TypeButton.CLEAN -> updateState { copy(text = clearTextField(this.text)) }
            TypeButton.OK -> set()
            TypeButton.UPDATE -> {}
        }
    }

    private fun inputPhone(char: String): String {
        val currentText = state.text
        if (currentText.length >= 15) return currentText
        return when (currentText.length) {
            0    -> "($char"
            3    -> "$currentText) $char"
            10   -> "$currentText-$char"
            else -> currentText + char
        }
    }

    private fun set() = viewModelScope.launch {
        runCatching {
            if((!state.text.isBlank()) && (state.text.length != 15)) {
                updateState { withFailure(getClassAndMethod(), Errors.INVALID) }
                return@launch
            }
            setPhone(state.text, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}