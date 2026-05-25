package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.Errors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DataVehicleOwnState(
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.EXCEPTION,
)

@HiltViewModel
class DataVehicleOwnViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(DataVehicleOwnState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: DataVehicleOwnState.() -> DataVehicleOwnState) {
        _uiState.update(block)
    }
    
    fun onCloseDialog() = updateState { copy(flagDialog = false) }

    
    
}