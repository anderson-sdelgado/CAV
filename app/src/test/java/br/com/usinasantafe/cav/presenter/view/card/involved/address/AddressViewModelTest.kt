package br.com.usinasantafe.cav.presenter.view.card.involved.address

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetAddress
import br.com.usinasantafe.cav.domain.usecases.card.SetAddress
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
class AddressViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getAddress = mock<GetAddress>()
    private val setAddress = mock<SetAddress>()
    private val viewModel = AddressViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.OPTION_ARG to Option.INSERT.ordinal,
                Args.FLOW_NOTE_ARG to FlowNote.COLAB.ordinal,
                Args.ID_MAIN_ARG to 0,
                Args.ID_SECONDARY_ARG to 0
            )
        ),
        getAddress = getAddress,
        setAddress = setAddress
    )

    @Test
    fun `recoverData - Check return failure if have error in GetState`() =
        runTest {
            whenever(
                getAddress(
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
                "AddressViewModel.recoverData -> GetState -> java.lang.Exception"
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
                getAddress(
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("Test")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
            assertEquals(
                viewModel.uiState.value.text,
                "Test"
            )
        }

    @Test
    fun `set - Check return failure if have error in SetAddress`() =
        runTest {
            whenever(
                setAddress(
                    address = "Test",
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "SetAddress",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onTextChanged("Test")
            viewModel.set()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "AddressViewModel.set -> SetAddress -> java.lang.Exception"
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
            viewModel.onTextChanged("Test")
            viewModel.set()
            verify(
                setAddress,
                atLeastOnce()
            ).invoke(
                address = "Test",
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