package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.lifecycle.ViewModel
import br.com.usinasantafe.cav.domain.usecases.card.GetLocal
import br.com.usinasantafe.cav.lib.Errors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
    val errors: Errors = Errors.FIELD_EMPTY,
)

@HiltViewModel
class LocalSupportViewModel @Inject constructor(
    private val getLocal: GetLocal,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocalSupportState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: LocalSupportState.() -> LocalSupportState) {
        _uiState.update(block)
    }
    
    fun setCloseDialog() = updateState { copy(flagDialog = false) }

    
    
}