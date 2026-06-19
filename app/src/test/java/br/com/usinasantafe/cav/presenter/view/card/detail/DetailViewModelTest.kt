package br.com.usinasantafe.cav.presenter.view.card.detail

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetDetail
import br.com.usinasantafe.cav.domain.usecases.card.SetDetail
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.Args
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getDetail = mock<GetDetail>()
    private val setDetail = mock<SetDetail>()
    private val viewModel = DetailViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.OPTION_ARG to Option.INSERT.ordinal,
                Args.FLOW_NOTE_ARG to FlowNote.EQUIP.ordinal,
                Args.ID_MAIN_ARG to  0,
                Args.ID_SECONDARY_ARG to 0
            )
        ),
        getDetail = getDetail,
        setDetail = setDetail
    )

    @Test
    fun `recoverData - Check return failure if have error in GetDetailVehicleOwn`() =
        runTest {
            whenever(
                getDetail(
                    option = Option.INSERT,
                    flowNote = FlowNote.EQUIP,
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
                "DetailViewModel.recoverData -> GetDetail -> java.lang.Exception"
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
                getDetail(
                    option = Option.INSERT,
                    flowNote = FlowNote.EQUIP,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("Test")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.text,
                "Test"
            )
        }

    @Test
    fun `set - Check return failure if have error in SetDetailVehicleOwn`() =
        runTest {
            whenever(
                setDetail(
                    text = "Test",
                    option = Option.INSERT,
                    flowNote = FlowNote.EQUIP,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "SetDetail",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onTextChanged("Test")
            viewModel.set()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "DetailViewModel.set -> SetDetail -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
        }

    @Test
    fun `set - Check return true if process execute successfully and return null`() =
        runTest {
            viewModel.onTextChanged("Test")
            viewModel.set()
            verify(
                setDetail,
                atLeastOnce()
            ).invoke(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                false
            )
            assertEquals(
                viewModel.uiState.value.id,
                0
            )
        }

    @Test
    fun `set - Check return true if process execute successfully`() =
        runTest {
            whenever(
                setDetail(
                    text = "Test",
                    option = Option.INSERT,
                    flowNote = FlowNote.EQUIP,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success(10)
            )
            viewModel.onTextChanged("Test")
            viewModel.set()
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                false
            )
            assertEquals(
                viewModel.uiState.value.id,
                10
            )
        }

}