package br.com.usinasantafe.cav.presenter.view.card.nature

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.ListNature
import br.com.usinasantafe.cav.domain.usecases.card.SetNatureList
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableNature
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
import br.com.usinasantafe.cav.utils.UpdateStatusState
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
class NatureViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val listNature = mock<ListNature>()
    private val updateTableNature = mock<UpdateTableNature>()
    private val setNatureList = mock<SetNatureList>()
    private val viewModel = NatureViewModel(
        listNature = listNature,
        updateTableNature = updateTableNature,
        setNatureList = setNatureList
    )

    @Test
    fun `list - Check return failure if have error in ListNature`() =
        runTest {
            whenever(
                listNature()
            ).thenReturn(
                resultFailure(
                    context = "ListNature",
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
                "NatureViewModel.list -> ListNature -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.flagAccess,
                false
            )
        }

    @Test
    fun `list - Check return true if ListNature execute successfully`() =
        runTest {
            whenever(
                listNature()
            ).thenReturn(
                Result.success(
                    listOf(
                        ItemCheckBoxModel(
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
                    ItemCheckBoxModel(
                        id = 1,
                        desc = "Test",
                        flag = false
                    )
                )
            )
        }

    @Test
    fun `updateDatabase - Check return failure usecase if have error in usecase CleanNature`() =
        runTest {
            whenever(
                updateTableNature(
                    count = 1f,
                    sizeAll = 4f
                )
            ).thenReturn(
                flowOf(
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_nature",
                        currentProgress = percentage(1f, 4f)
                    ),
                    UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "CleanNature -> java.lang.NullPointerException",
                        currentProgress = 1f,
                    )
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(result.count(), 2)
            assertEquals(
                result[0],
                NatureState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_nature",
                        currentProgress = percentage(1f, 4f)
                    )
                )
            )
            assertEquals(
                result[1],
                NatureState(
                    status = UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "NatureViewModel.updateAllDatabase -> CleanNature -> java.lang.NullPointerException",
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
                "NatureViewModel.updateDatabase -> NatureViewModel.updateAllDatabase -> CleanNature -> java.lang.NullPointerException"
            )
        }

    @Test
    fun `updateDatabase - Check return success in updateAllDatabase`() =
        runTest {
            whenever(
                updateTableNature(
                    count = 1f,
                    sizeAll = 4f
                )
            ).thenReturn(
                flowOf(
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_nature",
                        currentProgress = percentage(1f, 4f)
                    ),
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.CLEAN,
                        tableUpdate = "tb_nature",
                        currentProgress = percentage(2f, 4f)
                    ),
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.SAVE,
                        tableUpdate = "tb_nature",
                        currentProgress = percentage(3f, 4f)
                    ),
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(result.count(), 4)
            assertEquals(
                result[0],
                NatureState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_nature",
                        currentProgress = percentage(1f, 4f)
                    )
                )
            )
            assertEquals(
                result[1],
                NatureState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.CLEAN,
                        tableUpdate = "tb_nature",
                        currentProgress = percentage(2f, 4f),
                    )
                )
            )
            assertEquals(
                result[2],
                NatureState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.SAVE,
                        tableUpdate = "tb_nature",
                        currentProgress = percentage(3f, 4f),
                    )
                )
            )
            assertEquals(
                result[3],
                NatureState(
                    status = UpdateStatusState(
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
                ItemCheckBoxModel(
                    id = 1,
                    desc = "Test",
                    flag = false
                ),
                ItemCheckBoxModel(
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
                    ItemCheckBoxModel(
                        id = 1,
                        desc = "Test",
                        flag = false
                    ),
                    ItemCheckBoxModel(
                        id = 2,
                        desc = "Test2",
                        flag = true
                    )
                )
            )
        }

    @Test
    fun `save - Check return failure if have error in SetListNature`() =
        runTest {
            val list = listOf(
                ItemCheckBoxModel(
                    id = 1,
                    desc = "Test",
                    flag = false
                )
            )
            viewModel.list.addAll(list)
            whenever(
                setNatureList(list)
            ).thenReturn(
                resultFailure(
                    context = "SetListNature",
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
                "NatureViewModel.save -> SetListNature -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.flagAccess,
                false
            )
        }

    @Test
    fun `save - Check return true if SetListNature execute successfully`() =
        runTest {
            val list = listOf(
                ItemCheckBoxModel(
                    id = 1,
                    desc = "Test",
                    flag = false
                )
            )
            viewModel.list.addAll(list)
            viewModel.save()
            assertEquals(
                viewModel.uiState.value.flagAccess,
                true
            )
        }

}