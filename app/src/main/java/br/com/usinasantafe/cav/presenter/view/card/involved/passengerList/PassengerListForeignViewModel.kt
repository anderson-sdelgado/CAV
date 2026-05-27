package br.com.usinasantafe.cav.presenter.view.card.involved.passengerList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PassengerListState(
    val option: Option = Option.INSERT,
    val list: List<ItemListScreenModel> = emptyList(),
    val idSelection: Int = 0,
    val flagDialogCheck: Boolean = false,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<PassengerListState> {

    override fun copyWithStatus(status: UiStatusState): PassengerListState =
        copy(status = status)

}

@HiltViewModel
class PassengerListViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(PassengerListState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: PassengerListState.() -> PassengerListState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    fun onSelectionDelete(id: Int) = updateState { copy(flagDialogCheck = true, idSelection = id) }

    fun recoverData() = viewModelScope.launch {

    }

    fun delete() = viewModelScope.launch {

    }
}