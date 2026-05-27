package br.com.usinasantafe.cav.presenter.view.card.colab.data

import androidx.lifecycle.ViewModel
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ColabDataState(
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<ColabDataState> {

    override fun copyWithStatus(status: UiStatusState): ColabDataState =
        copy(status = status)

}

@HiltViewModel
class ColabDataViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(ColabDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: ColabDataState.() -> ColabDataState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }



}