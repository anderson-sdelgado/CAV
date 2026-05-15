package br.com.usinasantafe.cav.presenter.view.card.dataLocal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.ListOptionDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableItemDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableOptionDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableROptionItemDataLocal
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
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
import kotlin.onSuccess

data class OptionDataLocalState(
    val list: List<ItemListScreenModel> = emptyList(),
    val flagAccess: Boolean = false,
    override val status: UpdateStatusState = UpdateStatusState()
) : UiStateWithStatus<OptionDataLocalState> {

    override fun copyWithStatus(status: UpdateStatusState): OptionDataLocalState =
        copy(status = status)

}

@HiltViewModel
class OptionDataLocalViewModel @Inject constructor(
    private val listOptionDataLocal: ListOptionDataLocal,
    private val updateTableItemDataLocal: UpdateTableItemDataLocal,
    private val updateTableOptionDataLocal: UpdateTableOptionDataLocal,
    private val updateTableROptionItemDataLocal: UpdateTableROptionItemDataLocal,
) : ViewModel() {

    private val _uiState = MutableStateFlow(OptionDataLocalState())
    val uiState = _uiState.asStateFlow()

    private fun updateState(block: OptionDataLocalState.() -> OptionDataLocalState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun list() = viewModelScope.launch {
        runCatching {
            listOptionDataLocal().getOrThrow()
        }
        .onSuccess { updateState { copy(list = it) } }
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

    suspend fun updateAllDatabase(): Flow<OptionDataLocalState> =
        executeUpdateSteps(
            steps = listUpdate(),
            getState = { _uiState.value },
            getStatus = { it.status },
            copyStateWithStatus = { state, status -> state.copy(status = status) },
            classAndMethod = getClassAndMethod(),
        )

    suspend fun listUpdate() : List<Flow<UpdateStatusState>> {
        val sizeAll = sizeUpdate(3f)
        val list = mutableListOf<Flow<UpdateStatusState>>()
        var count = 0f
        list.add(updateTableItemDataLocal(sizeAll, ++count))
        list.add(updateTableOptionDataLocal(sizeAll, ++count))
        list.add(updateTableROptionItemDataLocal(sizeAll, ++count))
        return list
    }
}