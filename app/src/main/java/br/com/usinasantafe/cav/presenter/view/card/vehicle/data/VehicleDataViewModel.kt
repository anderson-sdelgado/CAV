package br.com.usinasantafe.cav.presenter.view.card.vehicle.data

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

data class VehicleDataState(
    val plate: String = "",
    val brand: String = "",
    val detail: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<VehicleDataState> {

    override fun copyWithStatus(status: UiStatusState): VehicleDataState =
        copy(status = status)

}

@HiltViewModel
class VehicleDataViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(VehicleDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: VehicleDataState.() -> VehicleDataState) {
        _uiState.update(block)
    }
    
    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun recoverData() = viewModelScope.launch {

    }

}