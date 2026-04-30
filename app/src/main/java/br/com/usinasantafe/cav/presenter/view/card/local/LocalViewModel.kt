package br.com.usinasantafe.cav.presenter.view.card.local

import androidx.lifecycle.ViewModel
import br.com.usinasantafe.cav.domain.usecases.card.SetLocal
import br.com.usinasantafe.cav.lib.Errors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class LocalState(
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.FIELD_EMPTY,
    val flagDialogCheck: Boolean = false,
)

@HiltViewModel
class LocalViewModel @Inject constructor(
    private val setLocal: SetLocal
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocalState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: LocalState.() -> LocalState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(flagDialog = false) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    fun onLocalChanged(address: String, latitude: Double, longitude: Double) {
        updateState { copy(address = address, latitude = latitude, longitude = longitude) }
    }

    fun set() {

    }


}