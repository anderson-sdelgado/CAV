package br.com.usinasantafe.cav.presenter.view.card.vehicle.brand

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetBrand
import br.com.usinasantafe.cav.domain.usecases.card.SetBrand
import br.com.usinasantafe.cav.lib.Errors
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
class BrandViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getBrand = mock<GetBrand>()
    private val setBrand = mock<SetBrand>()
    private val viewModel = BrandViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.OPTION_ARG to Option.INSERT.ordinal,
                Args.ID_MAIN_ARG to 1
            )
        ),
        getBrand = getBrand,
        setBrand = setBrand
    )

    @Test
    fun `recoverData - Check return failure if have error in GetBrand`() =
        runTest {
            whenever(
                getBrand(1)
            ).thenReturn(
                resultFailure(
                    context = "GetBrand",
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
                "BrandViewModel.recoverData -> GetBrand -> java.lang.Exception"
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
                viewModel.uiState.value.text,
                ""
            )
        }

    @Test
    fun `recoverData - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                getBrand(1)
            ).thenReturn(
                Result.success("ABC1234")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.text,
                "ABC1234"
            )
        }

    @Test
    fun `set - Check return failure if text is empty`() =
        runTest {
            viewModel.set()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "BrandViewModel.updateState -> BrandViewModel.set -> FIELD_EMPTY"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.FIELD_EMPTY
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
    fun `set - Check return failure if have error in SetBrand`() =
        runTest {
            whenever(
                setBrand("Test", Option.INSERT, 1)
            ).thenReturn(
                resultFailure(
                    context = "SetBrand",
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
                "BrandViewModel.set -> SetBrand -> java.lang.Exception"
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
    fun `set - Check return correct if function execute successfully`() =
        runTest {
            viewModel.onTextChanged("Test")
            viewModel.set()
            verify(setBrand, atLeastOnce()).invoke("Test", Option.INSERT,1)
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