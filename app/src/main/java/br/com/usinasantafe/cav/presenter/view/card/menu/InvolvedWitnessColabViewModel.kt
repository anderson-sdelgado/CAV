package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.ListInvolvedColab
import br.com.usinasantafe.cav.domain.usecases.card.ListWitnessColab
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class InvolvedWitnessColabState(
    val involvedList: List<ItemListScreenModel> = emptyList(),
    val witnessList: List<ItemListScreenModel> = emptyList(),
    val idSelection: Int = 0,
    val flagDialogCheck: Boolean = false,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<InvolvedWitnessColabState> {

    override fun copyWithStatus(status: UiStatusState): InvolvedWitnessColabState =
        copy(status = status)

}

@HiltViewModel
class InvolvedWitnessColabViewModel @Inject constructor(
    private val listInvolvedColab: ListInvolvedColab,
    private val listWitnessColab: ListWitnessColab,
) : ViewModel() {

    private val _uiState = MutableStateFlow(InvolvedWitnessColabState())
    val uiState = _uiState.asStateFlow()

    private fun updateState(block: InvolvedWitnessColabState.() -> InvolvedWitnessColabState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val involvedList = listInvolvedColab().getOrThrow()
            val witnessList = listWitnessColab().getOrThrow()
            InvolvedWitnessColabState(
                involvedList = involvedList,
                witnessList = witnessList
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