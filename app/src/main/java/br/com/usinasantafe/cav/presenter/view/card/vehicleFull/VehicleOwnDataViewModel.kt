package br.com.usinasantafe.cav.presenter.view.card.vehicleFull

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetDescColab
import br.com.usinasantafe.cav.domain.usecases.card.GetDescEquip
import br.com.usinasantafe.cav.domain.usecases.card.GetDescEquipSec
import br.com.usinasantafe.cav.domain.usecases.card.GetDescPassengers
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
import kotlin.onSuccess

data class VehicleOwnDataState(
    val idMain: Int = 0,
    val equip: String = "",
    val equipSec: String = "",
    val driver: String = "",
    val passengers: String = "",
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
    private val getDescPassengers: GetDescPassengers
) : ViewModel() {

    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!

    private val _uiState = MutableStateFlow(VehicleOwnDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: VehicleOwnDataState.() -> VehicleOwnDataState) {
        _uiState.update(block)
    }
    
    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false)) }

    init {
        updateState {
            copy(
                idMain = this@VehicleOwnDataViewModel.idMain
            )
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val equip = getDescEquip(FlowNote.EQUIP, state.idMain).getOrThrow()
            val equipSec = getDescEquipSec(state.idMain).getOrThrow()
            val driver = getDescColab(FlowNote.COLAB, state.idMain).getOrThrow()
            val passengers = getDescPassengers(FlowNote.PASSENGER_COLAB, state.idMain).getOrThrow()
            VehicleOwnDataState(
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