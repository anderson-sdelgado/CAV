package br.com.usinasantafe.cav.presenter.view.card.involved.address

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddressState(
    val text: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<AddressState> {

    override fun copyWithStatus(status: UiStatusState): AddressState =
        copy(status = status)

}

@HiltViewModel
class AddressViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddressState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: AddressState.() -> AddressState) {
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

    fun set() = viewModelScope.launch {

    }
}