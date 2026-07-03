package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.ListInvolved
import br.com.usinasantafe.cav.domain.usecases.card.ListWitness
import br.com.usinasantafe.cav.lib.TypePeople
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

data class InvolvedWitnessState(
    val involvedList: List<ItemListScreenModel> = emptyList(),
    val witnessList: List<ItemListScreenModel> = emptyList(),
    val idSelection: Int = 0,
    val flagDialogCheck: Boolean = false,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<InvolvedWitnessState> {

    override fun copyWithStatus(status: UiStatusState): InvolvedWitnessState =
        copy(status = status)

}

@HiltViewModel
class InvolvedWitnessViewModel @Inject constructor(
    private val listInvolved: ListInvolved,
    private val listWitness: ListWitness,
) : ViewModel() {

    private val _uiState = MutableStateFlow(InvolvedWitnessState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: InvolvedWitnessState.() -> InvolvedWitnessState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false)) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    fun onSelectionDelete(id: Int, typePeople: TypePeople) = updateState { copy(flagDialogCheck = true, idSelection = id) }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val involvedList = listInvolved().getOrThrow()
            val witnessList = listWitness().getOrThrow()
            InvolvedWitnessState(
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