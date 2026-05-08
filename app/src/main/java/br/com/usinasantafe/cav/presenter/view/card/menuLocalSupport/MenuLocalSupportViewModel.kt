package br.com.usinasantafe.cav.presenter.view.card.menuLocalSupport

import androidx.lifecycle.ViewModel
import br.com.usinasantafe.cav.lib.Errors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MenuLocalSupportState(
    val local: String = "",
    val dataLocal: String = "",
    val supportTeams: String = "",
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.FIELD_EMPTY,
)

@HiltViewModel
class MenuLocalSupportViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(MenuLocalSupportState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: MenuLocalSupportState.() -> MenuLocalSupportState) {
        _uiState.update(block)
    }
    
    fun setCloseDialog() = updateState { copy(flagDialog = false) }

    
    
}