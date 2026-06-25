package br.com.usinasantafe.cav.presenter.view.card.equipSecList

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.DeleteEquipSec
import br.com.usinasantafe.cav.domain.usecases.card.ListEquipSec
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.presenter.Args
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
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
class EquipSecListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val listEquipSec = mock<ListEquipSec>()
    private val deleteEquipSec = mock<DeleteEquipSec>()
    private val viewModel = EquipSecListViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.ID_MAIN_ARG to  0
            )
        ),
        listEquipSec = listEquipSec,
    )

    @Test
    fun `recoverData - Check return failure if have error in ListEquipSec`() =
        runTest {
            whenever(
                listEquipSec(0)
            ).thenReturn(
                resultFailure(
                    context = "ListEquipSec",
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
                "EquipSecListViewModel.recoverData -> ListEquipSec -> java.lang.Exception"
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
                listEquipSec(0)
            ).thenReturn(
                Result.success(
                    listOf(
                        ItemListScreenModel(
                            id = 1,
                            desc = "Test"
                        )
                    )
                )
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.list,
                listOf(
                    ItemListScreenModel(
                        id = 1,
                        desc = "Test"
                    )
                )
            )
        }

}