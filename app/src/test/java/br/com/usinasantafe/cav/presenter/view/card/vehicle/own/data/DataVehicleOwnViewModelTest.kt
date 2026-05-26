package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.data

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetDriver
import br.com.usinasantafe.cav.domain.usecases.card.GetEquip
import br.com.usinasantafe.cav.domain.usecases.card.GetEquipSec
import br.com.usinasantafe.cav.domain.usecases.card.GetPassengers
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Type
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
class DataVehicleOwnViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getEquip = mock<GetEquip>()
    private val getEquipSec = mock<GetEquipSec>()
    private val getDriver = mock<GetDriver>()
    private val getPassengers = mock<GetPassengers>()
    private val viewModel = DataVehicleOwnViewModel(
        saveStateHandle = SavedStateHandle(
            mapOf(
                Args.ID_MAIN_ARG to 2,
                Args.TYPE_ARG to Type.MAIN.ordinal
            )
        ),
        getEquip = getEquip,
        getEquipSec = getEquipSec,
        getDriver = getDriver,
        getPassengers = getPassengers
    )


    @Test
    fun `recoverData - Check return failure if have error in GetEquip`() =
        runTest {
            whenever(
                getEquip(2)
            ).thenReturn(
                resultFailure(
                    context = "GetEquip",
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
                "DataVehicleOwnViewModel.recoverData -> GetEquip -> java.lang.Exception"
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
                viewModel.uiState.value.equip,
                ""
            )
            assertEquals(
                viewModel.uiState.value.equipSec,
                ""
            )
            assertEquals(
                viewModel.uiState.value.driver,
                ""
            )
            assertEquals(
                viewModel.uiState.value.passengers,
                ""
            )
        }

    @Test
    fun `recoverData - Check return failure if have error in GetCar`() =
        runTest {
            whenever(
                getEquipSec(2)
            ).thenReturn(
                resultFailure(
                    context = "GetEquipSec",
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
                "DataVehicleOwnViewModel.recoverData -> GetEquipSec -> java.lang.Exception"
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
                viewModel.uiState.value.equip,
                ""
            )
            assertEquals(
                viewModel.uiState.value.equipSec,
                ""
            )
            assertEquals(
                viewModel.uiState.value.driver,
                ""
            )
            assertEquals(
                viewModel.uiState.value.passengers,
                ""
            )
        }

    @Test
    fun `recoverData - Check return failure if have error in GetNature`() =
        runTest {
            whenever(
                getDriver(2)
            ).thenReturn(
                resultFailure(
                    context = "GetDriver",
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
                "DataVehicleOwnViewModel.recoverData -> GetDriver -> java.lang.Exception"
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
                viewModel.uiState.value.equip,
                ""
            )
            assertEquals(
                viewModel.uiState.value.equipSec,
                ""
            )
            assertEquals(
                viewModel.uiState.value.driver,
                ""
            )
            assertEquals(
                viewModel.uiState.value.passengers,
                ""
            )
        }

    @Test
    fun `recoverData - Check return failure if have error in GetTypeAccident`() =
        runTest {
            whenever(
                getPassengers(2)
            ).thenReturn(
                resultFailure(
                    context = "GetPassengers",
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
                "DataVehicleOwnViewModel.recoverData -> GetPassengers -> java.lang.Exception"
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
                viewModel.uiState.value.equip,
                ""
            )
            assertEquals(
                viewModel.uiState.value.equipSec,
                ""
            )
            assertEquals(
                viewModel.uiState.value.driver,
                ""
            )
            assertEquals(
                viewModel.uiState.value.passengers,
                ""
            )
        }

    @Test
    fun `recoverData - Check return true if process execute successfully and return is null`() =
        runTest {
            whenever(
                getEquip(2)
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
                "DataVehicleOwnViewModel.recoverData -> Parameter specified as non-null is null: method br.com.usinasantafe.cav.presenter.view.card.vehicle.own.data.DataVehicleOwnState.<init>, parameter equipSec -> null"
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
                viewModel.uiState.value.equip,
                ""
            )
            assertEquals(
                viewModel.uiState.value.equipSec,
                ""
            )
            assertEquals(
                viewModel.uiState.value.driver,
                ""
            )
            assertEquals(
                viewModel.uiState.value.passengers,
                ""
            )
        }

    @Test
    fun `recoverData - Check return true if all process execute successfully`() =
        runTest {
            whenever(
                getEquip(2)
            ).thenReturn(
                Result.success("Equip")
            )
            whenever(
                getEquipSec(2)
            ).thenReturn(
                Result.success("EquipSec")
            )
            whenever(
                getDriver(2)
            ).thenReturn(
                Result.success("Driver")
            )
            whenever(
                getPassengers(2)
            ).thenReturn(
                Result.success("Passenger")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.equip,
                "Equip"
            )
            assertEquals(
                viewModel.uiState.value.equipSec,
                "EquipSec"
            )
            assertEquals(
                viewModel.uiState.value.driver,
                "Driver"
            )
            assertEquals(
                viewModel.uiState.value.passengers,
                "Passenger"
            )
        }


}