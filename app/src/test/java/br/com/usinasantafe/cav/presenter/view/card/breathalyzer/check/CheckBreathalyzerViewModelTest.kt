package br.com.usinasantafe.cav.presenter.view.card.breathalyzer.check

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetRealizedBreathalyzer
import br.com.usinasantafe.cav.domain.usecases.card.GetResultBreathalyzer
import br.com.usinasantafe.cav.domain.usecases.card.SetDataInitialBreathalyzer
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
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
class CheckBreathalyzerViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getRealizedBreathalyzer = mock<GetRealizedBreathalyzer>()
    private val getResultBreathalyzer = mock<GetResultBreathalyzer>()
    private val setDataInitialBreathalyzer = mock<SetDataInitialBreathalyzer>()

    private fun createViewModel(option: Option = Option.INSERT, idMain: Int = 0): CheckBreathalyzerViewModel {
        val savedStateHandle = SavedStateHandle(
            mapOf(
                OPTION_ARG to option.ordinal,
                ID_MAIN_ARG to idMain
            )
        )
        return CheckBreathalyzerViewModel(
            savedStateHandle = savedStateHandle,
            getRealizedBreathalyzer = getRealizedBreathalyzer,
            getResultBreathalyzer = getResultBreathalyzer,
            setDataInitialBreathalyzer = setDataInitialBreathalyzer
        )
    }

    @Test
    fun `recoverData - Check return failure if have error in GetRealizedBreathalyzer`() = runTest {
        val viewModel = createViewModel()
        whenever(getRealizedBreathalyzer(Option.INSERT, 0)).thenReturn(resultFailure("GetRealizedBreathalyzer", Exception()))
        
        viewModel.recoverData()
        advanceUntilIdle()

        assertEquals(true, viewModel.uiState.value.status.flagFailure)
        assertEquals("CheckBreathalyzerViewModel.recoverData -> GetRealizedBreathalyzer -> java.lang.Exception", viewModel.uiState.value.status.failure)
    }

    @Test
    fun `recoverData - Check return success if data recovered correctly`() = runTest {
        val viewModel = createViewModel()
        whenever(getRealizedBreathalyzer(Option.INSERT, 0)).thenReturn(Result.success(true))
        whenever(getResultBreathalyzer(Option.INSERT, 0)).thenReturn(Result.success(false))

        viewModel.recoverData()
        advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.status.flagFailure)
        assertEquals(true, viewModel.uiState.value.flagRealized)
        assertEquals(false, viewModel.uiState.value.flagResult)
    }

    @Test
    fun `onChangeFlagRealized - Check toggle and reset logic`() = runTest {
        val viewModel = createViewModel()
        
        // Mark SIM
        viewModel.onChangeFlagRealized(true)
        assertEquals(true, viewModel.uiState.value.flagRealized)

        // Mark SIM again (Toggle to null)
        viewModel.onChangeFlagRealized(true)
        assertEquals(null, viewModel.uiState.value.flagRealized)

        // Mark SIM and result
        viewModel.onChangeFlagRealized(true)
        viewModel.onChangeFlagResult(true)
        assertEquals(true, viewModel.uiState.value.flagRealized)
        assertEquals(true, viewModel.uiState.value.flagResult)

        // Change to NÃO (Realized false, result must be null)
        viewModel.onChangeFlagRealized(false)
        assertEquals(false, viewModel.uiState.value.flagRealized)
        assertEquals(null, viewModel.uiState.value.flagResult)
    }

    @Test
    fun `set - Check return failure if realized is null`() = runTest {
        val viewModel = createViewModel()
        
        viewModel.set()
        advanceUntilIdle()

        assertEquals(true, viewModel.uiState.value.status.flagFailure)
        assertEquals(Errors.CHECK_REALIZED_BREATHALYZER_INVALID, viewModel.uiState.value.status.errors)
    }

    @Test
    fun `set - Check return failure if realized is true but result is null`() = runTest {
        val viewModel = createViewModel()
        viewModel.onChangeFlagRealized(true)
        
        viewModel.set()
        advanceUntilIdle()

        assertEquals(true, viewModel.uiState.value.status.flagFailure)
        assertEquals(Errors.CHECK_RESULT_BREATHALYZER_INVALID, viewModel.uiState.value.status.errors)
    }

    @Test
    fun `set - Check return success if all data valid`() = runTest {
        val viewModel = createViewModel()
        viewModel.onChangeFlagRealized(true)
        viewModel.onChangeFlagResult(false)
        
        whenever(setDataInitialBreathalyzer(true, false, Option.INSERT, 0)).thenReturn(Result.success(Unit))

        viewModel.set()
        advanceUntilIdle()

        assertEquals(true, viewModel.uiState.value.status.flagAccess)
        assertEquals(false, viewModel.uiState.value.status.flagFailure)
    }

}
