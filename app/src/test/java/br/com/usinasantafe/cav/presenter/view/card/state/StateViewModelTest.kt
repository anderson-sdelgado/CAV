package br.com.usinasantafe.cav.presenter.view.card.state

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetState
import br.com.usinasantafe.cav.domain.usecases.card.SetState
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.presenter.Args
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class StateViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getState = mock<GetState>()
    private val setState = mock<SetState>()
    private fun createViewModel(
        option: Option = Option.INSERT,
        flowNote: FlowNote = FlowNote.COLAB,
        idMain: Int = 0,
        idSecondary: Int = 0
    ) = StateViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.OPTION_ARG to option.ordinal,
                Args.FLOW_NOTE_ARG to flowNote.ordinal,
                Args.ID_MAIN_ARG to  idMain,
                Args.ID_SECONDARY_ARG to idSecondary
            )
        ),
        getState = getState,
        setState = setState
    )

    @Test
    fun `recoverData - Check return failure if have error in GetState`() =
        runTest {
            whenever(
                getState(
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "GetState",
                    message = "-",
                    cause = Exception()
                )
            )
            val viewModel = createViewModel(Option.EDIT)
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "StateViewModel.recoverData -> GetState -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
        }

    @Test
    fun `recoverData - Check return true if process execute successfully`() =
        runTest {
            whenever(
                getState(
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success(State.DEAD)
            )
            val viewModel = createViewModel(Option.EDIT)
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
            assertEquals(
                viewModel.uiState.value.stateSelection,
                State.DEAD
            )
        }

    @Test
    fun `set - Check return failure if have error in SetState`() =
        runTest {
            whenever(
                setState(
                    state = State.DEAD,
                    option = Option.INSERT,
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "SetState",
                    message = "-",
                    cause = Exception()
                )
            )
            val viewModel = createViewModel()
            viewModel.onSelection(State.DEAD)
            viewModel.set()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "StateViewModel.set -> SetState -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
        }

    @Test
    fun `set - Check return true if process execute successfully`() =
        runTest {
            val viewModel = createViewModel()
            viewModel.onSelection(State.DEAD)
            viewModel.set()
            verify(
                setState,
                atLeastOnce()
            ).invoke(
                state = State.DEAD,
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                true
            )
        }

}
