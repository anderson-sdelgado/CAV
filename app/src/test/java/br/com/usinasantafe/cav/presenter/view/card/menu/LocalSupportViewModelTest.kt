package br.com.usinasantafe.cav.presenter.view.card.menu

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetLocal
import br.com.usinasantafe.cav.domain.usecases.card.GetSupportTeams
import br.com.usinasantafe.cav.domain.usecases.card.ListDataLocal
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.presenter.model.LocalScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class LocalSupportViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getLocal = mock<GetLocal>()
    private val listDataLocal = mock<ListDataLocal>()
    private val getSupportTeams = mock<GetSupportTeams>()
    private val viewModel = LocalSupportViewModel(
        getLocal = getLocal,
        listDataLocal = listDataLocal,
        getSupportTeams = getSupportTeams
    )

    @Test
    fun `recoverData - Check return true if process execute successfully and return is null`() =
        runTest {
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "LocalSupportViewModel.recoverData -> Cannot invoke \"br.com.usinasantafe.cav.presenter.model.LocalScreenModel.getAddress()\" because \"local\\1\" is null"
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
                viewModel.uiState.value.address,
                ""
            )
            assertEquals(
                viewModel.uiState.value.longitude,
                ""
            )
            assertEquals(
                viewModel.uiState.value.latitude,
                ""
            )
        }

    @Test
    fun `recoverData - Check return failure if have error in GetLocal`() =
        runTest {
            whenever(
                getLocal()
            ).thenReturn(
                resultFailure(
                    context = "GetLocal",
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
                "LocalSupportViewModel.recoverData -> GetLocal -> java.lang.Exception"
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
                viewModel.uiState.value.address,
                ""
            )
            assertEquals(
                viewModel.uiState.value.longitude,
                ""
            )
            assertEquals(
                viewModel.uiState.value.latitude,
                ""
            )
        }

    @Test
    fun `recoverData - Check return failure if have error in ListDataLocal`() =
        runTest {
            whenever(
                getLocal()
            ).thenReturn(
                Result.success(
                    LocalScreenModel(
                        address = "TEST",
                        longitude = "-25.69936",
                        latitude = "-22.54896"
                    )
                )
            )
            whenever(
                listDataLocal()
            ).thenReturn(
                resultFailure(
                    context = "ListDataLocal",
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
                "LocalSupportViewModel.recoverData -> ListDataLocal -> java.lang.Exception"
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
    fun `recoverData - Check return failure if have error in GetSupportTeams`() =
        runTest {
            whenever(
                getLocal()
            ).thenReturn(
                Result.success(
                    LocalScreenModel(
                        address = "TEST",
                        longitude = "-25.69936",
                        latitude = "-22.54896"
                    )
                )
            )
            whenever(
                listDataLocal()
            ).thenReturn(
                Result.success(
                    listOf(
                        "PERFIL" to "ACLIVE",
                        "OBRAS NA PISTA" to "PROTEÇÃO"
                    )
                )
            )
            whenever(
                getSupportTeams()
            ).thenReturn(
                resultFailure(
                    context = "GetSupportTeams",
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
                "LocalSupportViewModel.recoverData -> GetSupportTeams -> java.lang.Exception"
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
    fun `recoverData - Check return true if process execute successfully`() =
        runTest {
            whenever(
                getLocal()
            ).thenReturn(
                Result.success(
                    LocalScreenModel(
                        address = "TEST",
                        longitude = "-25.69936",
                        latitude = "-22.54896"
                    )
                )
            )
            whenever(
                listDataLocal()
            ).thenReturn(
                Result.success(
                    listOf(
                        "PERFIL" to "ACLIVE",
                        "OBRAS NA PISTA" to "PROTEÇÃO"
                    )
                )
            )
            whenever(
                getSupportTeams()
            ).thenReturn(
                Result.success("ITEM 1 - ITEM 2")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                false
            )
            assertEquals(
                viewModel.uiState.value.address,
                "TEST"
            )
            assertEquals(
                viewModel.uiState.value.longitude,
                "-25.69936"
            )
            assertEquals(
                viewModel.uiState.value.latitude,
                "-22.54896"
            )
            assertEquals(
                viewModel.uiState.value.dataLocalList,
                listOf(
                    "PERFIL" to "ACLIVE",
                    "OBRAS NA PISTA" to "PROTEÇÃO"
                )
            )
            assertEquals(
                viewModel.uiState.value.supportTeams,
                "ITEM 1 - ITEM 2"
            )
        }

}