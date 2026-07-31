package br.com.usinasantafe.cav.presenter.view.card.external.document

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetDocument
import br.com.usinasantafe.cav.domain.usecases.card.SetDocument
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
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

data class DocumentState(
    val option: Option = Option.INSERT,
    val flowNote: FlowNote = FlowNote.INVOLVED_EXTERNAL,
    val idMain: Int = 0,
    val idSecondary: Int = 0,
    val text: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<DocumentState> {

    override fun copyWithStatus(status: UiStatusState): DocumentState =
        copy(status = status)

}

@HiltViewModel
class DocumentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDocument: GetDocument,
    private val setDocument: SetDocument
) : ViewModel() {

    private val option: Int = savedStateHandle[OPTION_ARG]!!
    private val flowNote: Int = savedStateHandle[FLOW_NOTE_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!
    private val idSecondary: Int = savedStateHandle[ID_SECONDARY_ARG]!!

    private val _uiState = MutableStateFlow(DocumentState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: DocumentState.() -> DocumentState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                option = Option.entries[this@DocumentViewModel.option],
                flowNote = FlowNote.entries[this@DocumentViewModel.flowNote],
                idMain = this@DocumentViewModel.idMain,
                idSecondary = this@DocumentViewModel.idSecondary
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
            getDocument(state.option, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccess { updateState { copy(text = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun onTextField(text: String, typeButton: TypeButton) {
        when (typeButton) {
            TypeButton.NUMERIC -> updateState { copy(text = inputCPF(text)) }
            TypeButton.CLEAN -> updateState { copy(text = clearTextField(this.text)) }
            TypeButton.OK -> set()
            TypeButton.UPDATE -> {}
        }
    }

    private fun inputCPF(char: String): String {
        val currentText = state.text
        if (currentText.length >= 14) return currentText
        return when (currentText.length) {
            3, 7 -> "$currentText.$char"
            11   -> "$currentText-$char"
            else -> currentText + char
        }
    }

    private fun set() = viewModelScope.launch {
        runCatching {
            if(state.text.isBlank()) return@runCatching
            if (!isValidCPF(state.text)) {
                updateState { withFailure(getClassAndMethod(), Errors.INVALID) }
                return@launch
            }
            setDocument(state.text, state.option, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    private fun isValidCPF(cpf: String): Boolean {
        val cleanCpf = cpf.replace(Regex("[^0-9]"), "")
        if (cleanCpf.length != 11) return false
        if (cleanCpf.all { it == cleanCpf[0] }) return false
        val digit1 = calculateDigit(cleanCpf.substring(0, 9), intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2))
        val digit2 = calculateDigit(cleanCpf.substring(0, 9) + digit1, intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2))
        return cleanCpf.endsWith("$digit1$digit2")
    }

    private fun calculateDigit(str: String, pesos: IntArray): Int {
        var soma = 0
        for (i in str.indices) {
            soma += str[i].toString().toInt() * pesos[i]
        }
        val resto = soma % 11
        return if (resto < 2) 0 else 11 - resto
    }
}