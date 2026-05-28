package br.com.usinasantafe.cav.presenter.view.card.colab.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ColabDataState(
    val type: Type = Type.MAIN,
    val colab: String = "",
    val state: String = "",
    val detail: String = "",
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

    fun recoverData() = viewModelScope.launch {

    }

}