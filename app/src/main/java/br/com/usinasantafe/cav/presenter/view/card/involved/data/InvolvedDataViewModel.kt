package br.com.usinasantafe.cav.presenter.view.card.involved.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.DeleteInvolved
import br.com.usinasantafe.cav.domain.usecases.card.GetAddress
import br.com.usinasantafe.cav.domain.usecases.card.GetDetail
import br.com.usinasantafe.cav.domain.usecases.card.GetDocument
import br.com.usinasantafe.cav.domain.usecases.card.GetName
import br.com.usinasantafe.cav.domain.usecases.card.GetPhone
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
import br.com.usinasantafe.cav.utils.onSuccessStateAccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class InvolvedDataState(
    val flowNote: FlowNote = FlowNote.COLAB,
    val idMain: Int = 0,
    val idSecondary: Int = 0,
    val document: String = "",
    val name: String = "",
    val state: State = State.UNHARMED,
    val phone: String = "",
    val address: String = "",
    val detail: String = "",
    val flagDialogCheck: Boolean = false,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<InvolvedDataState> {

    override fun copyWithStatus(status: UiStatusState): InvolvedDataState =
        copy(status = status)

}

@HiltViewModel
class InvolvedDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDocument: GetDocument,
    private val getName: GetName,
    private val getState: GetState,
    private val getPhone: GetPhone,
    private val getAddress: GetAddress,
    private val getDetail: GetDetail,
    private val deleteInvolved: DeleteInvolved,
) : ViewModel() {

    private val flowNote: Int = savedStateHandle[FLOW_NOTE_ARG]!!
    private val idMain: Int = savedStateHandle[ID_MAIN_ARG]!!
    private val idSecondary: Int = savedStateHandle[ID_SECONDARY_ARG]!!

    private val _uiState = MutableStateFlow(InvolvedDataState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: InvolvedDataState.() -> InvolvedDataState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onDialogCheck(flag: Boolean) = updateState { copy(flagDialogCheck = flag) }

    init {
        updateState {
            copy(
                flowNote = FlowNote.entries[this@InvolvedDataViewModel.flowNote],
                idMain = this@InvolvedDataViewModel.idMain,
                idSecondary = this@InvolvedDataViewModel.idSecondary
            )
        }
    }

    fun recoverData() = viewModelScope.launch {

        data class RecoverInvolved(
            val document: String,
            val name: String,
            val state: State,
            val phone: String,
            val address: String,
            val detail: String
        )

        runCatching {
            RecoverInvolved(
                document = getDocument(Option.EDIT, state.flowNote, state.idMain, state.idSecondary).getOrThrow(),
                name = getName(Option.EDIT, state.flowNote, state.idMain, state.idSecondary).getOrThrow(),
                state = getState(Option.EDIT, state.flowNote, state.idMain, state.idSecondary).getOrThrow(),
                phone = getPhone(Option.EDIT, state.flowNote, state.idMain, state.idSecondary).getOrThrow(),
                address = getAddress(state.flowNote, state.idMain, state.idSecondary).getOrThrow(),
                detail = getDetail(Option.EDIT, state.flowNote, state.idMain, state.idSecondary).getOrThrow()
            )
        }
            .onSuccess {
                updateState {
                    copy(
                        document = it.document,
                        name = it.name,
                        state = it.state,
                        phone = it.phone,
                        address = it.address,
                        detail = it.detail
                    )
                }
            }
            .onFailureState(getClassAndMethod(), ::updateState)

    }

    fun delete() = viewModelScope.launch {
        runCatching {
            deleteInvolved(state.flowNote, state.idMain, state.idSecondary).getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }
}