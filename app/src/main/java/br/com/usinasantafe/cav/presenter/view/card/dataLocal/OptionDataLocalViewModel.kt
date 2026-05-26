package br.com.usinasantafe.cav.presenter.view.card.dataLocal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.ListOptionDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableItemDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableOptionDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableDataLocal
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.UiStateWithStatusUpdate
import br.com.usinasantafe.cav.utils.UiStatusStateUpdate
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

data class OptionDataLocalStateUpdate(
    val list: List<ItemListScreenModel> = emptyList(),
    override val status: UiStatusStateUpdate = UiStatusStateUpdate()
) : UiStateWithStatusUpdate<OptionDataLocalStateUpdate> {

    override fun copyWithStatus(status: UiStatusStateUpdate): OptionDataLocalStateUpdate =
        copy(status = status)

}

@HiltViewModel
class OptionDataLocalViewModel @Inject constructor(
    private val listOptionDataLocal: ListOptionDataLocal,
    private val updateTableItemDataLocal: UpdateTableItemDataLocal,
    private val updateTableOptionDataLocal: UpdateTableOptionDataLocal,
    private val updateTableDataLocal: UpdateTableDataLocal,
) : ViewModel() {

    private val _uiState = MutableStateFlow(OptionDataLocalStateUpdate())
    val uiState = _uiState.asStateFlow()

    private fun updateState(block: OptionDataLocalStateUpdate.() -> OptionDataLocalStateUpdate) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

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

    suspend fun updateAllDatabase(): Flow<OptionDataLocalStateUpdate> =
        executeUpdateSteps(
            steps = listUpdate(),
            getState = { _uiState.value },
            getStatus = { it.status },
            copyStateWithStatus = { state, status -> state.copy(status = status) },
            classAndMethod = getClassAndMethod(),
        )

    suspend fun listUpdate() : List<Flow<UiStatusStateUpdate>> {
        val sizeAll = sizeUpdate(3f)
        val list = mutableListOf<Flow<UiStatusStateUpdate>>()
        var count = 0f
        list.add(updateTableItemDataLocal(sizeAll, ++count))
        list.add(updateTableOptionDataLocal(sizeAll, ++count))
        list.add(updateTableDataLocal(sizeAll, ++count))
        return list
    }
}