package br.com.usinasantafe.cav.presenter.view.card.vehicleFull

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.DeleteVehicleOwn
import br.com.usinasantafe.cav.domain.usecases.card.GetDescColab
import br.com.usinasantafe.cav.domain.usecases.card.GetDescEquip
import br.com.usinasantafe.cav.domain.usecases.card.GetDescEquipSec
import br.com.usinasantafe.cav.domain.usecases.card.GetDescPassengers
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.TypeVehicle
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
import kotlin.onSuccess

data class VehicleOwnDataState(
    val idMain: Int = 0,
    val equip: String = "",
    val equipSec: String = "",
    val driver: String = "",
    val passengers: String = "",
    val flagDialogCheck: Boolean = false,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<VehicleOwnDataState> {

    override fun copyWithStatus(status: UiStatusState): VehicleOwnDataState =
        copy(status = status)

}

@HiltViewModel
class VehicleOwnDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDescEquip: GetDescEquip,
    private val getDescEquipSec: GetDescEquipSec,
    private val getDescColab: GetDescColab,
    private val getDescPassengers: GetDescPassengers,
    private val deleteVehicleOwn: DeleteVehicleOwn
) : ViewModel() {

    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!

    private val _uiState = MutableStateFlow(VehicleOwnDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: VehicleOwnDataState.() -> VehicleOwnDataState) {
        _uiState.update(block)
    }
    
    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false)) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    init {
        updateState {
            copy(
                idMain = this@VehicleOwnDataViewModel.idMain
            )
        }
    }

    fun recoverData() = viewModelScope.launch {

        data class RecoverVehicleOwn(
            val equip: String,
            val equipSec: String,
            val driver: String,
            val passengers: String
        )

        runCatching {
            RecoverVehicleOwn(
                equip = getDescEquip(FlowNote.EQUIP, state.idMain).getOrThrow(),
                equipSec = getDescEquipSec(state.idMain).getOrThrow(),
                driver = getDescColab(FlowNote.COLAB, state.idMain).getOrThrow(),
                passengers = getDescPassengers(FlowNote.PASSENGER_COLAB, state.idMain).getOrThrow()
            )

        }
            .onSuccess {
                updateState {
                    copy(
                        equip = it.equip,
                        equipSec = it.equipSec,
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
            deleteVehicleOwn(state.idMain).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }
}