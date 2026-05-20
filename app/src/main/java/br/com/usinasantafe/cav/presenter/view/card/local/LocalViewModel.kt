package br.com.usinasantafe.cav.presenter.view.card.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.SetLocal
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureHandled
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LocalState(
    val address: String = "",
    val latitude: Double? = null,
    val longitude: Double? = null,
    val flagDialogCheck: Boolean = false,
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.FIELD_EMPTY,
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

    fun onCloseDialog() = updateState { copy(flagDialog = false) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    fun onLocalChanged(address: String, latitude: Double, longitude: Double) {
        updateState { copy(address = address, latitude = latitude, longitude = longitude) }
    }

    fun set() = viewModelScope.launch {
        runCatching {
            setLocal(address = state.address, latitude = state.latitude, longitude = state.longitude).getOrThrow()
        }
            .onSuccess { updateState { copy(flagAccess = true, flagDialog = false) } }
            .onFailureHandled(getClassAndMethod(), ::onError)
    }

    private fun onError(failure: String, errors: Errors = Errors.EXCEPTION) = updateState { copy(flagDialog = true, failure = failure, errors = errors) }

}