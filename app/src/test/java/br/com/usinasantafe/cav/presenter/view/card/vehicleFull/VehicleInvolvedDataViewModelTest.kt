package br.com.usinasantafe.cav.presenter.view.card.vehicleFull

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.DeleteVehicleInvolved
import br.com.usinasantafe.cav.domain.usecases.card.GetDescDriver
import br.com.usinasantafe.cav.domain.usecases.card.GetDescPassengers
import br.com.usinasantafe.cav.domain.usecases.card.GetDescVehicle
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
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
class VehicleInvolvedDataViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getDescVehicle = mock<GetDescVehicle>()
    private val getDescDriver = mock<GetDescDriver>()
    private val getDescPassengers = mock<GetDescPassengers>()
    private val deleteVehicleInvolved = mock<DeleteVehicleInvolved>()

    private val viewModel = VehicleInvolvedDataViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.ID_MAIN_ARG to 2
            )
        ),
        getDescVehicle = getDescVehicle,
        getDescDriver = getDescDriver,
        getDescPassengers = getDescPassengers,
        deleteVehicleInvolved = deleteVehicleInvolved
    )

    @Test
    fun `recoverData - Check return failure if have error in GetDescVehicle`() =
        runTest {
            whenever(
                getDescVehicle(2)
            ).thenReturn(
                resultFailure(
                    context = "GetDescVehicle",
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
                "VehicleInvolvedDataViewModel.recoverData -> GetDescVehicle -> java.lang.Exception"
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
    fun `recoverData - Check return failure if some process return null`() =
        runTest {
            whenever(
                getDescVehicle(2)
            ).thenReturn(
                Result.success("ABC1234 - GOL")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "VehicleInvolvedDataViewModel.recoverData -> Parameter specified as non-null is null: method br.com.usinasantafe.cav.presenter.view.card.vehicleFull.VehicleInvolvedDataState.<init>, parameter driver -> null"
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
    fun `recoverData - Check return failure if have error in GetDescDriver`() =
        runTest {
            whenever(
                getDescVehicle(2)
            ).thenReturn(
                Result.success("ABC1234 - GOL")
            )
            whenever(
                getDescDriver(FlowNote.DRIVER, 2)
            ).thenReturn(
                resultFailure(
                    context = "GetDescDriver",
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
                "VehicleInvolvedDataViewModel.recoverData -> GetDescDriver -> java.lang.Exception"
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
    fun `recoverData - Check return failure if have error in getDescPassengers`() =
        runTest {
            whenever(
                getDescVehicle(2)
            ).thenReturn(
                Result.success("ABC1234 - GOL")
            )
            whenever(
                getDescDriver(FlowNote.DRIVER, 2)
            ).thenReturn(
                Result.success("123.456.789-00 - Test")
            )
            whenever(
                getDescPassengers(FlowNote.PASSENGER_INVOLVED, 2)
            ).thenReturn(
                resultFailure(
                    context = "getDescPassengers",
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
                "VehicleInvolvedDataViewModel.recoverData -> getDescPassengers -> java.lang.Exception"
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
                getDescVehicle(2)
            ).thenReturn(
                Result.success("ABC1234 - GOL")
            )
            whenever(
                getDescDriver(FlowNote.DRIVER, 2)
            ).thenReturn(
                Result.success("123.456.789-00 - Test")
            )
            whenever(
                getDescPassengers(FlowNote.PASSENGER_INVOLVED, 2)
            ).thenReturn(
                Result.success("123.456.789-00 - Test2")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                false
            )
            assertEquals(
                viewModel.uiState.value.vehicle,
                "ABC1234 - GOL"
            )
            assertEquals(
                viewModel.uiState.value.driver,
                "123.456.789-00 - Test"
            )
            assertEquals(
                viewModel.uiState.value.passengers,
                "123.456.789-00 - Test2"
            )
        }

}