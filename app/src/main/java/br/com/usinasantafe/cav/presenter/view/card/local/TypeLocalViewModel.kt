package br.com.usinasantafe.cav.presenter.view.card.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.SetLocal
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.handleFailure
import br.com.usinasantafe.cav.utils.onFailureHandled
import br.com.usinasantafe.cav.utils.withFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TypeLocalState(
    val address: String = "",
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.FIELD_EMPTY,
)

@HiltViewModel
class TypeLocalViewModel @Inject constructor(
    private val setLocal: SetLocal
) : ViewModel() {

    private val _uiState = MutableStateFlow(TypeLocalState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: TypeLocalState.() -> TypeLocalState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(flagDialog = false) }

    fun onAddressChanged(address: String) {
        _uiState.update {
            it.copy(address = address)
        }
    }

    fun set() =
        viewModelScope.launch {
            runCatching {
                if (state.address.isBlank()) {
                    handleFailure(getClassAndMethod(), Errors.FIELD_EMPTY, ::onError)
                    return@launch
                }
                setLocal(address = state.address).getOrThrow()
            }
                .onSuccess { updateState { copy(flagAccess = true, flagDialog = false) } }
                .onFailureHandled(getClassAndMethod(), ::onError)
        }

    private fun onError(failure: String, errors: Errors = Errors.EXCEPTION) = updateState { copy(flagDialog = true, failure = failure, errors = errors) }

}