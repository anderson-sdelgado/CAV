package br.com.usinasantafe.cav.presenter.view.card.involved.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StateVehicleInvolvedState(
    val idSelection: Int = 1,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<StateVehicleInvolvedState> {

    override fun copyWithStatus(status: UiStatusState): StateVehicleInvolvedState =
        copy(status = status)

}

@HiltViewModel
class StateVehicleInvolvedViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(StateVehicleInvolvedState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: StateVehicleInvolvedState.() -> StateVehicleInvolvedState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onSelection(id: Int) = updateState { copy(idSelection = id) }

    fun recoverData() = viewModelScope.launch {

    }

    fun set() = viewModelScope.launch {

    }

}