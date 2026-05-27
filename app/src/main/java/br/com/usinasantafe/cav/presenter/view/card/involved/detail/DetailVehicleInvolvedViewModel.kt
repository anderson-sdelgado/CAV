package br.com.usinasantafe.cav.presenter.view.card.involved.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeDetail
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailVehicleForeignState(
    val option: Option = Option.INSERT,
    val typeDetail: TypeDetail = TypeDetail.VEHICLE,
    val text: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<DetailVehicleForeignState> {

    override fun copyWithStatus(status: UiStatusState): DetailVehicleForeignState =
        copy(status = status)

}

@HiltViewModel
class DetailVehicleForeignViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailVehicleForeignState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: DetailVehicleForeignState.() -> DetailVehicleForeignState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onTextChanged(text: String) {
        _uiState.update {
            it.copy(text = text)
        }
    }

    fun recoverData() = viewModelScope.launch {

    }

    fun set() = viewModelScope.launch {

    }

}