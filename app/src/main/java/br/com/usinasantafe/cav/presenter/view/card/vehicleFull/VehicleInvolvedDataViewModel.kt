package br.com.usinasantafe.cav.presenter.view.card.vehicleFull

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

data class VehicleInvolvedDataState(
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
) : ViewModel() {

    private val _uiState = MutableStateFlow(VehicleInvolvedDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: VehicleInvolvedDataState.() -> VehicleInvolvedDataState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun recoverData() = viewModelScope.launch {

    }


}