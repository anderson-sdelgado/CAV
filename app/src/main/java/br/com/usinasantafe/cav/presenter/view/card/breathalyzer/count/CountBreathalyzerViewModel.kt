package br.com.usinasantafe.cav.presenter.view.card.breathalyzer.count

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetCountBreathalyzer
import br.com.usinasantafe.cav.domain.usecases.card.SetCountBreathalyzer
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.theme.addTextFieldComma
import br.com.usinasantafe.cav.presenter.theme.clearTextFieldComma
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.doubleToString
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

const val COUNT_DECIMAL = 2
data class CountBreathalyzerState(
    val option: Option = Option.INSERT,
    val idMain: Int = 0,
    val text: String = "0,00",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<CountBreathalyzerState> {

    override fun copyWithStatus(status: UiStatusState): CountBreathalyzerState =
        copy(status = status)

}

@HiltViewModel
class CountBreathalyzerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCountBreathalyzer: GetCountBreathalyzer,
    private val setCountBreathalyzer: SetCountBreathalyzer
) : ViewModel() {

    private val option: Int = savedStateHandle[OPTION_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!

    private val _uiState = MutableStateFlow(CountBreathalyzerState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: CountBreathalyzerState.() -> CountBreathalyzerState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                option = Option.entries[this@CountBreathalyzerViewModel.option],
                idMain = this@CountBreathalyzerViewModel.idMain,
            )
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            getCountBreathalyzer(state.option, state.idMain).getOrThrow()
        }
            .onSuccess {
                it?.let { count ->
                    updateState { copy(text = doubleToString(count, COUNT_DECIMAL)) }
                }
            }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun onTextField(text: String, typeButton: TypeButton) {
        when (typeButton) {
            TypeButton.NUMERIC -> updateState { copy(text = addTextFieldComma(this.text, text, 2)) }
            TypeButton.CLEAN -> updateState { copy(text = clearTextFieldComma(this.text)) }
            TypeButton.OK -> set()
            TypeButton.UPDATE -> Unit
        }
    }

    private fun set() = viewModelScope.launch {
        runCatching {
            if (state.text == "0,00") {
                updateState { withFailure(getClassAndMethod(), Errors.FIELD_EMPTY) }
                return@launch
            }
            setCountBreathalyzer(state.text, state.option, state.idMain).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }


}