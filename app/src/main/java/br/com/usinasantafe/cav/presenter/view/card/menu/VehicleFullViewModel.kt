package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.lifecycle.ViewModel
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class VehicleFullState(
    val vehicleOwnList: List<VehicleScreenModel> = emptyList(),
    val vehicleForeignList: List<VehicleScreenModel> = emptyList(),
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.FIELD_EMPTY,
)

@HiltViewModel
class VehicleFullViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(VehicleFullState())
    val uiState = _uiState.asStateFlow()

    private fun updateState(block: VehicleFullState.() -> VehicleFullState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(flagDialog = false) }

}