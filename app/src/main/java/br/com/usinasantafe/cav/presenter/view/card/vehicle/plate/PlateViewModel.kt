package br.com.usinasantafe.cav.presenter.view.card.vehicle.plate

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetPlate
import br.com.usinasantafe.cav.domain.usecases.card.SetPlate
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import br.com.usinasantafe.cav.utils.onSuccessStateAccess
import br.com.usinasantafe.cav.utils.withFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlateState(
    val option: Option = Option.INSERT,
    val idMain: Int = 0,
    val text: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<PlateState> {

    override fun copyWithStatus(status: UiStatusState): PlateState =
        copy(status = status)

}

@HiltViewModel
class PlateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPlate: GetPlate,
    private val setPlate: SetPlate
) : ViewModel() {

    private val option: Int = savedStateHandle[OPTION_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!

    private val _uiState = MutableStateFlow(PlateState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: PlateState.() -> PlateState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                option = Option.entries[this@PlateViewModel.option],
                idMain = this@PlateViewModel.idMain,
            )
        }
    }

    fun onTextChanged(text: String) {
        if (text.length <= 7) {
            _uiState.update {
                it.copy(text = text)
            }
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            getPlate(state.option, idMain).getOrThrow()
        }
            .onSuccess { updateState { copy(text = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun set() = viewModelScope.launch {
        runCatching {
            if (state.text.isBlank()) {
                updateState { withFailure(getClassAndMethod(), Errors.FIELD_EMPTY) }
                return@launch
            }
            if (state.text.length < 7) {
                updateState { withFailure(getClassAndMethod(), Errors.INVALID) }
                return@launch
            }
            setPlate(state.text, state.option, idMain).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}