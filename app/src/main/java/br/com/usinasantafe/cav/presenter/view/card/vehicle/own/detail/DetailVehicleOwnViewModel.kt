package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeDetailOwn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailVehicleOwnState(
    val option: Option = Option.INSERT,
    val typeDetailOwn: TypeDetailOwn = TypeDetailOwn.EQUIP,
    val text: String = "",
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.EXCEPTION,
)

@HiltViewModel
class DetailVehicleOwnViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailVehicleOwnState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: DetailVehicleOwnState.() -> DetailVehicleOwnState) {
        _uiState.update(block)
    }
    
    fun setCloseDialog() = updateState { copy(flagDialog = false) }

    fun onTextChanged(detail: String) {
        _uiState.update {
            it.copy(text = detail)
        }
    }

    fun set() = viewModelScope.launch {

    }
}