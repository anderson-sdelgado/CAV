package br.com.usinasantafe.cav.presenter.view.card.vehicle.plate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlateState(
    val option: Option = Option.INSERT,
    val text: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<PlateState> {

    override fun copyWithStatus(status: UiStatusState): PlateState =
        copy(status = status)

}

@HiltViewModel
class PlateViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlateState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: PlateState.() -> PlateState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onTextChanged(text: String) {
        if (text.length <= 7) {
            _uiState.update {
                it.copy(text = text)
            }
        }
    }

    fun recoverData() = viewModelScope.launch {

    }

    fun set() = viewModelScope.launch {

    }

}