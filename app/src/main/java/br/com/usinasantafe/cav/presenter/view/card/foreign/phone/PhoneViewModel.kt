package br.com.usinasantafe.cav.presenter.view.card.foreign.phone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PhoneState(
    val text: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<PhoneState> {

    override fun copyWithStatus(status: UiStatusState): PhoneState =
        copy(status = status)

}

@HiltViewModel
class PhoneViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhoneState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: PhoneState.() -> PhoneState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onTextChanged(text: String) {
        _uiState.update {
            it.copy(text = text)
        }
    }

    fun recoverData() = viewModelScope.launch {

    }

    fun onTextField(text: String, typeButton: TypeButton) {

    }

    private fun set() = viewModelScope.launch {

    }

}