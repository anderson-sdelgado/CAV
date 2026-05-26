package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetLocal
import br.com.usinasantafe.cav.domain.usecases.card.GetSupportTeams
import br.com.usinasantafe.cav.domain.usecases.card.ListDataLocal
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.presenter.view.card.local.InputLocalState
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureHandled
import br.com.usinasantafe.cav.utils.onFailureState
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
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<LocalSupportState> {

    override fun copyWithStatus(status: UiStatusState): LocalSupportState =
        copy(status = status)

}

@HiltViewModel
class LocalSupportViewModel @Inject constructor(
    private val getLocal: GetLocal,
    private val listDataLocal: ListDataLocal,
    private val getSupportTeams: GetSupportTeams
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocalSupportState())
    val uiState = _uiState.asStateFlow()

    private fun updateState(block: LocalSupportState.() -> LocalSupportState) {
        _uiState.update(block)
    }
    
    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val local = getLocal().getOrThrow()
            val dataLocalList = listDataLocal().getOrThrow()
            val supportTeams = getSupportTeams().getOrThrow()
            LocalSupportState(
                address = local.address,
                latitude = local.latitude,
                longitude = local.longitude,
                dataLocalList = dataLocalList,
                supportTeams = supportTeams
            )
        }
            .onSuccess { newState ->
                updateState {
                    newState.copy(status = status.copy(flagFailure = false))
                }
            }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}