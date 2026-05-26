package br.com.usinasantafe.cav.presenter.view.card.menu

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.CancelCard
import br.com.usinasantafe.cav.domain.usecases.card.GetAttendant
import br.com.usinasantafe.cav.domain.usecases.card.GetCar
import br.com.usinasantafe.cav.domain.usecases.card.GetNature
import br.com.usinasantafe.cav.domain.usecases.card.GetTypeAccident
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
class DataInitialViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getAttendant = mock<GetAttendant>()
    private val getCar = mock<GetCar>()
    private val getNature = mock<GetNature>()
    private val getTypeAccident = mock<GetTypeAccident>()
    private val cancelCard = mock<CancelCard>()
    private val viewModel = DataInitialViewModel(
        getAttendant = getAttendant,
        getCar = getCar,
        getNature = getNature,
        getTypeAccident = getTypeAccident,
        cancelCard = cancelCard
    )

    @Test
    fun `recoverData - Check return failure if have error in GetAttendant`() =
        runTest {
            whenever(
                getAttendant()
            ).thenReturn(
                resultFailure(
                    context = "GetAttendant",
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
                "DataInitialViewModel.recoverData -> GetAttendant -> java.lang.Exception"
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
                viewModel.uiState.value.attendant,
                ""
            )
            assertEquals(
                viewModel.uiState.value.car,
                ""
            )
            assertEquals(
                viewModel.uiState.value.nature,
                ""
            )
            assertEquals(
                viewModel.uiState.value.typeAccident,
                ""
            )
        }

    @Test
    fun `recoverData - Check return failure if have error in GetCar`() =
        runTest {
            whenever(
                getCar()
            ).thenReturn(
                resultFailure(
                    context = "GetCar",
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
                "DataInitialViewModel.recoverData -> GetCar -> java.lang.Exception"
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
                viewModel.uiState.value.attendant,
                ""
            )
            assertEquals(
                viewModel.uiState.value.car,
                ""
            )
            assertEquals(
                viewModel.uiState.value.nature,
                ""
            )
            assertEquals(
                viewModel.uiState.value.typeAccident,
                ""
            )
        }

    @Test
    fun `recoverData - Check return failure if have error in GetNature`() =
        runTest {
            whenever(
                getNature()
            ).thenReturn(
                resultFailure(
                    context = "GetNature",
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
                "DataInitialViewModel.recoverData -> GetNature -> java.lang.Exception"
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
                viewModel.uiState.value.attendant,
                ""
            )
            assertEquals(
                viewModel.uiState.value.car,
                ""
            )
            assertEquals(
                viewModel.uiState.value.nature,
                ""
            )
            assertEquals(
                viewModel.uiState.value.typeAccident,
                ""
            )
        }
    
    @Test
    fun `recoverData - Check return failure if have error in GetTypeAccident`() =
        runTest {
            whenever(
                getTypeAccident()
            ).thenReturn(
                resultFailure(
                    context = "GetTypeAccident",
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
                "DataInitialViewModel.recoverData -> GetTypeAccident -> java.lang.Exception"
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
                viewModel.uiState.value.attendant,
                ""
            )
            assertEquals(
                viewModel.uiState.value.car,
                ""
            )
            assertEquals(
                viewModel.uiState.value.nature,
                ""
            )
            assertEquals(
                viewModel.uiState.value.typeAccident,
                ""
            )
        }
    
    @Test
    fun `recoverData - Check return true if process execute successfully and return is null`() =
        runTest {
            whenever(
                getAttendant()
            ).thenReturn(
                Result.success("Test")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "DataInitialViewModel.recoverData -> Parameter specified as non-null is null: method br.com.usinasantafe.cav.presenter.view.card.menu.DataInitialState.<init>, parameter car"
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
                viewModel.uiState.value.attendant,
                ""
            )
            assertEquals(
                viewModel.uiState.value.car,
                ""
            )
            assertEquals(
                viewModel.uiState.value.nature,
                ""
            )
            assertEquals(
                viewModel.uiState.value.typeAccident,
                ""
            )
        }

    @Test
    fun `recoverData - Check return true if all process execute successfully`() =
        runTest {
            whenever(
                getAttendant()
            ).thenReturn(
                Result.success("Attendant")
            )
            whenever(
                getCar()
            ).thenReturn(
                Result.success("Car")
            )
            whenever(
                getNature()
            ).thenReturn(
                Result.success("-")
            )
            whenever(
                getTypeAccident()
            ).thenReturn(
                Result.success("-")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.attendant,
                "Attendant"
            )
            assertEquals(
                viewModel.uiState.value.car,
                "Car"
            )
            assertEquals(
                viewModel.uiState.value.nature,
                "-"
            )
            assertEquals(
                viewModel.uiState.value.typeAccident,
                "-"
            )
        }

    @Test
    fun `cancel - Check return failure if have error in CancelCard`() =
        runTest {
            whenever(
                cancelCard()
            ).thenReturn(
                resultFailure(
                    context = "CancelCard",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onDialogCheck(true)
            assertEquals(
                viewModel.uiState.value.flagDialogCheck,
                true
            )
            viewModel.cancel()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "DataInitialViewModel.cancel -> CancelCard -> java.lang.Exception"
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
                viewModel.uiState.value.flagDialogCheck,
                false
            )
        }

    @Test
    fun `cancel - Check return true if process execute successfully`() =
        runTest {
            assertEquals(
                viewModel.uiState.value.flagCancel,
                false
            )
            viewModel.cancel()
            assertEquals(
                viewModel.uiState.value.flagCancel,
                true
            )
        }

}