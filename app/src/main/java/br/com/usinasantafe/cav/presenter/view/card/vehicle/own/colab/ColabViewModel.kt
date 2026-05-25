package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.colab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UpdateStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ColabState(
    val option: Option = Option.INSERT,
    val type: Type = Type.MAIN,
    val flagAccess: Boolean = false,
    val regColab: String = "",
    override val status: UpdateStatusState = UpdateStatusState()
) : UiStateWithStatus<ColabState> {

    override fun copyWithStatus(status: UpdateStatusState): ColabState =
        copy(status = status)

}

@HiltViewModel
class ColabViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(ColabState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: ColabState.() -> ColabState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun setTextField(text: String, typeButton: TypeButton) {

    }

    private fun set() = viewModelScope.launch {

    }
}