package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.equipSecList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
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
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.EXCEPTION,
)

@HiltViewModel
class EquipSecListViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(EquipSecListState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: EquipSecListState.() -> EquipSecListState) {
        _uiState.update(block)
    }
    
    fun onCloseDialog() = updateState { copy(flagDialog = false) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    fun onCheckDelete(id: Int) = updateState { copy(flagDialogCheck = true, idSelection = id) }

    fun delete() = viewModelScope.launch {

    }
}