package br.com.usinasantafe.cav.presenter.view.card.foreign.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.TypePeople
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ForeignDataState(
    val typePeople: TypePeople = TypePeople.DRIVER,
    val document: String = "",
    val name: String = "",
    val state: String = "",
    val phone: String = "",
    val address: String = "",
    val detail: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<ForeignDataState> {

    override fun copyWithStatus(status: UiStatusState): ForeignDataState =
        copy(status = status)

}

@HiltViewModel
class ForeignDataViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForeignDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: ForeignDataState.() -> ForeignDataState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun recoverData() = viewModelScope.launch {

    }

}