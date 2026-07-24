package br.com.usinasantafe.cav.presenter.view.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.common.StartFlow
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SplashState(
    val flagAccessCard: Boolean = false,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<SplashState> {

    override fun copyWithStatus(status: UiStatusState): SplashState =
        copy(status = status)

}

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val startFlow: StartFlow
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: SplashState.() -> SplashState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun startApp() = viewModelScope.launch {
        runCatching {
            startFlow().getOrThrow()
        }
            .onSuccess { updateState { copy(flagAccessCard = it, status = status.copy(flagAccess = true)) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }


}