package br.com.usinasantafe.cav.presenter.view.card.local

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.SetLocal
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class LocalViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val setLocal = mock<SetLocal>()
    private val viewModel = LocalViewModel(
        setLocal = setLocal
    )

    @Test
    fun `set - Check return failure if have error in SetLocal`() =
        runTest {
            whenever(
                setLocal(
                    address = "Test",
                    longitude = 0.0,
                    latitude = 0.0
                )
            ).thenReturn(
                resultFailure(
                    context = "SetLocal",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onLocalChanged(
                address = "Test",
                longitude = 0.0,
                latitude = 0.0
            )
            viewModel.set()
            assertEquals(
                viewModel.uiState.value.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.failure,
                "LocalViewModel.set -> SetLocal -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.flagAccess,
                false
            )
        }

    @Test
    fun `set - Check return true if SetLocal execute successfully`() =
        runTest {
            viewModel.onLocalChanged(
                address = "Test",
                longitude = 0.0,
                latitude = 0.0
            )
            viewModel.set()
            assertEquals(
                viewModel.uiState.value.flagAccess,
                true
            )
            assertEquals(
                viewModel.uiState.value.flagDialog,
                false
            )
        }

}