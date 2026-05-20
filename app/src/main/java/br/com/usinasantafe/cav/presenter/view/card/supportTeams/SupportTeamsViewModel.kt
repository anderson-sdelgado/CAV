package br.com.usinasantafe.cav.presenter.view.card.supportTeams

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.ListSupportTeams
import br.com.usinasantafe.cav.domain.usecases.card.SetSupportTeamsList
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableSupportTeams
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.presenter.view.card.typeAccident.TypeAccidentState
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.executeUpdateSteps
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureUpdate
import br.com.usinasantafe.cav.utils.sizeUpdate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SupportTeamsState(
    val flagAccess: Boolean = false,
    override val status: UpdateStatusState = UpdateStatusState()
): UiStateWithStatus<SupportTeamsState> {

    override fun copyWithStatus(status: UpdateStatusState): SupportTeamsState =
        copy(status = status)

}

@HiltViewModel
class SupportTeamsViewModel @Inject constructor(
    private val listSupportTeams: ListSupportTeams,
    private val updateTableSupportTeams: UpdateTableSupportTeams,
    private val setSupportTeamsList: SetSupportTeamsList
) : ViewModel() {

    val list = mutableStateListOf<ItemCheckBoxScreenModel>()

    private val _uiState = MutableStateFlow(SupportTeamsState())
    val uiState = _uiState.asStateFlow()

    private fun updateState(block: SupportTeamsState.() -> SupportTeamsState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onCheckChange(id: Int, checked: Boolean) {
        val index = list.indexOfFirst { it.id == id }
        if (index != -1) {
            list[index] = list[index].copy(flag = checked)
        }
    }

    fun list() = viewModelScope.launch {
        runCatching {
            listSupportTeams().getOrThrow()
        }
            .onSuccess {
                list.clear()
                list.addAll(it)
            }
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    fun save() = viewModelScope.launch {
        runCatching {
            setSupportTeamsList(list.toList()).getOrThrow()
        }
            .onSuccess { updateState { copy(flagAccess = true) } }
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    fun updateDatabase() = viewModelScope.launch {
        updateAllDatabase().collect { stateUpdate ->
            _uiState.value = stateUpdate
        }
        if (_uiState.value.status.levelUpdate == LevelUpdate.FINISH_UPDATE_COMPLETED) {
            list()
        }
    }

    suspend fun updateAllDatabase(): Flow<SupportTeamsState> =
        executeUpdateSteps(
            steps = listOf(updateTableSupportTeams(sizeUpdate())),
            getState = { _uiState.value },
            getStatus = { it.status },
            copyStateWithStatus = { state, status -> state.copy(status = status) },
            classAndMethod = getClassAndMethod(),
        )

}