package br.com.usinasantafe.cav.presenter.view.card.vehicleFull

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetDescDriver
import br.com.usinasantafe.cav.domain.usecases.card.GetDescPassengers
import br.com.usinasantafe.cav.domain.usecases.card.GetDescVehicle
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
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

data class VehicleInvolvedDataState(
    val idMain: Int = 0,
    val vehicle: String = "",
    val driver: String = "",
    val passengers: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<VehicleInvolvedDataState> {

    override fun copyWithStatus(status: UiStatusState): VehicleInvolvedDataState =
        copy(status = status)

}

@HiltViewModel
class VehicleInvolvedDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDescVehicle: GetDescVehicle,
    private val getDescDriver: GetDescDriver,
    private val getDescPassengers: GetDescPassengers
) : ViewModel() {

    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!

    private val _uiState = MutableStateFlow(VehicleInvolvedDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: VehicleInvolvedDataState.() -> VehicleInvolvedDataState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                idMain = this@VehicleInvolvedDataViewModel.idMain
            )
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val vehicle = getDescVehicle(state.idMain).getOrThrow()
            val driver = getDescDriver(FlowNote.DRIVER, state.idMain).getOrThrow()
            val passengers = getDescPassengers(FlowNote.PASSENGER_INVOLVED, state.idMain).getOrThrow()
            VehicleInvolvedDataState(
                vehicle = vehicle,
                driver = driver,
                passengers = passengers
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