package br.com.usinasantafe.cav.presenter.view.card.involved.phone

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetPhone
import br.com.usinasantafe.cav.domain.usecases.card.SetPhone
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
class PhoneViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getPhone = mock<GetPhone>()
    private val setPhone = mock<SetPhone>()
    private val viewModel = PhoneViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.OPTION_ARG to Option.INSERT.ordinal,
                Args.FLOW_NOTE_ARG to FlowNote.COLAB.ordinal,
                Args.ID_MAIN_ARG to 0,
                Args.ID_SECONDARY_ARG to 0
            )
        ),
        getPhone = getPhone,
        setPhone = setPhone
    )

    @Test
    fun `recoverData - Check return failure if have error in GetPhone`() =
        runTest {
            whenever(
                getPhone(
                    option = Option.INSERT,
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "GetPhone",
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
                "PhoneViewModel.recoverData -> GetPhone -> java.lang.Exception"
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
                getPhone(
                    option = Option.INSERT,
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("(16) 99999-1234")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.text,
                "(16) 99999-1234"
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
                "(12) 34"
            )
            viewModel.onTextField("5", TypeButton.NUMERIC)
            viewModel.onTextField("6", TypeButton.NUMERIC)
            viewModel.onTextField("7", TypeButton.NUMERIC)
            viewModel.onTextField("8", TypeButton.NUMERIC)
            assertEquals(
                viewModel.uiState.value.text,
                "(12) 34567-8"
            )
            viewModel.onTextField("", TypeButton.CLEAN)
            assertEquals(
                viewModel.uiState.value.text,
                "(12) 34567"
            )
        }

    @Test
    fun `set - Check return failure if input invalid`() =
        runTest {
            "1699".forEach { char ->
                viewModel.onTextField(char.toString(), TypeButton.NUMERIC)
            }
            viewModel.onTextField("", TypeButton.OK)
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "PhoneViewModel.onTextField -> PhoneViewModel.updateState -> PhoneViewModel.set -> INVALID"
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
        "169999912345".forEach { char ->
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