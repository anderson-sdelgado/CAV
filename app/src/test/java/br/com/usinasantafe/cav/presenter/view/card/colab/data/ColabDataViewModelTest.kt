package br.com.usinasantafe.cav.presenter.view.card.colab.data

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.DeleteInvolvedExternal
import br.com.usinasantafe.cav.domain.usecases.card.GetDescColab
import br.com.usinasantafe.cav.domain.usecases.card.GetDetail
import br.com.usinasantafe.cav.domain.usecases.card.GetState
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
class ColabDataViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getDescColab = mock<GetDescColab>()
    private val getState = mock<GetState>()
    private val getDetail = mock<GetDetail>()
    private val deleteInvolvedExternal = mock<DeleteInvolvedExternal>()
    private val viewModel = ColabDataViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.FLOW_NOTE_ARG to FlowNote.COLAB.ordinal,
                Args.ID_MAIN_ARG to 0,
                Args.ID_SECONDARY_ARG to 0
            )
        ),
        getDescColab = getDescColab,
        getState = getState,
        getDetail = getDetail,
        deleteInvolvedExternal = deleteInvolvedExternal
    )

    @Test
    fun `recoverData - Check return failure if have error in DescColab`() =
        runTest {
            whenever(
                getDescColab(
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "DescColab",
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
                "ColabDataViewModel.recoverData -> DescColab -> java.lang.Exception"
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
    fun `recoverData - Check return failure if some process return is null`() =
        runTest {
            whenever(
                getDescColab(
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("123456 - Teste")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "ColabDataViewModel.recoverData -> Parameter specified as non-null is null: method br.com.usinasantafe.cav.presenter.view.card.colab.data.ColabDataState.<init>, parameter state -> null"
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
    fun `recoverData - Check return failure if have error in getDescState`() =
        runTest {
            whenever(
                getDescColab(
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("123456 - Teste")
            )
            whenever(
                getState(
                    option = Option.EDIT,
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "getDescState",
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
                "ColabDataViewModel.recoverData -> getDescState -> java.lang.Exception"
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
                getDescColab(
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("123456 - Teste")
            )
            whenever(
                getState(
                    option = Option.EDIT,
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success(State.UNHARMED)
            )
            whenever(
                getDetail(
                    option = Option.EDIT,
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
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
                "ColabDataViewModel.recoverData -> GetDetail -> java.lang.Exception"
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
                getDescColab(
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("123456 - Teste")
            )
            whenever(
                getState(
                    option = Option.EDIT,
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success(State.UNHARMED)
            )
            whenever(
                getDetail(
                    option = Option.EDIT,
                    flowNote = FlowNote.COLAB,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("Test")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                false
            )
            assertEquals(
                viewModel.uiState.value.colab,
                "123456 - Teste"
            )
            assertEquals(
                viewModel.uiState.value.state,
                State.UNHARMED
            )
            assertEquals(
                viewModel.uiState.value.detail,
                "Test"
            )
        }

}