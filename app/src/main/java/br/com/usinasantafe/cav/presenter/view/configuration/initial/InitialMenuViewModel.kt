package br.com.usinasantafe.cav.presenter.view.configuration.initial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.StatusSend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class InitialMenuState(
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val statusSend: StatusSend = StatusSend.STARTED
)

@HiltViewModel
class InitialMenuViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(InitialMenuState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: InitialMenuState.() -> InitialMenuState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(flagDialog = false) }

    fun recoverStatusSend() = viewModelScope.launch {

    }

    fun checkAccess() = viewModelScope.launch {

    }

    private fun onError(failure: String) = updateState { copy(flagDialog = true, failure = failure, flagFailure = true) }

}