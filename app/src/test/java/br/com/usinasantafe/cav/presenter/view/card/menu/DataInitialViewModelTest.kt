package br.com.usinasantafe.cav.presenter.view.card.menu

import br.com.usinasantafe.cav.MainCoroutineRule
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
    private val viewModel = DataInitialViewModel(
        getAttendant = getAttendant,
        getCar = getCar,
        getNature = getNature,
        getTypeAccident = getTypeAccident
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
                viewModel.uiState.value.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.failure,
                "DataInitialViewModel.recoverData -> GetAttendant -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.flagFailure,
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
                viewModel.uiState.value.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.failure,
                "DataInitialViewModel.recoverData -> GetCar -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.flagFailure,
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
                viewModel.uiState.value.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.failure,
                "DataInitialViewModel.recoverData -> GetNature -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.flagFailure,
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
                viewModel.uiState.value.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.failure,
                "DataInitialViewModel.recoverData -> GetTypeAccident -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.flagFailure,
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
    fun `recoverData - Check return true if GetAttendant execute successfully and return is null`() =
        runTest {
            whenever(
                getAttendant()
            ).thenReturn(
                Result.success("Test")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.failure,
                "DataInitialViewModel.recoverData -> Parameter specified as non-null is null: method br.com.usinasantafe.cav.presenter.view.card.menu.DataInitialState.<init>, parameter car"
            )
            assertEquals(
                viewModel.uiState.value.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.flagFailure,
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

}