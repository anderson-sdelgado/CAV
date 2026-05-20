package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.equip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UpdateStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EquipState(
    val option: Option = Option.INSERT,
    val nroEquip: String = "",
    val flagAccess: Boolean = false,
    override val status: UpdateStatusState = UpdateStatusState()
) : UiStateWithStatus<EquipState> {

    override fun copyWithStatus(status: UpdateStatusState): EquipState =
        copy(status = status)

}

@HiltViewModel
class EquipViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(EquipState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: EquipState.() -> EquipState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun setTextField(text: String, typeButton: TypeButton) {

    }

    private fun set() = viewModelScope.launch {

    }
}