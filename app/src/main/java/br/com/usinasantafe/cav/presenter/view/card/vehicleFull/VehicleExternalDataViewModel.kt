package br.com.usinasantafe.cav.presenter.view.card.vehicleFull

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.DeleteVehicleInvolved
import br.com.usinasantafe.cav.domain.usecases.card.GetDescDriver
import br.com.usinasantafe.cav.domain.usecases.card.GetDescPassengers
import br.com.usinasantafe.cav.domain.usecases.card.GetDescVehicle
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import br.com.usinasantafe.cav.utils.onSuccessStateAccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class VehicleExternalDataState(
    val idMain: Int = 0,
    val vehicle: String = "",
    val driver: String = "",
    val passengers: String = "",
    val flagDialogCheck: Boolean = false,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<VehicleExternalDataState> {

    override fun copyWithStatus(status: UiStatusState): VehicleExternalDataState =
        copy(status = status)

}

@HiltViewModel
class VehicleExternalDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDescVehicle: GetDescVehicle,
    private val getDescDriver: GetDescDriver,
    private val getDescPassengers: GetDescPassengers,
    private val deleteVehicleInvolved: DeleteVehicleInvolved,
) : ViewModel() {

    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!

    private val _uiState = MutableStateFlow(VehicleExternalDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: VehicleExternalDataState.() -> VehicleExternalDataState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    init {
        updateState {
            copy(
                idMain = this@VehicleExternalDataViewModel.idMain
            )
        }
    }

    fun recoverData() = viewModelScope.launch {

        data class RecoverVehicleInvolved(
            val vehicle: String,
            val driver: String,
            val passengers: String
        )

        runCatching {
            RecoverVehicleInvolved(
                vehicle = getDescVehicle(state.idMain).getOrThrow(),
                driver = getDescDriver(FlowNote.DRIVER, state.idMain).getOrThrow(),
                passengers = getDescPassengers(FlowNote.PASSENGER_EXTERNAL, state.idMain).getOrThrow()
            )
        }
            .onSuccess {
                updateState {
                    copy(
                        vehicle = it.vehicle,
                        driver = it.driver,
                        passengers = it.passengers,
                        status = status.copy(flagFailure = false)
                    )
                }
            }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun delete() = viewModelScope.launch {
        runCatching {
            deleteVehicleInvolved(state.idMain).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }
}