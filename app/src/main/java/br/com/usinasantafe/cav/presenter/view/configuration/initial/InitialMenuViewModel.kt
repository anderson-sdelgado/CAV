package br.com.usinasantafe.cav.presenter.view.configuration.initial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.common.GetStatusSend
import br.com.usinasantafe.cav.domain.usecases.config.CheckAccessInitial
import br.com.usinasantafe.cav.lib.StatusSend
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

data class InitialMenuState(
    val statusSend: StatusSend = StatusSend.STARTED,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<InitialMenuState> {

    override fun copyWithStatus(status: UiStatusState): InitialMenuState =
        copy(status = status)

}

@HiltViewModel
class InitialMenuViewModel @Inject constructor(
    private val getStatusSend: GetStatusSend,
    private val checkAccessInitial: CheckAccessInitial
) : ViewModel() {

    private val _uiState = MutableStateFlow(InitialMenuState())
    val uiState = _uiState.asStateFlow()

    private fun updateState(block: InitialMenuState.() -> InitialMenuState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(status = status.copy(flagDialog = false)) }

    fun recoverStatusSend() = viewModelScope.launch {
        runCatching {
            getStatusSend().getOrThrow()
        }
            .onSuccess { updateState { copy(statusSend = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun onCheckAccess() = viewModelScope.launch {
        runCatching {
            checkAccessInitial().getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}