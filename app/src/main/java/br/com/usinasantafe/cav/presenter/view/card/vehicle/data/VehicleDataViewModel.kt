package br.com.usinasantafe.cav.presenter.view.card.vehicle.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetBrand
import br.com.usinasantafe.cav.domain.usecases.card.GetDetail
import br.com.usinasantafe.cav.domain.usecases.card.GetPlate
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
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

data class VehicleDataState(
    val idMain: Int = 0,
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
    savedStateHandle: SavedStateHandle,
    private val getPlate: GetPlate,
    private val getBrand: GetBrand,
    private val getDetail: GetDetail
) : ViewModel() {

    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!

    private val _uiState = MutableStateFlow(VehicleDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: VehicleDataState.() -> VehicleDataState) {
        _uiState.update(block)
    }
    
    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                idMain = this@VehicleDataViewModel.idMain
            )
        }
    }

    fun recoverData() = viewModelScope.launch {

        data class RecoverVehicle(
            val plate: String,
            val brand: String,
            val detail: String
        )

        runCatching {
            RecoverVehicle(
                plate = getPlate(Option.EDIT, state.idMain).getOrThrow(),
                brand = getBrand(Option.EDIT, state.idMain).getOrThrow(),
                detail = getDetail(Option.EDIT, FlowNote.VEHICLE, state.idMain, 0).getOrThrow()
            )
        }
            .onSuccess {
                updateState {
                    copy(
                        plate = it.plate,
                        brand = it.brand,
                        detail = it.detail,
                        status = status.copy(flagFailure = false)
                    )
                }
            }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}