package br.com.usinasantafe.cav.presenter.view.card.colab.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetDescColab
import br.com.usinasantafe.cav.domain.usecases.card.GetDetail
import br.com.usinasantafe.cav.domain.usecases.card.GetState
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ColabDataState(
    val flowNote: FlowNote = FlowNote.COLAB,
    val idMain: Int = 0,
    val idSecondary: Int = 0,
    val colab: String = "",
    val state: State = State.UNHARMED,
    val detail: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<ColabDataState> {

    override fun copyWithStatus(status: UiStatusState): ColabDataState =
        copy(status = status)

}

@HiltViewModel
class ColabDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDescColab: GetDescColab,
    private val getState: GetState,
    private val getDetail: GetDetail
) : ViewModel() {

    private val flowNote: Int = savedStateHandle[FLOW_NOTE_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!
    private val idSecondary: Int = savedStateHandle[ID_SECONDARY_ARG]!!

    private val _uiState = MutableStateFlow(ColabDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: ColabDataState.() -> ColabDataState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                flowNote = FlowNote.entries[this@ColabDataViewModel.flowNote],
                idMain = this@ColabDataViewModel.idMain,
                idSecondary = this@ColabDataViewModel.idSecondary
            )
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            val descColab = getDescColab(state.flowNote, state.idMain, state.idSecondary).getOrThrow()
            val stateRet = getState(Option.EDIT, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
            val detail = getDetail(Option.EDIT, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
            ColabDataState(
                colab = descColab,
                state = stateRet,
                detail = detail
            )
        }
            .onSuccess { newState ->
                updateState {
                    newState.copy(status = status.copy(flagFailure = false))
                }
            }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}