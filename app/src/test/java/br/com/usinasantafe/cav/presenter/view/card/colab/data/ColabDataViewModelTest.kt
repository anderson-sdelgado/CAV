package br.com.usinasantafe.cav.presenter.view.card.colab.data

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.DeleteInvolvedExternal
import br.com.usinasantafe.cav.domain.usecases.card.GetDescBreathalyzer
import br.com.usinasantafe.cav.domain.usecases.card.GetDescColab
import br.com.usinasantafe.cav.domain.usecases.card.GetDetail
import br.com.usinasantafe.cav.domain.usecases.card.GetState
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
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
class ColabDataViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getDescColab = mock<GetDescColab>()
    private val getState = mock<GetState>()
    private val getDetail = mock<GetDetail>()
    private val getDescBreathalyzer = mock<GetDescBreathalyzer>()
    private val deleteInvolvedExternal = mock<DeleteInvolvedExternal>()

    private fun createViewModel(
        flowNote: FlowNote = FlowNote.COLAB,
        idMain: Int = 1,
        idSecondary: Int = 0
    ): ColabDataViewModel {
        val savedStateHandle = SavedStateHandle(
            mapOf(
                FLOW_NOTE_ARG to flowNote.ordinal,
                ID_MAIN_ARG to idMain,
                ID_SECONDARY_ARG to idSecondary
            )
        )
        return ColabDataViewModel(
            savedStateHandle = savedStateHandle,
            getDescColab = getDescColab,
            getState = getState,
            getDetail = getDetail,
            getDescBreathalyzer = getDescBreathalyzer,
            deleteInvolvedExternal = deleteInvolvedExternal
        )
    }

    @Test
    fun `recoverData - Check return failure if have error in GetDescColab`() = runTest {
        val viewModel = createViewModel()
        whenever(getDescColab(FlowNote.COLAB, 1, 0)).thenReturn(resultFailure("GetDescColab", Exception()))
        
        viewModel.recoverData()
        advanceUntilIdle()

        assertEquals(true, viewModel.uiState.value.status.flagFailure)
        assertEquals("ColabDataViewModel.recoverData -> GetDescColab -> java.lang.Exception", viewModel.uiState.value.status.failure)
    }

    @Test
    fun `recoverData - Check return success if data recovered correctly for FlowNote COLAB`() = runTest {
        val viewModel = createViewModel(FlowNote.COLAB, 1, 0)
        whenever(getDescColab(FlowNote.COLAB, 1, 0)).thenReturn(Result.success("123 - TEST"))
        whenever(getState(Option.EDIT, FlowNote.COLAB, 1, 0)).thenReturn(Result.success(State.INJURED))
        whenever(getDetail(Option.EDIT, FlowNote.COLAB, 1, 0)).thenReturn(Result.success("DETAIL TEST"))
        whenever(getDescBreathalyzer(1)).thenReturn(Result.success("BAF: REALIZADO"))

        viewModel.recoverData()
        advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.status.flagFailure)
        assertEquals("123 - TEST", viewModel.uiState.value.colab)
        assertEquals(State.INJURED, viewModel.uiState.value.state)
        assertEquals("DETAIL TEST", viewModel.uiState.value.detail)
        assertEquals("BAF: REALIZADO", viewModel.uiState.value.breathalyzer)
    }

    @Test
    fun `recoverData - Check breathalyzer is empty if FlowNote is not COLAB`() = runTest {
        val viewModel = createViewModel(FlowNote.PASSENGER_COLAB, 1, 10)
        whenever(getDescColab(FlowNote.PASSENGER_COLAB, 1, 10)).thenReturn(Result.success("456 - PASS"))
        whenever(getState(Option.EDIT, FlowNote.PASSENGER_COLAB, 1, 10)).thenReturn(Result.success(State.UNHARMED))
        whenever(getDetail(Option.EDIT, FlowNote.PASSENGER_COLAB, 1, 10)).thenReturn(Result.success(""))

        viewModel.recoverData()
        advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.status.flagFailure)
        assertEquals("456 - PASS", viewModel.uiState.value.colab)
        assertEquals("", viewModel.uiState.value.breathalyzer)
    }

    @Test
    fun `delete - Check return failure if have error in DeleteInvolvedExternal`() = runTest {
        val viewModel = createViewModel(FlowNote.INVOLVED_COLAB, 1, 0)
        whenever(deleteInvolvedExternal(FlowNote.INVOLVED_COLAB, 1, 0)).thenReturn(resultFailure("DeleteInvolvedExternal", Exception()))

        viewModel.delete()
        advanceUntilIdle()

        assertEquals(true, viewModel.uiState.value.status.flagFailure)
    }

    @Test
    fun `delete - Check return success if deleted correctly`() = runTest {
        val viewModel = createViewModel(FlowNote.INVOLVED_COLAB, 1, 0)
        whenever(deleteInvolvedExternal(FlowNote.INVOLVED_COLAB, 1, 0)).thenReturn(Result.success(Unit))

        viewModel.delete()
        advanceUntilIdle()

        assertEquals(true, viewModel.uiState.value.status.flagAccess)
        assertEquals(false, viewModel.uiState.value.status.flagFailure)
    }

}
