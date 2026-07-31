package br.com.usinasantafe.cav.presenter.view.card.external.data

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.*
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.State
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
class PeopleExternalDataViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getDocument = mock<GetDocument>()
    private val getName = mock<GetName>()
    private val getState = mock<GetState>()
    private val getPhone = mock<GetPhone>()
    private val getAddress = mock<GetAddress>()
    private val getDetail = mock<GetDetail>()
    private val deleteInvolvedExternal = mock<DeleteInvolvedExternal>()
    
    private val viewModel = InvolvedDataViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.FLOW_NOTE_ARG to FlowNote.INVOLVED_EXTERNAL.ordinal,
                Args.ID_MAIN_ARG to 1,
                Args.ID_SECONDARY_ARG to 0
            )
        ),
        getDocument = getDocument,
        getName = getName,
        getState = getState,
        getPhone = getPhone,
        getAddress = getAddress,
        getDetail = getDetail,
        deleteInvolvedExternal = deleteInvolvedExternal
    )

    @Test
    fun `recoverData - Check return failure if have error in GetDocument`() =
        runTest {
            whenever(
                getDocument(
                    option = Option.EDIT,
                    flowNote = FlowNote.INVOLVED_EXTERNAL,
                    idMain = 1,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "GetDocument",
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
                "InvolvedDataViewModel.recoverData -> GetDocument -> java.lang.Exception"
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
                getDocument(Option.EDIT, FlowNote.INVOLVED_EXTERNAL, 1, 0)
            ).thenReturn(Result.success("123"))
            whenever(
                getName(Option.EDIT, FlowNote.INVOLVED_EXTERNAL, 1, 0)
            ).thenReturn(Result.success("TEST NAME"))
            whenever(
                getState(Option.EDIT, FlowNote.INVOLVED_EXTERNAL, 1, 0)
            ).thenReturn(Result.success(State.DEAD))
            whenever(
                getPhone(Option.EDIT, FlowNote.INVOLVED_EXTERNAL, 1, 0)
            ).thenReturn(Result.success("16999999999"))
            whenever(
                getAddress(FlowNote.INVOLVED_EXTERNAL, 1, 0)
            ).thenReturn(Result.success("ADDR"))
            whenever(
                getDetail(Option.EDIT, FlowNote.INVOLVED_EXTERNAL, 1, 0)
            ).thenReturn(Result.success("DETAIL"))

            viewModel.recoverData()
            
            assertEquals(viewModel.uiState.value.document, "123")
            assertEquals(viewModel.uiState.value.name, "TEST NAME")
            assertEquals(viewModel.uiState.value.state, State.DEAD)
            assertEquals(viewModel.uiState.value.phone, "16999999999")
            assertEquals(viewModel.uiState.value.address, "ADDR")
            assertEquals(viewModel.uiState.value.detail, "DETAIL")
            assertEquals(viewModel.uiState.value.status.flagFailure, false)
        }

    @Test
    fun `delete - Check return failure if have error`() =
        runTest {
            whenever(
                deleteInvolvedExternal(FlowNote.INVOLVED_EXTERNAL, 1, 0)
            ).thenReturn(
                resultFailure(
                    context = "DeletePassenger",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.delete()
            assertEquals(viewModel.uiState.value.status.flagFailure, true)
            assertEquals(
                viewModel.uiState.value.status.failure,
                "InvolvedDataViewModel.delete -> DeletePassenger -> java.lang.Exception"
            )
        }
}
