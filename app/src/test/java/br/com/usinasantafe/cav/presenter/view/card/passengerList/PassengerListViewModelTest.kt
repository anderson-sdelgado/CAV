package br.com.usinasantafe.cav.presenter.view.card.passengerList

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.DeleteInvolvedExternal
import br.com.usinasantafe.cav.domain.usecases.card.ListPassenger
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
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
class PassengerListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val listPassenger = mock<ListPassenger>()
    private val deleteInvolvedExternal = mock<DeleteInvolvedExternal>()
    private val viewModel = PassengerListViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.FLOW_NOTE_ARG to FlowNote.COLAB.ordinal,
                Args.ID_MAIN_ARG to  0
            )
        ),
        listPassenger = listPassenger,
        deleteInvolvedExternal = deleteInvolvedExternal
    )

    @Test
    fun `recoverData - Check return failure if have error in ListPassenger`() =
        runTest {
            whenever(
                listPassenger(
                    flowNote = FlowNote.COLAB,
                    idMain = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "ListPassenger",
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
                "PassengerListViewModel.recoverData -> ListPassenger -> java.lang.Exception"
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
                listPassenger(
                    flowNote = FlowNote.COLAB,
                    idMain = 0
                )
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

    @Test
    fun `delete - Check return failure if have error in DeletePassenger`() =
        runTest {
            whenever(
                deleteInvolvedExternal(
                    idSecondary = 2,
                    flowNote = FlowNote.COLAB,
                    idMain = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "DeletePassenger",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onSelectionDelete(2)
            viewModel.delete()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "PassengerListViewModel.delete -> DeletePassenger -> java.lang.Exception"
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
    fun `delete - Check return true if process execute successfully`() =
        runTest {
            whenever(
                listPassenger(
                    flowNote = FlowNote.COLAB,
                    idMain = 0
                )
            ).thenReturn(
                Result.success(
                    emptyList()
                )
            )
            viewModel.onSelectionDelete(2)
            viewModel.delete()
            verify(deleteInvolvedExternal, atLeastOnce()).invoke(
                idSecondary = 2,
                flowNote = FlowNote.COLAB,
                idMain = 0
            )
            assertEquals(
                viewModel.uiState.value.list,
                emptyList()
            )
        }

}