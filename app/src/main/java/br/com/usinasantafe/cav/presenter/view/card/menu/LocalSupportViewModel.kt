package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetLocal
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureHandled
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LocalSupportState(
    val address: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val dataLocalList: List<Pair<String, String>> = emptyList(),
    val supportTeams: String = "",
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.EXCEPTION,
)

@HiltViewModel
class LocalSupportViewModel @Inject constructor(
    private val getLocal: GetLocal,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocalSupportState())
    val uiState = _uiState.asStateFlow()

    private fun updateState(block: LocalSupportState.() -> LocalSupportState) {
        _uiState.update(block)
    }
    
    fun setCloseDialog() = updateState { copy(flagDialog = false) }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val local = getLocal().getOrThrow()
            LocalSupportState(
                address = local.address,
                latitude = local.latitude,
                longitude = local.longitude
            )
        }
            .onSuccess { newState ->
                updateState {
                    newState.copy(flagFailure = false)
                }
            }
            .onFailureHandled(getClassAndMethod(), ::onError)
    }

    private fun onError(failure: String) = updateState { copy(flagDialog = true, failure = failure, flagFailure = true) }

}