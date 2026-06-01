package br.com.usinasantafe.cav.presenter.view.card.state

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetIdState
import br.com.usinasantafe.cav.domain.usecases.card.SetState
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
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

    private val getIdState = mock<GetIdState>()
    private val setState = mock<SetState>()
    private val viewModel = StateViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.OPTION_ARG to Option.INSERT.ordinal,
                Args.FLOW_NOTE_ARG to FlowNote.COLAB.ordinal,
                Args.ID_MAIN_ARG to 0,
                Args.ID_SECONDARY_ARG to 0
            )
        ),
        getIdState = getIdState,
        setState = setState
    )

    @Test
    fun `recoverData - Check return failure if have error in GetState`() =
        runTest {
            whenever(
                getIdState(
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
                getIdState(
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success(2)
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
            assertEquals(
                viewModel.uiState.value.idSelection,
                2
            )
        }

    @Test
    fun `set - Check return failure if have error in SetState`() =
        runTest {
            whenever(
                setState(
                    idSelection = 2,
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
            viewModel.onSelection(2)
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
            viewModel.onSelection(2)
            viewModel.set()
            verify(
                setState,
                atLeastOnce()
            ).invoke(
                idSelection = 2,
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