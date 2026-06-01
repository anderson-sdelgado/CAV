package br.com.usinasantafe.cav.presenter.view.card.vehicleFull

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetDescColab
import br.com.usinasantafe.cav.domain.usecases.card.GetDescEquip
import br.com.usinasantafe.cav.domain.usecases.card.GetDescEquipSec
import br.com.usinasantafe.cav.domain.usecases.card.GetDescPassengers
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
class VehicleOwnDataViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getDescEquip = mock<GetDescEquip>()
    private val getDescEquipSec = mock<GetDescEquipSec>()
    private val getDescColab = mock<GetDescColab>()
    private val getDescPassengers = mock<GetDescPassengers>()
    private val viewModel = VehicleOwnDataViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.ID_MAIN_ARG to 2
            )
        ),
        getDescEquip = getDescEquip,
        getDescEquipSec = getDescEquipSec,
        getDescColab = getDescColab,
        getDescPassengers = getDescPassengers
    )


    @Test
    fun `recoverData - Check return failure if have error in getDescEquip`() =
        runTest {
            whenever(
                getDescEquip(FlowNote.EQUIP, 2)
            ).thenReturn(
                resultFailure(
                    context = "getDescEquip",
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
                "VehicleOwnDataViewModel.recoverData -> getDescEquip -> java.lang.Exception"
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
    fun `recoverData - Check return failure if have error in GetDescEquipSec`() =
        runTest {
            whenever(
                getDescEquipSec(2)
            ).thenReturn(
                resultFailure(
                    context = "GetDescEquipSec",
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
                "VehicleOwnDataViewModel.recoverData -> GetDescEquipSec -> java.lang.Exception"
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
    fun `recoverData - Check return failure if have error in GetDescDriverColab`() =
        runTest {
            whenever(
                getDescColab(FlowNote.COLAB, 2)
            ).thenReturn(
                resultFailure(
                    context = "GetDescDriverColab",
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
                "VehicleOwnDataViewModel.recoverData -> GetDescDriverColab -> java.lang.Exception"
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
    fun `recoverData - Check return failure if have error in GetDescPassengersColab`() =
        runTest {
            whenever(
                getDescPassengers(2)
            ).thenReturn(
                resultFailure(
                    context = "GetDescPassengersColab",
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
                "VehicleOwnDataViewModel.recoverData -> GetDescPassengersColab -> java.lang.Exception"
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
                getDescEquip(FlowNote.EQUIP, 2)
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
                "VehicleOwnDataViewModel.recoverData -> Parameter specified as non-null is null: method br.com.usinasantafe.cav.presenter.view.card.vehicleFull.VehicleOwnDataState.<init>, parameter equipSec -> null"
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
                getDescEquip(FlowNote.EQUIP,2)
            ).thenReturn(
                Result.success("Equip")
            )
            whenever(
                getDescEquipSec(2)
            ).thenReturn(
                Result.success("EquipSec")
            )
            whenever(
                getDescColab(FlowNote.COLAB,2)
            ).thenReturn(
                Result.success("Driver")
            )
            whenever(
                getDescPassengers(2)
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