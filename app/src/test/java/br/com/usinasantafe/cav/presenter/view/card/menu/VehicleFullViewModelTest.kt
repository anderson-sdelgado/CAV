package br.com.usinasantafe.cav.presenter.view.card.menu

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.DeleteVehicleOwn
import br.com.usinasantafe.cav.domain.usecases.card.DeleteVehicleInvolved
import br.com.usinasantafe.cav.domain.usecases.card.ListVehicleInvolved
import br.com.usinasantafe.cav.domain.usecases.card.ListVehicleOwn
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.TypeVehicle
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class VehicleFullViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val listVehicleOwn = mock<ListVehicleOwn>()
    private val listVehicleInvolved = mock<ListVehicleInvolved>()
    private val deleteVehicleOwn = mock<DeleteVehicleOwn>()
    private val deleteVehicleInvolved = mock<DeleteVehicleInvolved>()
    private val viewModel = VehicleFullViewModel(
        listVehicleOwn = listVehicleOwn,
        listVehicleInvolved = listVehicleInvolved,
        deleteVehicleOwn = deleteVehicleOwn,
        deleteVehicleInvolved = deleteVehicleInvolved
    )

    @Test
    fun `recoverData - Check return failure if have error in ListVehicleOwn`() =
        runTest {
            whenever(
                listVehicleOwn()
            ).thenReturn(
                resultFailure(
                    context = "ListVehicleOwn",
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
                "VehicleFullViewModel.recoverData -> ListVehicleOwn -> java.lang.Exception"
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
    fun `recoverData - Check return failure if process is incompleted`() =
        runTest {
            whenever(
                listVehicleOwn()
            ).thenReturn(
                Result.success(
                    listOf(
                        VehicleScreenModel(
                            id = 1,
                            driver = "123456 - TESTE",
                            vehicle = "2200 - CAMINHAO"
                        )
                    )
                )
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "VehicleFullViewModel.recoverData -> Parameter specified as non-null is null: method br.com.usinasantafe.cav.presenter.view.card.menu.VehicleFullState.<init>, parameter vehicleInvolvedList -> null"
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
    fun `recoverData - Check return failure if have error in ListVehicleInvolved`() =
        runTest {
            whenever(
                listVehicleInvolved()
            ).thenReturn(
                resultFailure(
                    context = "ListVehicleInvolved",
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
                "VehicleFullViewModel.recoverData -> ListVehicleInvolved -> java.lang.Exception"
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
                listVehicleOwn()
            ).thenReturn(
                Result.success(
                    listOf(
                        VehicleScreenModel(
                            id = 1,
                            driver = "123456 - TESTE",
                            vehicle = "2200 - CAMINHAO"
                        )
                    )
                )
            )
            whenever(
                listVehicleInvolved()
            ).thenReturn(
                Result.success(
                    listOf(
                        VehicleScreenModel(
                            id = 1,
                            driver = "123.456.789-00 - TESTE",
                            vehicle = "ABC1234 - GOL"
                        )
                    )
                )
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.vehicleOwnList,
                listOf(
                    VehicleScreenModel(
                        id = 1,
                        driver = "123456 - TESTE",
                        vehicle = "2200 - CAMINHAO"
                    )
                )
            )
            assertEquals(
                viewModel.uiState.value.vehicleInvolvedList,
                listOf(
                    VehicleScreenModel(
                        id = 1,
                        driver = "123.456.789-00 - TESTE",
                        vehicle = "ABC1234 - GOL"
                    )
                )
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                false
            )
        }

    @Test
    fun `delete - Check return failure if have error in DeleteEquip`() =
        runTest {
            whenever(
                deleteVehicleOwn(1)
            ).thenReturn(
                resultFailure(
                    context = "DeleteEquip",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onSelectionDelete(1 , TypeVehicle.OWN)
            viewModel.delete()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "VehicleFullViewModel.delete -> DeleteEquip -> java.lang.Exception"
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
    fun `delete - Check return failure if have error in DeleteVehicle`() =
        runTest {
            whenever(
                deleteVehicleInvolved(2)
            ).thenReturn(
                resultFailure(
                    context = "DeleteVehicle",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onSelectionDelete(2 , TypeVehicle.INVOLVED)
            viewModel.delete()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "VehicleFullViewModel.delete -> DeleteVehicle -> java.lang.Exception"
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
    fun `delete - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                listVehicleOwn()
            ).thenReturn(
                Result.success(emptyList())
            )
            whenever(
                listVehicleInvolved()
            ).thenReturn(
                Result.success(emptyList())
            )
            viewModel.onSelectionDelete(2 , TypeVehicle.INVOLVED)
            viewModel.delete()
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
            assertEquals(
                viewModel.uiState.value.vehicleOwnList,
                emptyList()
            )
            assertEquals(
                viewModel.uiState.value.vehicleInvolvedList,
                emptyList()
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                false
            )
            assertEquals(
                viewModel.uiState.value.flagDialogCheck,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                false
            )
        }

}