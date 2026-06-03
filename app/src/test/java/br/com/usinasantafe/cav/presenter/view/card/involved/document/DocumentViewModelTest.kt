package br.com.usinasantafe.cav.presenter.view.card.involved.document

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetDocument
import br.com.usinasantafe.cav.domain.usecases.card.SetDocument
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.Args
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class DocumentViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getDocument = mock<GetDocument>()
    private val setDocument = mock<SetDocument>()
    private val viewModel = DocumentViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.OPTION_ARG to Option.INSERT.ordinal,
                Args.FLOW_NOTE_ARG to FlowNote.EQUIP.ordinal,
                Args.ID_MAIN_ARG to  0,
                Args.ID_SECONDARY_ARG to 0
            )
        ),
        getDocument = getDocument,
        setDocument = setDocument
    )

    @Test
    fun `recoverData - Check return failure if have error in GetDocument`() =
        runTest {
            whenever(
                getDocument(
                    flowNote = FlowNote.EQUIP,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "GetDocument",
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
                "DocumentViewModel.recoverData -> GetDocument -> java.lang.Exception"
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
    fun `recoverData - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                getDocument(
                    flowNote = FlowNote.EQUIP,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("123.456.789-00")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.text,
                "123.456.789-00"
            )
        }

    @Test
    fun `onTextField - Check digit and clean text`() =
        runTest {
            viewModel.onTextField("1", TypeButton.NUMERIC)
            viewModel.onTextField("2", TypeButton.NUMERIC)
            viewModel.onTextField("3", TypeButton.NUMERIC)
            viewModel.onTextField("4", TypeButton.NUMERIC)
            assertEquals(
                viewModel.uiState.value.text,
                "123.4"
            )
            viewModel.onTextField("5", TypeButton.NUMERIC)
            viewModel.onTextField("6", TypeButton.NUMERIC)
            viewModel.onTextField("7", TypeButton.NUMERIC)
            assertEquals(
                viewModel.uiState.value.text,
                "123.456.7"
            )
            viewModel.onTextField("", TypeButton.CLEAN)
            assertEquals(
                viewModel.uiState.value.text,
                "123.456"
            )
        }

    @Test
    fun `set - Check return access if text is empty`() =
        runTest {
            viewModel.onTextField("", TypeButton.OK)
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                true
            )
        }

    @Test
    fun `set - Check return failure if text has fewer than 14 characters`() =
        runTest {
            viewModel.onTextField("1", TypeButton.NUMERIC)
            viewModel.onTextField("2", TypeButton.NUMERIC)
            viewModel.onTextField("3", TypeButton.NUMERIC)
            viewModel.onTextField("4", TypeButton.NUMERIC)
            viewModel.onTextField("", TypeButton.OK)
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "DocumentViewModel.onTextField -> DocumentViewModel.updateState -> DocumentViewModel.set -> INVALID"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.INVALID
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
        }

    @Test
    fun `set - Check return failure if input invalid`() =
        runTest {
            "12345678900".forEach { char ->
                viewModel.onTextField(char.toString(), TypeButton.NUMERIC)
            }
            viewModel.onTextField("", TypeButton.OK)
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "DocumentViewModel.onTextField -> DocumentViewModel.updateState -> DocumentViewModel.set -> INVALID"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.INVALID
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
        }

    @Test
    fun `set - Check return access if CPF is valid`() = runTest {
        "12345678909".forEach { char ->
            viewModel.onTextField(char.toString(), TypeButton.NUMERIC)
        }

        viewModel.onTextField("", TypeButton.OK)

        assertEquals(
            viewModel.uiState.value.status.flagAccess,
            true
        )
        assertEquals(
            viewModel.uiState.value.status.flagFailure,
            false
        )
    }

}