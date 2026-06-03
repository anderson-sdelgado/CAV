package br.com.usinasantafe.cav.presenter.view.card.equip.equip

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetNroEquip
import br.com.usinasantafe.cav.domain.usecases.card.SetEquip
import br.com.usinasantafe.cav.domain.usecases.common.HasNroEquip
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableEquip
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.Args
import br.com.usinasantafe.cav.utils.UiStatusStateUpdate
import br.com.usinasantafe.cav.utils.percentage
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class EquipViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getNroEquip = mock<GetNroEquip>()
    private val updateTableEquip = mock<UpdateTableEquip>()
    private val hasNroEquip = mock<HasNroEquip>()
    private val setEquip = mock<SetEquip>()
    private val viewModel = EquipViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                Args.OPTION_ARG to Option.INSERT.ordinal,
                Args.FLOW_NOTE_ARG to FlowNote.EQUIP.ordinal,
                Args.ID_MAIN_ARG to  0,
                Args.ID_SECONDARY_ARG to 0
            )
        ),
        getNroEquip = getNroEquip,
        updateTableEquip = updateTableEquip,
        hasNroEquip = hasNroEquip,
        setEquip = setEquip
    )

    @Test
    fun `recoverData - Check return failure if have error in GetEquip`() =
        runTest {
            whenever(
                getNroEquip(
                    flowNote = FlowNote.EQUIP,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "GetNroEquip",
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
                "EquipViewModel.recoverData -> GetNroEquip -> java.lang.Exception"
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
                getNroEquip(
                    flowNote = FlowNote.EQUIP,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                Result.success("2200")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
            assertEquals(
                viewModel.uiState.value.text,
                "2200"
            )
        }

    @Test
    fun `setTextField - Check add char`() {
        viewModel.onTextField(
            "1",
            TypeButton.NUMERIC
        )
        assertEquals(
            "1",
            viewModel.uiState.value.text
        )
    }

    @Test
    fun `setTextField - Check remover char`() {
        viewModel.onTextField(
            "19759",
            TypeButton.NUMERIC
        )
        viewModel.onTextField(
            "APAGAR",
            TypeButton.CLEAN
        )
        viewModel.onTextField(
            "APAGAR",
            TypeButton.CLEAN
        )
        viewModel.onTextField(
            "APAGAR",
            TypeButton.CLEAN
        )
        viewModel.onTextField(
            "1",
            TypeButton.NUMERIC
        )
        assertEquals(
            viewModel.uiState.value.text,
            "191"
        )
    }

    @Test
    fun `setTextField - Check msg of empty field`() {
        viewModel.onTextField(
            "OK",
            TypeButton.OK
        )
        assertEquals(
            viewModel.uiState.value.status.flagDialog,
            true
        )
        assertEquals(
            viewModel.uiState.value.status.errors,
            Errors.FIELD_EMPTY
        )
    }

    @Test
    fun `setTextField - Check return failure usecase if have error in usecase CleanEquip`() =
        runTest {
            whenever(
                updateTableEquip(
                    count = 1f,
                    sizeAll = 4f
                )
            ).thenReturn(
                flowOf(
                    UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_equip",
                        currentProgress = percentage(1f, 4f)
                    ),
                    UiStatusStateUpdate(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "CleanEquip -> java.lang.NullPointerException",
                        currentProgress = 1f,
                    )
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(result.count(), 2)
            assertEquals(
                result[0],
                EquipStateUpdate(
                    status = UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_equip",
                        currentProgress = percentage(1f, 4f)
                    )
                )
            )
            assertEquals(
                result[1],
                EquipStateUpdate(
                    status = UiStatusStateUpdate(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "EquipViewModel.updateAllDatabase -> CleanEquip -> java.lang.NullPointerException",
                        currentProgress = 1f,
                    )
                )
            )
            viewModel.onTextField(
                "ATUALIZAR DADOS",
                TypeButton.UPDATE
            )
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "EquipViewModel.onTextField -> EquipViewModel.updateAllDatabase -> CleanEquip -> java.lang.NullPointerException"
            )
        }

    @Test
    fun `setTextField - Check return success in updateAllDatabase`() =
        runTest {
            whenever(
                updateTableEquip(
                    count = 1f,
                    sizeAll = 4f
                )
            ).thenReturn(
                flowOf(
                    UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_equip",
                        currentProgress = percentage(1f, 4f)
                    ),
                    UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.CLEAN,
                        tableUpdate = "tb_equip",
                        currentProgress = percentage(2f, 4f)
                    ),
                    UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.SAVE,
                        tableUpdate = "tb_equip",
                        currentProgress = percentage(3f, 4f)
                    ),
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(result.count(), 4)
            assertEquals(
                result[0],
                EquipStateUpdate(
                    status = UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_equip",
                        currentProgress = percentage(1f, 4f)
                    )
                )
            )
            assertEquals(
                result[1],
                EquipStateUpdate(
                    status = UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.CLEAN,
                        tableUpdate = "tb_equip",
                        currentProgress = percentage(2f, 4f),
                    )
                )
            )
            assertEquals(
                result[2],
                EquipStateUpdate(
                    status = UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.SAVE,
                        tableUpdate = "tb_equip",
                        currentProgress = percentage(3f, 4f),
                    )
                )
            )
            assertEquals(
                result[3],
                EquipStateUpdate(
                    status = UiStatusStateUpdate(
                        flagDialog = true,
                        flagProgress = false,
                        flagFailure = false,
                        levelUpdate = LevelUpdate.FINISH_UPDATE_COMPLETED,
                        currentProgress = 1f,
                    )
                )
            )
            viewModel.onTextField(
                "ATUALIZAR DADOS",
                TypeButton.UPDATE
            )
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
        }

    @Test
    fun `setTextField - Check return failure if field is empty`() =
        runTest {
            viewModel.onTextField(
                "OK",
                TypeButton.OK
            )
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.FIELD_EMPTY
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "EquipViewModel.onTextField -> EquipViewModel.updateState -> EquipViewModel.set -> FIELD_EMPTY"
            )
            assertEquals(
                viewModel.uiState.value.status.flagProgress,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
        }

    @Test
    fun `setTextField - Check return failure if have error in usecase HasNroEquip`() =
        runTest {
            whenever(
                hasNroEquip("200")
            ).thenReturn(
                resultFailure(
                    context = "IHasNroEquip",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onTextField(
                "200",
                TypeButton.NUMERIC
            )
            viewModel.onTextField(
                "OK",
                TypeButton.OK
            )
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "EquipViewModel.onTextField -> EquipViewModel.set -> IHasNroEquip -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.flagProgress,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
        }

    @Test
    fun `setTextField - Check return false if not have nro in table`() =
        runTest {
            whenever(
                hasNroEquip("200")
            ).thenReturn(
                Result.success(false)
            )
            viewModel.onTextField(
                "200",
                TypeButton.NUMERIC
            )
            viewModel.onTextField(
                "OK",
                TypeButton.OK
            )
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.INVALID
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
        }

    @Test
    fun `setTextField - Check return failure if have error in usecase SetIdEquip`() =
        runTest {
            whenever(
                hasNroEquip("200")
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                setEquip(
                    nroEquip = "200",
                    option = Option.INSERT,
                    flowNote = FlowNote.EQUIP,
                    idMain = 0,
                    idSecondary = 0
                )
            ).thenReturn(
                resultFailure(
                    context = "ISetIdEquip",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onTextField(
                "200",
                TypeButton.NUMERIC
            )
            viewModel.onTextField(
                "OK",
                TypeButton.OK
            )
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "EquipViewModel.onTextField -> EquipViewModel.set -> ISetIdEquip -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.flagProgress,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
        }

    @Test
    fun `setTextField - Check access release if executed successfully`() =
        runTest {
            whenever(
                hasNroEquip("200")
            ).thenReturn(
                Result.success(true)
            )
            viewModel.onTextField(
                "200",
                TypeButton.NUMERIC
            )
            viewModel.onTextField(
                "OK",
                TypeButton.OK
            )
            verify(setEquip, atLeastOnce()).invoke(
                nroEquip = "200",
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                true
            )
        }

}