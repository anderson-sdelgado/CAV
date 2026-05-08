package br.com.usinasantafe.cav.presenter.view.card.menuDataInitial

import androidx.lifecycle.ViewModel
import br.com.usinasantafe.cav.lib.Errors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MenuDataInitialState(
    val attendant: String = "",
    val car: String = "",
    val local: String = "",
    val nature: String = "",
    val typeAccident: String = "",
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.FIELD_EMPTY,
)

@HiltViewModel
class MenuDataInitialViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(MenuDataInitialState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: MenuDataInitialState.() -> MenuDataInitialState) {
        _uiState.update(block)
    }
    
    fun setCloseDialog() = updateState { copy(flagDialog = false) }


}