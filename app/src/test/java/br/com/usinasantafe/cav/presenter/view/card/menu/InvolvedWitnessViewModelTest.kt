package br.com.usinasantafe.cav.presenter.view.card.menu

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.DeleteInvolved
import br.com.usinasantafe.cav.domain.usecases.card.DeleteWitness
import br.com.usinasantafe.cav.domain.usecases.card.ListInvolved
import br.com.usinasantafe.cav.domain.usecases.card.ListWitness
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.TypePeople
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class InvolvedWitnessViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val listInvolved = mock<ListInvolved>()
    private val listWitness = mock<ListWitness>()
    private val deleteInvolved = mock<DeleteInvolved>()
    private val deleteWitness = mock<DeleteWitness>()
    private val viewModel = InvolvedWitnessViewModel(
        listInvolved = listInvolved,
        listWitness = listWitness,
        deleteInvolved = deleteInvolved,
        deleteWitness = deleteWitness
    )

    @Test
    fun `recoverData - Check return failure if have error in ListInvolved`() =
        runTest {
            whenever(
                listInvolved()
            ).thenReturn(
                resultFailure(
                    context = "ListInvolved",
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
                "InvolvedWitnessViewModel.recoverData -> ListInvolved -> java.lang.Exception"
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
                listInvolved()
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
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "InvolvedWitnessViewModel.recoverData -> Parameter specified as non-null is null: method br.com.usinasantafe.cav.presenter.view.card.menu.InvolvedWitnessState.<init>, parameter witnessList -> null"
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
    fun `recoverData - Check return failure if have error in ListWitness`() =
        runTest {
            whenever(
                listWitness()
            ).thenReturn(
                resultFailure(
                    context = "ListWitness",
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
                "InvolvedWitnessViewModel.recoverData -> ListWitness -> java.lang.Exception"
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
                listInvolved()
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
            whenever(
                listWitness()
            ).thenReturn(
                Result.success(
                    listOf(
                        ItemListScreenModel(
                            id = 1,
                            desc = "Test2"
                        )
                    )
                )
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.involvedList,
                listOf(
                    ItemListScreenModel(
                        id = 1,
                        desc = "Test"
                    )
                )
            )
            assertEquals(
                viewModel.uiState.value.witnessList,
                listOf(
                    ItemListScreenModel(
                        id = 1,
                        desc = "Test2"
                    )
                )
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                false
            )
        }

    @Test
    fun `delete - Check return failure if have error in DeleteInvolved`() =
        runTest {
            whenever(
                deleteInvolved(1)
            ).thenReturn(
                resultFailure(
                    context = "DeleteInvolved",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onSelectionDelete(1 , TypePeople.INVOLVED)
            viewModel.delete()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "InvolvedWitnessViewModel.delete -> DeleteInvolved -> java.lang.Exception"
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
    fun `delete - Check return failure if have error in DeleteWitness`() =
        runTest {
            whenever(
                deleteWitness(2)
            ).thenReturn(
                resultFailure(
                    context = "DeleteWitness",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onSelectionDelete(2 , TypePeople.WITNESS)
            viewModel.delete()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "InvolvedWitnessViewModel.delete -> DeleteWitness -> java.lang.Exception"
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
                listInvolved()
            ).thenReturn(
                Result.success(emptyList())
            )
            whenever(
                listWitness()
            ).thenReturn(
                Result.success(emptyList())
            )
            viewModel.onSelectionDelete(2 , TypePeople.WITNESS)
            viewModel.delete()
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
            assertEquals(
                viewModel.uiState.value.involvedList,
                emptyList()
            )
            assertEquals(
                viewModel.uiState.value.witnessList,
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