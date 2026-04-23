package br.com.usinasantafe.cav.presenter.view.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureHandled
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class SplashState(
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val failure: String = "",
)

@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: SplashState.() -> SplashState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(flagDialog = false) }

    fun startApp() = viewModelScope.launch {
        onSuccess()
    }

    private fun onSuccess() = updateState { copy(flagAccess = true) }
    private fun onError(error: String) = updateState { copy(flagDialog = true, failure = error) }

}