package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetDriver
import br.com.usinasantafe.cav.domain.usecases.card.GetEquip
import br.com.usinasantafe.cav.domain.usecases.card.GetEquipSec
import br.com.usinasantafe.cav.domain.usecases.card.GetPassengers
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
import kotlin.onSuccess

data class DataVehicleOwnState(
    val idMain: Int = 0,
    val equip: String = "",
    val equipSec: String = "",
    val driver: String = "",
    val passengers: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<DataVehicleOwnState> {

    override fun copyWithStatus(status: UiStatusState): DataVehicleOwnState =
        copy(status = status)

}

@HiltViewModel
class DataVehicleOwnViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private val getEquip: GetEquip,
    private val getEquipSec: GetEquipSec,
    private val getDriver: GetDriver,
    private val getPassengers: GetPassengers
) : ViewModel() {

    private val idMain: Int = saveStateHandle[ID_MAIN_ARG]!!

    private val _uiState = MutableStateFlow(DataVehicleOwnState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: DataVehicleOwnState.() -> DataVehicleOwnState) {
        _uiState.update(block)
    }
    
    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false)) }

    init {
        updateState {
            copy(
                idMain = this@DataVehicleOwnViewModel.idMain
            )
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val equip = getEquip(state.idMain).getOrThrow()
            val equipSec = getEquipSec(state.idMain).getOrThrow()
            val driver = getDriver(state.idMain).getOrThrow()
            val passengers = getPassengers(state.idMain).getOrThrow()
            DataVehicleOwnState(
                idMain = state.idMain,
                equip = equip,
                equipSec = equipSec,
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