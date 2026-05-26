package br.com.usinasantafe.cav.presenter.view.card.local

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.SetLocal
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class InputLocalViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val setLocal = mock<SetLocal>()
    private val viewModel = InputLocalViewModel(
        setLocal = setLocal
    )

    @Test
    fun `set - Check return failure if field is empty`() =
        runTest {
            viewModel.set()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "TypeLocalViewModel.set -> FIELD_EMPTY"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.FIELD_EMPTY
            )
        }

    @Test
    fun `set - Check return failure if have error in SetLocal`() =
        runTest {
            whenever(
                setLocal("Test")
            ).thenReturn(
                resultFailure(
                    context = "SetLocal",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onAddressChanged("Test")
            viewModel.set()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "TypeLocalViewModel.set -> SetLocal -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
        }

    @Test
    fun `set - Check return true if SetLocal execute successfully`() =
        runTest {
            viewModel.onAddressChanged("Test")
            viewModel.set()
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                false
            )
        }

}