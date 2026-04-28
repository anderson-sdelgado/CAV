package br.com.usinasantafe.cav.presenter.view.configuration.initial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.common.GetStatusSend
import br.com.usinasantafe.cav.domain.usecases.config.CheckAccessInitial
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureHandled
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
    private val getStatusSend: GetStatusSend,
    private val checkAccessInitial: CheckAccessInitial
) : ViewModel() {

    private val _uiState = MutableStateFlow(InitialMenuState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: InitialMenuState.() -> InitialMenuState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(flagDialog = false) }

    fun recoverStatusSend() = viewModelScope.launch {
        runCatching {
            getStatusSend().getOrThrow()
        }
            .onSuccess { updateState { copy(statusSend = it) } }
            .onFailureHandled(getClassAndMethod(), ::onError)
    }

    fun checkAccess() = viewModelScope.launch {
        runCatching {
            checkAccessInitial().getOrThrow()
        }
            .onSuccess { updateState { copy(flagDialog = !it, flagAccess = it) } }
            .onFailureHandled(getClassAndMethod(), ::onError)
    }

    private fun onError(failure: String) = updateState { copy(flagDialog = true, failure = failure, flagFailure = true) }

}