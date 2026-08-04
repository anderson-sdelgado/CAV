package br.com.usinasantafe.cav.presenter.view.card.breathalyzer.count

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetCountBreathalyzer
import br.com.usinasantafe.cav.domain.usecases.card.SetCountBreathalyzer
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
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
class CountBreathalyzerViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getCountBreathalyzer = mock<GetCountBreathalyzer>()
    private val setCountBreathalyzer = mock<SetCountBreathalyzer>()

    private fun createViewModel(option: Option = Option.INSERT, idMain: Int = 0): CountBreathalyzerViewModel {
        val savedStateHandle = SavedStateHandle(
            mapOf(
                OPTION_ARG to option.ordinal,
                ID_MAIN_ARG to idMain
            )
        )
        return CountBreathalyzerViewModel(
            savedStateHandle = savedStateHandle,
            getCountBreathalyzer = getCountBreathalyzer,
            setCountBreathalyzer = setCountBreathalyzer
        )
    }

    @Test
    fun `init - Check state initialized correctly`() {
        val viewModel = createViewModel(Option.EDIT, 1)
        assertEquals(Option.EDIT, viewModel.uiState.value.option)
        assertEquals(1, viewModel.uiState.value.idMain)
        assertEquals("0,00", viewModel.uiState.value.text)
    }

    @Test
    fun `recoverData - Check return failure if have error in GetCountBreathalyzer`() = runTest {
        val viewModel = createViewModel()
        whenever(getCountBreathalyzer(Option.INSERT, 0)).thenReturn(resultFailure("GetCountBreathalyzer", Exception()))
        
        viewModel.recoverData()
        advanceUntilIdle()

        assertEquals(true, viewModel.uiState.value.status.flagFailure)
        assertEquals("CountBreathalyzerViewModel.recoverData -> GetCountBreathalyzer -> java.lang.Exception", viewModel.uiState.value.status.failure)
    }

    @Test
    fun `recoverData - Check return success and format if data recovered`() = runTest {
        val viewModel = createViewModel()
        whenever(getCountBreathalyzer(Option.INSERT, 0)).thenReturn(Result.success(0.12))

        viewModel.recoverData()
        advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.status.flagFailure)
        assertEquals("0,12", viewModel.uiState.value.text)
    }

    @Test
    fun `onTextField - Check numeric input update text correctly`() {
        val viewModel = createViewModel()
        
        viewModel.onTextField("1", TypeButton.NUMERIC)
        assertEquals("0,01", viewModel.uiState.value.text)

        viewModel.onTextField("2", TypeButton.NUMERIC)
        assertEquals("0,12", viewModel.uiState.value.text)

        viewModel.onTextField("5", TypeButton.NUMERIC)
        assertEquals("1,25", viewModel.uiState.value.text)
    }

    @Test
    fun `onTextField - Check clean button update text correctly`() {
        val viewModel = createViewModel()
        
        viewModel.onTextField("1", TypeButton.NUMERIC) // 0,01
        viewModel.onTextField("2", TypeButton.NUMERIC) // 0,12
        
        viewModel.onTextField("", TypeButton.CLEAN)
        assertEquals("0,01", viewModel.uiState.value.text)

        viewModel.onTextField("", TypeButton.CLEAN)
        assertEquals("0,00", viewModel.uiState.value.text)
    }

    @Test
    fun `set - Check return failure if text is 0,00`() = runTest {
        val viewModel = createViewModel()
        
        viewModel.onTextField("", TypeButton.OK)
        advanceUntilIdle()

        assertEquals(true, viewModel.uiState.value.status.flagFailure)
        assertEquals(Errors.FIELD_EMPTY, viewModel.uiState.value.status.errors)
    }

    @Test
    fun `set - Check return success if text is valid`() = runTest {
        val viewModel = createViewModel()
        viewModel.onTextField("5", TypeButton.NUMERIC) // 0,05
        
        whenever(setCountBreathalyzer("0,05", Option.INSERT, 0)).thenReturn(Result.success(Unit))

        viewModel.onTextField("", TypeButton.OK)
        advanceUntilIdle()

        assertEquals(true, viewModel.uiState.value.status.flagAccess)
        assertEquals(false, viewModel.uiState.value.status.flagFailure)
    }

}
