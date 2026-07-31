package br.com.usinasantafe.cav.presenter.view.card.breathalyzer.check

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetRealizedBreathalyzer
import br.com.usinasantafe.cav.domain.usecases.card.GetResultBreathalyzer
import br.com.usinasantafe.cav.domain.usecases.card.SetDataInitialBreathalyzer
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
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

data class CheckBreathalyzerState(
    val option: Option = Option.INSERT,
    val idMain: Int = 0,
    val flagRealized: Boolean? = null,
    val flagResult: Boolean? = null,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<CheckBreathalyzerState> {

    override fun copyWithStatus(status: UiStatusState): CheckBreathalyzerState =
        copy(status = status)

}

@HiltViewModel
class CheckBreathalyzerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRealizedBreathalyzer: GetRealizedBreathalyzer,
    private val getResultBreathalyzer: GetResultBreathalyzer,
    private val setDataInitialBreathalyzer: SetDataInitialBreathalyzer
) : ViewModel() {

    private val option: Int = savedStateHandle[OPTION_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!

    private val _uiState = MutableStateFlow(CheckBreathalyzerState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: CheckBreathalyzerState.() -> CheckBreathalyzerState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                option = Option.entries[this@CheckBreathalyzerViewModel.option],
                idMain = this@CheckBreathalyzerViewModel.idMain,
            )
        }
    }

    fun onChangeFlagRealized(checked: Boolean) {
        updateState {
            val updatedValue = if (flagRealized == checked) null else checked
            copy(
                flagRealized = updatedValue,
                flagResult = if (updatedValue == true) flagResult else null
            )
        }
    }

    fun onChangeFlagResult(checked: Boolean) {
        updateState {
            val updatedValue = if (flagResult == checked) null else checked
            copy(flagResult = updatedValue)
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val flagRealized = getRealizedBreathalyzer(state.option, state.idMain).getOrThrow()
            val flagResult = getResultBreathalyzer(state.option, state.idMain).getOrThrow()
            Pair(flagRealized, flagResult)
        }
            .onSuccess { (flagRealized, flagResult) ->
                updateState {
                    copy(
                        flagRealized = flagRealized,
                        flagResult = flagResult,
                        status = status.copy(flagFailure = false)
                    )
                }
            }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun set() = viewModelScope.launch {
        runCatching {
            setDataInitialBreathalyzer(state.flagRealized, state.flagResult, state.option, state.idMain).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}