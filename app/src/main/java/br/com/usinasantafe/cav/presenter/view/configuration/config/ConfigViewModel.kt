package br.com.usinasantafe.cav.presenter.view.configuration.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.config.GetConfig
import br.com.usinasantafe.cav.domain.usecases.config.SaveConfig
import br.com.usinasantafe.cav.domain.usecases.config.SendConfig
import br.com.usinasantafe.cav.domain.usecases.config.SetFinishUpdateAllTable
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableColab
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableEquip
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.executeUpdateSteps
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureEmit
import br.com.usinasantafe.cav.utils.onFailureHandled
import br.com.usinasantafe.cav.utils.onFailureUpdate
import br.com.usinasantafe.cav.utils.percentage
import br.com.usinasantafe.cav.utils.required
import br.com.usinasantafe.cav.utils.sizeUpdate
import br.com.usinasantafe.cav.utils.withFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ConfigState(
    val number: String = "",
    val password: String = "",
    val version: String = "",
    val flagAccess: Boolean = false,
    override val status: UpdateStatusState = UpdateStatusState()
) : UiStateWithStatus<ConfigState> {

    override fun copyWithStatus(status: UpdateStatusState): ConfigState =
        copy(status = status)
}

@HiltViewModel
class ConfigViewModel @Inject constructor(
    private val getConfig: GetConfig,
    private val sendConfig: SendConfig,
    private val saveConfig: SaveConfig,
    private val setFinishUpdateAllTable: SetFinishUpdateAllTable,
    private val updateTableColab: UpdateTableColab,
    private val updateTableEquip: UpdateTableEquip
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConfigState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: ConfigState.() -> ConfigState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState {
        val flag = (status.levelUpdate == LevelUpdate.FINISH_UPDATE_COMPLETED)
        copy(status = status.copy(flagDialog = false, flagFailure = false), flagAccess = flag)
    }

    fun onNumberChanged(v: String) = updateState { copy(number = v) }
    fun onPasswordChanged(v: String) = updateState { copy(password = v) }

    fun setConfigMain(version: String) = updateState {
        copy(version = version)
    }

    fun returnConfig() = viewModelScope.launch {
        runCatching {
            getConfig().getOrThrow()
        }
            .onSuccess { config ->
                config?.let {
                    updateState { copy(number = it.number, password = it.password,) }
                }
            }
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    private fun ConfigState.isValid() = number.isNotBlank() && password.isNotBlank()

    fun onSaveAndUpdate() = viewModelScope.launch {
        if (!state.isValid()) {
            updateState { withFailure(getClassAndMethod(), Errors.FIELD_EMPTY) }
            return@launch
        }
        token().collect { state ->
            _uiState.value = state
            if (!state.status.flagFailure && state.status.currentProgress == 1f) {
                updateAllDatabase().onCompletion {
                    if(!state.status.flagFailure){
                        setFinishUpdateAllTable().fold(
                            onSuccess = {
                                emit(
                                    state.copy(
                                        status = state.status.copy(
                                            tableUpdate = "",
                                            flagDialog = true,
                                            flagProgress = true,
                                            flagFailure = false,
                                            levelUpdate = LevelUpdate.FINISH_UPDATE_COMPLETED,
                                            currentProgress = 1f
                                        )
                                    )
                                )
                            },
                            onFailure = {
                                val newState = state.withFailure(getClassAndMethod(), it, flagProgress = true)
                                emit(newState)
                                _uiState.value = newState
                            }
                        )
                    }
                }.collect { _uiState.value = it }
            }
        }
    }

    fun token(): Flow<ConfigState> = flow {
            runCatching {
                val sizeToken = 3f
                emit(
                    state.copy(
                        status = state.status.copy(
                            flagProgress = true,
                            levelUpdate = LevelUpdate.GET_TOKEN,
                            currentProgress = percentage(1f, sizeToken)
                        )
                    )
                )
                val config = sendConfig(state.number, state.password, state.version).getOrThrow()

                emit(
                    state.copy(
                        status = state.status.copy(
                            flagProgress = true,
                            levelUpdate = LevelUpdate.SAVE_TOKEN,
                            currentProgress = percentage(2f, sizeToken)
                        )
                    )
                )

                saveConfig(state.number, state.password, state.version, config::idServ.required()).getOrThrow()

            }
                .onSuccess {
                    emit(
                        state.copy(
                            status = state.status.copy(
                                flagProgress = true,
                                currentProgress = 1f,
                                levelUpdate = LevelUpdate.FINISH_UPDATE_INITIAL
                            )
                        )
                    )
                }
                .onFailureEmit(this, state, getClassAndMethod(), Errors.TOKEN)
    }

    suspend fun updateAllDatabase(): Flow<ConfigState> =
        executeUpdateSteps(
            steps = listUpdate(),
            getState = { _uiState.value },
            getStatus = { it.status },
            copyStateWithStatus = { state, status -> state.copy(status = status) },
            classAndMethod = getClassAndMethod(),
            flagUpdateFinish = false
        )

    suspend fun listUpdate() : List<Flow<UpdateStatusState>> {
        val sizeAll = sizeUpdate(2f)
        val list = mutableListOf<Flow<UpdateStatusState>>()
        var count = 0f
        list.add(updateTableColab(sizeAll, ++count))
        list.add(updateTableEquip(sizeAll, ++count))
        return list
    }
}