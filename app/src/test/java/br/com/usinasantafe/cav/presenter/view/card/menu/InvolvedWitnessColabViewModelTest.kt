package br.com.usinasantafe.cav.presenter.view.card.menu

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.ListInvolvedColab
import br.com.usinasantafe.cav.domain.usecases.card.ListWitnessColab
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class InvolvedWitnessColabViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val listInvolvedColab = mock<ListInvolvedColab>()
    private val listWitnessColab = mock<ListWitnessColab>()

    private val viewModel = InvolvedWitnessColabViewModel(
        listInvolvedColab = listInvolvedColab,
        listWitnessColab = listWitnessColab
    )

    @Test
    fun `recoverData - Check return failure if have error in ListInvolvedColab`() =
        runTest {
            whenever(listInvolvedColab()).thenReturn(resultFailure("ListInvolvedColab", Exception()))
            whenever(listWitnessColab()).thenReturn(Result.success(emptyList()))

            viewModel.recoverData()
            advanceUntilIdle()

            assertEquals(true, viewModel.uiState.value.status.flagFailure)
            assertEquals("InvolvedWitnessColabViewModel.recoverData -> ListInvolvedColab -> java.lang.Exception", viewModel.uiState.value.status.failure)
        }

    @Test
    fun `recoverData - Check return failure if have error in ListWitnessColab`() =
        runTest {
            whenever(listInvolvedColab()).thenReturn(Result.success(emptyList()))
            whenever(listWitnessColab()).thenReturn(resultFailure("ListWitnessColab", Exception()))

            viewModel.recoverData()
            advanceUntilIdle()

            assertEquals(true, viewModel.uiState.value.status.flagFailure)
            assertEquals("InvolvedWitnessColabViewModel.recoverData -> ListWitnessColab -> java.lang.Exception", viewModel.uiState.value.status.failure)
        }

    @Test
    fun `recoverData - Check return success if all data recovered correctly`() =
        runTest {
            val involvedList = listOf(ItemListScreenModel(1, "123 - TEST"))
            val witnessList = listOf(ItemListScreenModel(2, "456 - TEST 2"))

            whenever(listInvolvedColab()).thenReturn(Result.success(involvedList))
            whenever(listWitnessColab()).thenReturn(Result.success(witnessList))

            viewModel.recoverData()
            advanceUntilIdle()

            assertEquals(false, viewModel.uiState.value.status.flagFailure)
            assertEquals(involvedList, viewModel.uiState.value.involvedList)
            assertEquals(witnessList, viewModel.uiState.value.witnessList)
        }

    @Test
    fun `onCloseDialog - Check state update`() {
        viewModel.onCloseDialog()
        assertEquals(false, viewModel.uiState.value.status.flagDialog)
        assertEquals(false, viewModel.uiState.value.status.flagFailure)
    }

    @Test
    fun `onDialogCheck - Check state update`() {
        viewModel.onDialogCheck(true)
        assertEquals(true, viewModel.uiState.value.flagDialogCheck)
        viewModel.onDialogCheck(false)
        assertEquals(false, viewModel.uiState.value.flagDialogCheck)
    }

}
