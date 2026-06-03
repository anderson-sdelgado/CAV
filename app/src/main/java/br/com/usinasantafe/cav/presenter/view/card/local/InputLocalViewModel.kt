package br.com.usinasantafe.cav.presenter.view.card.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.SetLocal
import br.com.usinasantafe.cav.lib.Errors
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

data class InputLocalState(
    val address: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<InputLocalState> {

    override fun copyWithStatus(status: UiStatusState): InputLocalState =
        copy(status = status)

}

@HiltViewModel
class InputLocalViewModel @Inject constructor(
    private val setLocal: SetLocal
) : ViewModel() {

    private val _uiState = MutableStateFlow(InputLocalState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: InputLocalState.() -> InputLocalState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onAddressChanged(address: String) {
        _uiState.update {
            it.copy(address = address)
        }
    }

    fun set() = viewModelScope.launch {
        runCatching {
            if (state.address.isBlank()) {
                updateState { withFailure(getClassAndMethod(), Errors.FIELD_EMPTY) }
                return@launch
            }
            setLocal(address = state.address).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}