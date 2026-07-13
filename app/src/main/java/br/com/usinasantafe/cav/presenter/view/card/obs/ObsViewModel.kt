package br.com.usinasantafe.cav.presenter.view.card.obs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetObs
import br.com.usinasantafe.cav.domain.usecases.card.SetObs
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import br.com.usinasantafe.cav.utils.onSuccessStateAccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ObsState(
    val text: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<ObsState> {

    override fun copyWithStatus(status: UiStatusState): ObsState =
        copy(status = status)

}

@HiltViewModel
class ObsViewModel @Inject constructor(
    private val getObs: GetObs,
    private val setObs: SetObs,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ObsState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: ObsState.() -> ObsState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onTextChanged(text: String) {
        _uiState.update {
            it.copy(text = text)
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            getObs().getOrThrow()
        }
            .onSuccess { updateState { copy(text = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun set() = viewModelScope.launch {
        runCatching {
            setObs(state.text).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}