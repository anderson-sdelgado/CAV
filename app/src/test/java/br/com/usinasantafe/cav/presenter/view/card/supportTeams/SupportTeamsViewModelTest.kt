package br.com.usinasantafe.cav.presenter.view.card.supportTeams

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.ListSupportTeams
import br.com.usinasantafe.cav.domain.usecases.card.SetSupportTeamsList
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableSupportTeams
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
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
class SupportTeamsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val listSupportTeams = mock<ListSupportTeams>()
    private val updateTableSupportTeams = mock<UpdateTableSupportTeams>()
    private val setSupportTeamsList = mock<SetSupportTeamsList>()
    private val viewModel = SupportTeamsViewModel(
        listSupportTeams = listSupportTeams,
        updateTableSupportTeams = updateTableSupportTeams,
        setSupportTeamsList = setSupportTeamsList
    )

    @Test
    fun `list - Check return failure if have error in ListSupportTeams`() =
        runTest {
            whenever(
                listSupportTeams()
            ).thenReturn(
                resultFailure(
                    context = "ListSupportTeams",
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
                "SupportTeamsViewModel.list -> ListSupportTeams -> java.lang.Exception"
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
    fun `list - Check return true if ListSupportTeams execute successfully`() =
        runTest {
            whenever(
                listSupportTeams()
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
    fun `updateDatabase - Check return failure usecase if have error in usecase CleanSupportTeams`() =
        runTest {
            whenever(
                updateTableSupportTeams(
                    count = 1f,
                    sizeAll = 4f
                )
            ).thenReturn(
                flowOf(
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_support_teams",
                        currentProgress = percentage(1f, 4f)
                    ),
                    UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "CleanSupportTeams -> java.lang.NullPointerException",
                        currentProgress = 1f,
                    )
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(result.count(), 2)
            assertEquals(
                result[0],
                SupportTeamsState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_support_teams",
                        currentProgress = percentage(1f, 4f)
                    )
                )
            )
            assertEquals(
                result[1],
                SupportTeamsState(
                    status = UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "SupportTeamsViewModel.updateAllDatabase -> CleanSupportTeams -> java.lang.NullPointerException",
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
                "SupportTeamsViewModel.updateDatabase -> SupportTeamsViewModel.updateAllDatabase -> CleanSupportTeams -> java.lang.NullPointerException"
            )
        }

    @Test
    fun `updateDatabase - Check return success in updateAllDatabase`() =
        runTest {
            whenever(
                listSupportTeams()
            ).thenReturn(
                Result.success(
                    emptyList()
                )
            )
            whenever(
                updateTableSupportTeams(
                    count = 1f,
                    sizeAll = 4f
                )
            ).thenReturn(
                flowOf(
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_support_teams",
                        currentProgress = percentage(1f, 4f)
                    ),
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.CLEAN,
                        tableUpdate = "tb_support_teams",
                        currentProgress = percentage(2f, 4f)
                    ),
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.SAVE,
                        tableUpdate = "tb_support_teams",
                        currentProgress = percentage(3f, 4f)
                    ),
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(result.count(), 4)
            assertEquals(
                result[0],
                SupportTeamsState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_support_teams",
                        currentProgress = percentage(1f, 4f)
                    )
                )
            )
            assertEquals(
                result[1],
                SupportTeamsState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.CLEAN,
                        tableUpdate = "tb_support_teams",
                        currentProgress = percentage(2f, 4f),
                    )
                )
            )
            assertEquals(
                result[2],
                SupportTeamsState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.SAVE,
                        tableUpdate = "tb_support_teams",
                        currentProgress = percentage(3f, 4f),
                    )
                )
            )
            assertEquals(
                result[3],
                SupportTeamsState(
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
    fun `save - Check return failure if have error in SetListSupportTeams`() =
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
                setSupportTeamsList(list)
            ).thenReturn(
                resultFailure(
                    context = "SetListSupportTeams",
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
                "SupportTeamsViewModel.save -> SetListSupportTeams -> java.lang.Exception"
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
    fun `save - Check return true if SetListSupportTeams execute successfully`() =
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
                viewModel.uiState.value.flagAccess,
                true
            )
        }

}