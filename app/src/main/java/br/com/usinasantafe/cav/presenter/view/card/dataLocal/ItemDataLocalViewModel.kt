package br.com.usinasantafe.cav.presenter.view.card.dataLocal

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.ListItemDataLocal
import br.com.usinasantafe.cav.domain.usecases.card.SetDataLocalList
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableItemDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableOptionDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableROptionItemDataLocal
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.Args.ID_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
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

data class ItemDataLocalState(
    val id: Int = 0,
    val flagAccess: Boolean = false,
    override val status: UpdateStatusState = UpdateStatusState()
) : UiStateWithStatus<ItemDataLocalState> {

    override fun copyWithStatus(status: UpdateStatusState): ItemDataLocalState =
        copy(status = status)

}

@HiltViewModel
class ItemDataLocalViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private val listItemDataLocal: ListItemDataLocal,
    private val updateTableItemDataLocal: UpdateTableItemDataLocal,
    private val updateTableOptionDataLocal: UpdateTableOptionDataLocal,
    private val updateTableROptionItemDataLocal: UpdateTableROptionItemDataLocal,
    private val setDataLocalList: SetDataLocalList
) : ViewModel() {

    private val id: Int = saveStateHandle[ID_ARG]!!

    val list = mutableStateListOf<ItemCheckBoxScreenModel>()

    private val _uiState = MutableStateFlow(ItemDataLocalState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: ItemDataLocalState.() -> ItemDataLocalState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

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

    suspend fun updateAllDatabase(): Flow<ItemDataLocalState> =
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