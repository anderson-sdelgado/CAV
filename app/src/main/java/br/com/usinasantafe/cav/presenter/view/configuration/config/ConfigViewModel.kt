package br.com.usinasantafe.cav.presenter.view.configuration.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.percentage
import br.com.usinasantafe.cav.utils.withFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ConfigState(
    val number: String = "",
    val password: String = "",
    val version: String = "",
    val qtdTable: Float = 0f,
    override val status: UpdateStatusState = UpdateStatusState()
) : UiStateWithStatus<ConfigState> {

    override fun copyWithStatus(status: UpdateStatusState): ConfigState =
        copy(status = status)
}

@HiltViewModel
class ConfigViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConfigState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: ConfigState.() -> ConfigState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState {
        copy(status = status.copy(flagDialog = false, flagFailure = false))
    }

    fun onNumberChanged(v: String) = updateState { copy(number = v) }
    fun onPasswordChanged(v: String) = updateState { copy(password = v) }

    fun setConfigMain(version: String) = updateState {
        copy(version = version)
    }

    fun returnDataConfig() = viewModelScope.launch {

    }

    private fun ConfigState.isValid() = number.isNotBlank() && password.isNotBlank()

    fun onSaveAndUpdate() = viewModelScope.launch {
        if (!state.isValid()) {
            updateState { withFailure(getClassAndMethod(), Errors.FIELD_EMPTY) }
            return@launch
        }
    }

    fun token(): Flow<ConfigState> = flow {
        with(state) {
            runCatching {
                val sizeToken = 3f
                emit(
                    copy(
                        status = status.copy(
                            flagProgress = true,
                            levelUpdate = LevelUpdate.GET_TOKEN,
                            currentProgress = percentage(1f, sizeToken)
                        )
                    )
                )
            }
        }
    }
}