package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.CancelCard
import br.com.usinasantafe.cav.domain.usecases.card.GetAttendant
import br.com.usinasantafe.cav.domain.usecases.card.GetCar
import br.com.usinasantafe.cav.domain.usecases.card.GetNature
import br.com.usinasantafe.cav.domain.usecases.card.GetTypeAccident
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureHandled
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.onSuccess

data class DataInitialState(
    val attendant: String = "",
    val car: String = "",
    val nature: String = "",
    val typeAccident: String = "",
    val flagCancel: Boolean = false,
    val flagDialog: Boolean = false,
    val flagDialogCheck: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.EXCEPTION,
)

@HiltViewModel
class DataInitialViewModel @Inject constructor(
    private val getAttendant: GetAttendant,
    private val getCar: GetCar,
    private val getNature: GetNature,
    private val getTypeAccident: GetTypeAccident,
    private val cancelCard: CancelCard
) : ViewModel() {

    private val _uiState = MutableStateFlow(DataInitialState())
    val uiState = _uiState.asStateFlow()

    private fun updateState(block: DataInitialState.() -> DataInitialState) {
        _uiState.update(block)
    }
    
    fun onCloseDialog() = updateState { copy(flagDialog = false) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val attendant = getAttendant().getOrThrow()
            val car = getCar().getOrThrow()
            val nature = getNature().getOrThrow()
            val typeAccident = getTypeAccident().getOrThrow()
            DataInitialState(
                attendant = attendant,
                car = car,
                nature = nature,
                typeAccident = typeAccident
            )
        }
            .onSuccess { newState ->
                updateState {
                    newState.copy(flagFailure = false)
                }
            }
            .onFailureHandled(getClassAndMethod(), ::onError)
    }

    fun cancel() = viewModelScope.launch {
        runCatching {
            onDialogCheck(false)
            cancelCard().getOrThrow()
        }
            .onSuccess { updateState { copy(flagCancel = true) } }
            .onFailureHandled(getClassAndMethod(), ::onError)
    }

    private fun onError(failure: String) = updateState { copy(flagDialog = true, failure = failure, flagFailure = true) }

}