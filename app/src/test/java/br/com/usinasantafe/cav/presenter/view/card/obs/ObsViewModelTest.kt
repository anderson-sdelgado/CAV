package br.com.usinasantafe.cav.presenter.view.card.obs

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetObs
import br.com.usinasantafe.cav.domain.usecases.card.SetObs
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ObsViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getObs = mock<GetObs>()
    private val setObs = mock<SetObs>()
    private val viewModel = ObsViewModel(
        getObs = getObs,
        setObs = setObs
    )

    @Test
    fun `recoverData - Check return failure if have error in GetObs`() =
        runTest {
            whenever(
                getObs()
            ).thenReturn(
                resultFailure(
                    "GetObs",
                    "-",
                    Exception()
                )
            )
            viewModel.recoverData()
            advanceUntilIdle()
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "ObsViewModel.recoverData -> GetObs -> java.lang.Exception"
            )
        }

    @Test
    fun `recoverData - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                getObs()
            ).thenReturn(
                Result.success("TESTE OBS")
            )
            viewModel.recoverData()
            advanceUntilIdle()
            assertEquals(
                viewModel.uiState.value.text,
                "TESTE OBS"
            )
        }

    @Test
    fun `set - Check return failure if have error in SetObs`() =
        runTest {
            viewModel.onTextChanged("TESTE OBS")
            whenever(
                setObs("TESTE OBS")
            ).thenReturn(
                resultFailure(
                    "SetObs",
                    "-",
                    Exception()
                )
            )
            viewModel.set()
            advanceUntilIdle()
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "ObsViewModel.set -> SetObs -> java.lang.Exception"
            )
        }

    @Test
    fun `set - Check return correct if function execute successfully`() =
        runTest {
            viewModel.onTextChanged("TESTE OBS")
            whenever(
                setObs("TESTE OBS")
            ).thenReturn(
                Result.success(Unit)
            )
            viewModel.set()
            advanceUntilIdle()
            verify(setObs, atLeastOnce()).invoke("TESTE OBS")
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                true
            )
        }

}
