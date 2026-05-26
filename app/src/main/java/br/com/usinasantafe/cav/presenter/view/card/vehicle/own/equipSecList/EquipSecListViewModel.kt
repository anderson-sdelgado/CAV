package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.equipSecList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.DeleteEquipSec
import br.com.usinasantafe.cav.domain.usecases.card.ListEquipSec
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
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

data class EquipSecListState(
    val option: Option = Option.INSERT,
    val list: List<ItemListScreenModel> = emptyList(),
    val idSelection: Int = 0,
    val flagDialogCheck: Boolean = false,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<EquipSecListState> {

    override fun copyWithStatus(status: UiStatusState): EquipSecListState =
        copy(status = status)

}

@HiltViewModel
class EquipSecListViewModel @Inject constructor(
    private val listEquipSec: ListEquipSec,
    private val deleteEquipSec: DeleteEquipSec
) : ViewModel() {

    private val _uiState = MutableStateFlow(EquipSecListState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: EquipSecListState.() -> EquipSecListState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    fun onSelectionDelete(id: Int) = updateState { copy(flagDialogCheck = true, idSelection = id) }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            listEquipSec().getOrThrow()
        }
            .onSuccess { updateState { copy(list = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun delete() = viewModelScope.launch {
        runCatching {
            deleteEquipSec(state.idSelection).getOrThrow()
        }
            .onSuccess { recoverData() }
            .onFailureState(getClassAndMethod(), ::updateState)
    }
}