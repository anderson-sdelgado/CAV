package br.com.usinasantafe.cav.presenter.view.card.vehicle.data

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetBrand
import br.com.usinasantafe.cav.domain.usecases.card.GetDetail
import br.com.usinasantafe.cav.domain.usecases.card.GetPlate
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
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
class VehicleDataViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getPlate = mock<GetPlate>()
    private val getBrand = mock<GetBrand>()
    private val getDetail = mock<GetDetail>()
    private val viewModel = VehicleDataViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.FLOW_NOTE_ARG to FlowNote.VEHICLE.ordinal,
                Args.ID_MAIN_ARG to 1,
                Args.ID_SECONDARY_ARG to 0
            )
        ),
        getPlate = getPlate,
        getBrand = getBrand,
        getDetail = getDetail
    )

    @Test
    fun `recoverData - Check return failure if have error in GetPlate`() =
        runTest {
            whenever(
                getPlate(
                    option = Option.EDIT,
                    idMain = 1
                )
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
                "VehicleDataViewModel.recoverData -> GetPlate -> java.lang.Exception"
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
    fun `recoverData - Check return failure if have error in GetBrand`() =
        runTest {
            whenever(
                getPlate(
                    option = Option.EDIT,
                    idMain = 1
                )
            ).thenReturn(
                Result.success("ABC-1234")
            )
            whenever(
                getBrand(
                    option = Option.EDIT,
                    idMain = 1
                )
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
                "VehicleDataViewModel.recoverData -> GetBrand -> java.lang.Exception"
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
    fun `recoverData - Check return failure if have error in GetDetail`() =
        runTest {
            whenever(
                getPlate(
                    option = Option.EDIT,
                    idMain = 1
                )
            ).thenReturn(
                Result.success("ABC-1234")
            )
            whenever(
                getBrand(
                    option = Option.EDIT,
                    idMain = 1
                )
            ).thenReturn(
                Result.success("FORD")
            )
            whenever(
                getDetail(
                    option = Option.EDIT,
                    flowNote = FlowNote.VEHICLE,
                    idMain = 1,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "GetDetail",
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
                "VehicleDataViewModel.recoverData -> GetDetail -> java.lang.Exception"
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
    fun `recoverData - Check return true if function execute successfully`() =
        runTest {
            whenever(
                getPlate(
                    option = Option.EDIT,
                    idMain = 1
                )
            ).thenReturn(
                Result.success("ABC-1234")
            )
            whenever(
                getBrand(
                    option = Option.EDIT,
                    idMain = 1
                )
            ).thenReturn(
                Result.success("FORD")
            )
            whenever(
                getDetail(
                    option = Option.EDIT,
                    flowNote = FlowNote.VEHICLE,
                    idMain = 1,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("Test")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                false
            )
            assertEquals(
                viewModel.uiState.value.plate,
                "ABC-1234"
            )
            assertEquals(
                viewModel.uiState.value.brand,
                "FORD"
            )
            assertEquals(
                viewModel.uiState.value.detail,
                "Test"
            )
        }
}
