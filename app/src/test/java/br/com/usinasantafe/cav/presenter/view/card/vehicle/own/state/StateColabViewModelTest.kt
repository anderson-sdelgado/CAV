package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.state

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetStateColab
import br.com.usinasantafe.cav.domain.usecases.card.SetStateColab
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.presenter.Args
import br.com.usinasantafe.cav.presenter.view.card.state.StateColabOwnViewModel
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
class StateColabViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getStateColab = mock<GetStateColab>()
    private val setStateColab = mock<SetStateColab>()
    private val viewModel = StateColabOwnViewModel(
        saveStateHandle = SavedStateHandle(
            mapOf(
                Args.OPTION_ARG to Option.INSERT.ordinal,
                Args.TYPE_ARG to Type.MAIN.ordinal
            )
        ),
        getStateColab = getStateColab,
        setStateColab = setStateColab
    )

    @Test
    fun `recoverData - Check return failure if have error in GetStateColab`() =
        runTest {
            whenever(
                getStateColab(
                    option = Option.INSERT,
                    type = Type.MAIN
                )
            ).thenReturn(
                resultFailure(
                    context = "GetStateColab",
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
                "StateColabViewModel.recoverData -> GetStateColab -> java.lang.Exception"
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
                getStateColab(
                    option = Option.INSERT,
                    type = Type.MAIN
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
    fun `set - Check return failure if have error in SetStateColab`() =
        runTest {
            whenever(
                setStateColab(
                    option = Option.INSERT,
                    type = Type.MAIN,
                    id = 2
                )
            ).thenReturn(
                resultFailure(
                    context = "SetStateColab",
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
                "StateColabViewModel.set -> SetStateColab -> java.lang.Exception"
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
            whenever(
                setStateColab(
                    option = Option.INSERT,
                    type = Type.MAIN,
                    id = 2
                )
            ).thenReturn(
                Result.success(Unit)
            )
            viewModel.onSelection(2)
            viewModel.set()
            verify(
                setStateColab,
                atLeastOnce()
            ).invoke(
                option = Option.INSERT,
                type = Type.MAIN,
                id = 2
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                true
            )
        }

}