package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.ListVehicleInvolved
import br.com.usinasantafe.cav.domain.usecases.card.ListVehicleOwn
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
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

data class VehicleFullState(
    val vehicleOwnList: List<VehicleScreenModel> = emptyList(),
    val vehicleInvolvedList: List<VehicleScreenModel> = emptyList(),
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<VehicleFullState> {

    override fun copyWithStatus(status: UiStatusState): VehicleFullState =
        copy(status = status)

}

@HiltViewModel
class VehicleFullViewModel @Inject constructor(
    private val listVehicleOwn: ListVehicleOwn,
    private val listVehicleInvolved: ListVehicleInvolved,
) : ViewModel() {

    private val _uiState = MutableStateFlow(VehicleFullState())
    val uiState = _uiState.asStateFlow()

    private fun updateState(block: VehicleFullState.() -> VehicleFullState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false)) }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val vehicleOwnList = listVehicleOwn().getOrThrow()
            val vehicleInvolvedList = listVehicleInvolved().getOrThrow()
            VehicleFullState(
                vehicleOwnList = vehicleOwnList,
                vehicleInvolvedList = vehicleInvolvedList
            )
        }
            .onSuccess { newState ->
                updateState {
                    newState.copy(status = status.copy(flagFailure = false))
                }
            }
            .onFailureState(getClassAndMethod(), ::updateState)
    }


}