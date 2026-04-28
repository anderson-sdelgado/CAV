package br.com.usinasantafe.cav.presenter.view.configuration.initial

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.common.GetStatusSend
import br.com.usinasantafe.cav.domain.usecases.config.CheckAccessInitial
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class InitialMenuViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val checkAccessInitial = mock<CheckAccessInitial>()
    private val getStatusSend = mock<GetStatusSend>()
    private val viewModel = InitialMenuViewModel(
        checkAccessInitial = checkAccessInitial,
        getStatusSend = getStatusSend
    )

    @Test
    fun `checkAccess - Check return failure if have error in CheckAccessInitial`() =
        runTest {
            whenever(
                checkAccessInitial()
            ).thenReturn(
                resultFailure(
                    "CheckAccessInitial",
                    "-",
                    Exception()
                )
            )
            viewModel.checkAccess()
            val uiState = viewModel.uiState.value
            assertEquals(
                uiState.flagDialog,
                true
            )
            assertEquals(
                uiState.flagAccess,
                false
            )
            assertEquals(
                uiState.flagFailure,
                true
            )
            assertEquals(
                uiState.failure,
                "InitialMenuViewModel.checkAccess -> CheckAccessInitial -> java.lang.Exception",
            )
        }

    @Test
    fun `checkAccess - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                checkAccessInitial()
            ).thenReturn(
                Result.success(false)
            )
            viewModel.checkAccess()
            val uiState = viewModel.uiState.value
            assertEquals(
                uiState.flagDialog,
                true
            )
            assertEquals(
                uiState.flagAccess,
                false
            )
            assertEquals(
                uiState.flagFailure,
                false
            )
        }

    @Test
    fun `recoverStatusSend - Check return failure if have error in GetStatusSend`() =
        runTest {
            whenever(
                getStatusSend()
            ).thenReturn(
                resultFailure(
                    "GetStatusSend",
                    "-",
                    Exception()
                )
            )
            viewModel.recoverStatusSend()
            val uiState = viewModel.uiState.value
            assertEquals(
                uiState.failure,
                "InitialMenuViewModel.recoverStatusSend -> GetStatusSend -> java.lang.Exception",
            )
        }

    @Test
    fun `recoverStatusSend - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                getStatusSend()
            ).thenReturn(
                Result.success(
                    StatusSend.SENT
                )
            )
            viewModel.recoverStatusSend()
            val uiState = viewModel.uiState.value
            assertEquals(
                uiState.failure,
                ""
            )
            assertEquals(
                uiState.statusSend,
                StatusSend.SENT
            )
        }

}