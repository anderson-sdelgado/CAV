package br.com.usinasantafe.cav.presenter.view.card.menu

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.ListInvolvedExternal
import br.com.usinasantafe.cav.domain.usecases.card.ListWitnessExternal
import br.com.usinasantafe.cav.lib.Errors
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
class ExternalWitnessPeopleExternalViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val listInvolvedExternal = mock<ListInvolvedExternal>()
    private val listWitnessExternal = mock<ListWitnessExternal>()
    private val viewModel = InvolvedWitnessExternalViewModel(
        listInvolvedExternal = listInvolvedExternal,
        listWitnessExternal = listWitnessExternal,
    )

    @Test
    fun `recoverData - Check return failure if have error in ListInvolved`() =
        runTest {
            whenever(
                listInvolvedExternal()
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
                listInvolvedExternal()
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
                listWitnessExternal()
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
                listInvolvedExternal()
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
                listWitnessExternal()
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

}