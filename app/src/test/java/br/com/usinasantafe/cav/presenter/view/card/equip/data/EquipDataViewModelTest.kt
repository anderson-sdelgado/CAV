package br.com.usinasantafe.cav.presenter.view.card.equip.data

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.DeleteEquipSec
import br.com.usinasantafe.cav.domain.usecases.card.GetDescEquip
import br.com.usinasantafe.cav.domain.usecases.card.GetDetail
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
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
class EquipDataViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getDescEquip = mock<GetDescEquip>()
    private val getDetail = mock<GetDetail>()
    private val deleteEquipSec = mock<DeleteEquipSec>()
    private val viewModel = EquipDataViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.FLOW_NOTE_ARG to FlowNote.EQUIP.ordinal,
                Args.ID_MAIN_ARG to 0,
                Args.ID_SECONDARY_ARG to 0
            )
        ),
        getDescEquip = getDescEquip,
        getDetail = getDetail,
        deleteEquipSec = deleteEquipSec
    )
    
    @Test
    fun `recoverData - Check return failure if have error in GetDescEquip`() =
        runTest {
            whenever(
                getDescEquip(
                    flowNote = FlowNote.EQUIP,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "GetDescEquip",
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
                "EquipDataViewModel.recoverData -> GetDescEquip -> java.lang.Exception"
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
                getDescEquip(
                    flowNote = FlowNote.EQUIP,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("2200 - TRATOR")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "EquipDataViewModel.recoverData -> Parameter specified as non-null is null: method br.com.usinasantafe.cav.presenter.view.card.equip.data.EquipDataState.<init>, parameter detail -> null"
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
                getDetail(
                    option = Option.EDIT,
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
                "EquipDataViewModel.recoverData -> GetDetail -> java.lang.Exception"
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
                getDescEquip(
                    flowNote = FlowNote.EQUIP,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("2200 - TRATOR")
            )
            whenever(
                getDetail(
                    option = Option.EDIT,
                    flowNote = FlowNote.EQUIP,
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
                viewModel.uiState.value.equip,
                "2200 - TRATOR"
            )
            assertEquals(
                viewModel.uiState.value.detail,
                "Test"
            )
        }
}