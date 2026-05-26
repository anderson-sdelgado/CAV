package br.com.usinasantafe.cav.presenter.view.card.typeAccident

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.ListTypeAccident
import br.com.usinasantafe.cav.domain.usecases.card.SetTypeAccidentList
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableTypeAccident
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.UiStatusStateUpdate
import br.com.usinasantafe.cav.utils.percentage
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class TypeAccidentViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val listTypeAccident = mock<ListTypeAccident>()
    private val updateTableTypeAccident = mock<UpdateTableTypeAccident>()
    private val setTypeAccidentList = mock<SetTypeAccidentList>()
    private val viewModel = TypeAccidentViewModel(
        listTypeAccident = listTypeAccident,
        updateTableTypeAccident = updateTableTypeAccident,
        setTypeAccidentList = setTypeAccidentList
    )

    @Test
    fun `list - Check return failure if have error in ListTypeAccident`() =
        runTest {
            whenever(
                listTypeAccident()
            ).thenReturn(
                resultFailure(
                    context = "ListTypeAccident",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.list()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "TypeAccidentViewModel.list -> ListTypeAccident -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
        }

    @Test
    fun `list - Check return true if ListTypeAccident execute successfully`() =
        runTest {
            whenever(
                listTypeAccident()
            ).thenReturn(
                Result.success(
                    listOf(
                        ItemCheckBoxScreenModel(
                            id = 1,
                            desc = "Test",
                            flag = false
                        )
                    )
                )
            )
            viewModel.list()
            val list = viewModel.list.toList()
            assertEquals(
                list.size,
                1
            )
            assertEquals(
                list,
                listOf(
                    ItemCheckBoxScreenModel(
                        id = 1,
                        desc = "Test",
                        flag = false
                    )
                )
            )
        }

    @Test
    fun `updateDatabase - Check return failure usecase if have error in usecase CleanTypeAccident`() =
        runTest {
            whenever(
                updateTableTypeAccident(
                    count = 1f,
                    sizeAll = 4f
                )
            ).thenReturn(
                flowOf(
                    UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_type_accident",
                        currentProgress = percentage(1f, 4f)
                    ),
                    UiStatusStateUpdate(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "CleanTypeAccident -> java.lang.NullPointerException",
                        currentProgress = 1f,
                    )
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(result.count(), 2)
            assertEquals(
                result[0],
                TypeAccidentStateUpdate(
                    status = UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_type_accident",
                        currentProgress = percentage(1f, 4f)
                    )
                )
            )
            assertEquals(
                result[1],
                TypeAccidentStateUpdate(
                    status = UiStatusStateUpdate(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "TypeAccidentViewModel.updateAllDatabase -> CleanTypeAccident -> java.lang.NullPointerException",
                        currentProgress = 1f,
                    )
                )
            )
            viewModel.updateDatabase()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "TypeAccidentViewModel.updateDatabase -> TypeAccidentViewModel.updateAllDatabase -> CleanTypeAccident -> java.lang.NullPointerException"
            )
        }

    @Test
    fun `updateDatabase - Check return success in updateAllDatabase`() =
        runTest {
            whenever(
                listTypeAccident()
            ).thenReturn(
                Result.success(
                    emptyList()
                )
            )
            whenever(
                updateTableTypeAccident(
                    count = 1f,
                    sizeAll = 4f
                )
            ).thenReturn(
                flowOf(
                    UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_type_accident",
                        currentProgress = percentage(1f, 4f)
                    ),
                    UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.CLEAN,
                        tableUpdate = "tb_type_accident",
                        currentProgress = percentage(2f, 4f)
                    ),
                    UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.SAVE,
                        tableUpdate = "tb_type_accident",
                        currentProgress = percentage(3f, 4f)
                    ),
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(result.count(), 4)
            assertEquals(
                result[0],
                TypeAccidentStateUpdate(
                    status = UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_type_accident",
                        currentProgress = percentage(1f, 4f)
                    )
                )
            )
            assertEquals(
                result[1],
                TypeAccidentStateUpdate(
                    status = UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.CLEAN,
                        tableUpdate = "tb_type_accident",
                        currentProgress = percentage(2f, 4f),
                    )
                )
            )
            assertEquals(
                result[2],
                TypeAccidentStateUpdate(
                    status = UiStatusStateUpdate(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.SAVE,
                        tableUpdate = "tb_type_accident",
                        currentProgress = percentage(3f, 4f),
                    )
                )
            )
            assertEquals(
                result[3],
                TypeAccidentStateUpdate(
                    status = UiStatusStateUpdate(
                        flagDialog = true,
                        flagProgress = false,
                        flagFailure = false,
                        levelUpdate = LevelUpdate.FINISH_UPDATE_COMPLETED,
                        currentProgress = 1f,
                    )
                )
            )
            viewModel.updateDatabase()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
        }

    @Test
    fun `onCheckChange - Check altered data`() =
        runTest {
            val listBefore = listOf(
                ItemCheckBoxScreenModel(
                    id = 1,
                    desc = "Test",
                    flag = false
                ),
                ItemCheckBoxScreenModel(
                    id = 2,
                    desc = "Test2",
                    flag = false
                )
            )
            viewModel.list.addAll(listBefore)
            viewModel.onCheckChange(2, true)
            val listAfter = viewModel.list.toList()
            assertEquals(
                listAfter.size,
                2
            )
            assertEquals(
                listAfter,
                listOf(
                    ItemCheckBoxScreenModel(
                        id = 1,
                        desc = "Test",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 2,
                        desc = "Test2",
                        flag = true
                    )
                )
            )
        }

    @Test
    fun `save - Check return failure if have error in SetListTypeAccident`() =
        runTest {
            val list = listOf(
                ItemCheckBoxScreenModel(
                    id = 1,
                    desc = "Test",
                    flag = false
                )
            )
            viewModel.list.addAll(list)
            whenever(
                setTypeAccidentList(list)
            ).thenReturn(
                resultFailure(
                    context = "SetListTypeAccident",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.save()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "TypeAccidentViewModel.save -> SetListTypeAccident -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
        }

    @Test
    fun `save - Check return true if SetListTypeAccident execute successfully`() =
        runTest {
            val list = listOf(
                ItemCheckBoxScreenModel(
                    id = 1,
                    desc = "Test",
                    flag = false
                )
            )
            viewModel.list.addAll(list)
            viewModel.save()
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                true
            )
        }

}