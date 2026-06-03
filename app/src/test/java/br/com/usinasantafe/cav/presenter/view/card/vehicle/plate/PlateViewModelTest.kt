package br.com.usinasantafe.cav.presenter.view.card.vehicle.plate

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetPlate
import br.com.usinasantafe.cav.domain.usecases.card.SetPlate
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
class PlateViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getPlate = mock<GetPlate>()
    private val setPlate = mock<SetPlate>()
    private val viewModel = PlateViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.OPTION_ARG to Option.INSERT.ordinal,
                Args.ID_MAIN_ARG to 1
            )
        ),
        getPlate = getPlate,
        setPlate = setPlate
    )

    @Test
    fun `recoverData - Check return failure if have error in GetPlate`() =
        runTest {
            whenever(
                getPlate(1)
            ).thenReturn(
                resultFailure(
                    context = "GetPlate",
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
                "PlateViewModel.recoverData -> GetPlate -> java.lang.Exception"
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
                getPlate(1)
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
                "PlateViewModel.updateState -> PlateViewModel.set -> FIELD_EMPTY"
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
    fun `set - Check return failure if have error in SetPlate`() =
        runTest {
            whenever(
                setPlate("Test", 1)
            ).thenReturn(
                resultFailure(
                    context = "SetPlate",
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
                "PlateViewModel.set -> SetPlate -> java.lang.Exception"
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
            verify(setPlate, atLeastOnce()).invoke("Test", 1)
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