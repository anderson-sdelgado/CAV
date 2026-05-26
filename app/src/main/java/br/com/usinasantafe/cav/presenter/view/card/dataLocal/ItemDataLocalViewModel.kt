package br.com.usinasantafe.cav.presenter.view.card.dataLocal

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.ListItemDataLocal
import br.com.usinasantafe.cav.domain.usecases.card.SetDataLocalList
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableItemDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableOptionDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableDataLocal
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.UiStateWithStatusUpdate
import br.com.usinasantafe.cav.utils.UiStatusStateUpdate
import br.com.usinasantafe.cav.utils.executeUpdateSteps
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureUpdate
import br.com.usinasantafe.cav.utils.onSuccessUpdateAccess
import br.com.usinasantafe.cav.utils.sizeUpdate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ItemDataLocalStateUpdate(
    val id: Int = 0,
    override val status: UiStatusStateUpdate = UiStatusStateUpdate()
) : UiStateWithStatusUpdate<ItemDataLocalStateUpdate> {

    override fun copyWithStatus(status: UiStatusStateUpdate): ItemDataLocalStateUpdate =
        copy(status = status)

}

@HiltViewModel
class ItemDataLocalViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private val listItemDataLocal: ListItemDataLocal,
    private val updateTableItemDataLocal: UpdateTableItemDataLocal,
    private val updateTableOptionDataLocal: UpdateTableOptionDataLocal,
    private val updateTableDataLocal: UpdateTableDataLocal,
    private val setDataLocalList: SetDataLocalList
) : ViewModel() {

    private val id: Int = saveStateHandle[ID_MAIN_ARG]!!

    val list = mutableStateListOf<ItemCheckBoxScreenModel>()

    private val _uiState = MutableStateFlow(ItemDataLocalStateUpdate())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: ItemDataLocalStateUpdate.() -> ItemDataLocalStateUpdate) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init { updateState { copy(id = this@ItemDataLocalViewModel.id) } }

    fun onCheckChange(id: Int, checked: Boolean) {
        val index = list.indexOfFirst { it.id == id }
        if (index != -1) {
            list[index] = list[index].copy(flag = checked)
        }
    }

    fun list() = viewModelScope.launch {
        runCatching {
            listItemDataLocal(state.id).getOrThrow()
        }
            .onSuccess {
                list.clear()
                list.addAll(it)
            }
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    fun save() = viewModelScope.launch {
        runCatching {
            setDataLocalList(id, list.toList()).getOrThrow()
        }
            .onSuccessUpdateAccess(::updateState)
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

    suspend fun updateAllDatabase(): Flow<ItemDataLocalStateUpdate> =
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