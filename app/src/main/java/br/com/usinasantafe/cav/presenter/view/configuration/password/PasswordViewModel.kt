package br.com.usinasantafe.cav.presenter.view.configuration.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.config.CheckPassword
import br.com.usinasantafe.cav.presenter.view.card.local.InputLocalState
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureHandled
import br.com.usinasantafe.cav.utils.onFailureState
import br.com.usinasantafe.cav.utils.onSuccessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PasswordState(
    val password: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<PasswordState> {

    override fun copyWithStatus(status: UiStatusState): PasswordState =
        copy(status = status)

}

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val checkPassword: CheckPassword
) : ViewModel() {

    private val _uiState = MutableStateFlow(PasswordState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: PasswordState.() -> PasswordState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onPasswordChanged(password: String) = updateState { copy(password = password) }

    fun onCheckAccess() =
        viewModelScope.launch {
            runCatching {
                checkPassword(state.password).getOrThrow()
            }
                .onSuccessState(::updateState)
                .onFailureState(getClassAndMethod(), ::updateState)
        }

}