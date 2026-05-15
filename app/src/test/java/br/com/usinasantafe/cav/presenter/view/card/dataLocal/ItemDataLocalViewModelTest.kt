package br.com.usinasantafe.cav.presenter.view.card.dataLocal

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.ListItemDataLocal
import br.com.usinasantafe.cav.domain.usecases.card.SetDataLocalList
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableItemDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableOptionDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableROptionItemDataLocal
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.presenter.Args
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.percentage
import br.com.usinasantafe.cav.utils.resultFailure
import br.com.usinasantafe.cav.utils.sizeUpdate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ItemDataLocalViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private var tableList = mutableListOf<String>()
    private val qtdTable = 3f

    private val listItemDataLocal = mock<ListItemDataLocal>()
    private val updateTableItemDataLocal = mock<UpdateTableItemDataLocal>()
    private val updateTableOptionDataLocal = mock<UpdateTableOptionDataLocal>()
    private val updateTableROptionItemDataLocal = mock<UpdateTableROptionItemDataLocal>()
    private val setDataLocalList = mock<SetDataLocalList>()
    private val viewModel = ItemDataLocalViewModel(
        saveStateHandle = SavedStateHandle(
            mapOf(
                Args.ID_ARG to 1,
            )
        ),
        listItemDataLocal = listItemDataLocal,
        updateTableItemDataLocal = updateTableItemDataLocal,
        updateTableOptionDataLocal = updateTableOptionDataLocal,
        updateTableROptionItemDataLocal = updateTableROptionItemDataLocal,
        setDataLocalList = setDataLocalList
    )

    @Test
    fun `list - Check return failure if have error in ListItemDataLocal`() =
        runTest {
            whenever(
                listItemDataLocal(1)
            ).thenReturn(
                resultFailure(
                    context = "ListItemDataLocal",
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
                "ItemDataLocalViewModel.list -> ListItemDataLocal -> java.lang.Exception"
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
                listItemDataLocal(1)
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
    fun `updateDatabase - Check return failure if have error in CleanItemDataLocal`() =
        runTest {
            val qtdBefore = 0f
            whenever(
                updateTableItemDataLocal(
                    sizeAll = sizeUpdate(qtdTable),
                    count = (qtdBefore + 1)
                )
            ).thenReturn(
                flowOf(
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_item_data_local",
                        currentProgress = percentage(((qtdBefore * 3) + 1), qtdTable)
                    ),
                    UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "CleanItemDataLocal -> java.lang.NullPointerException",
                    )
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(
                result.count(),
                ((qtdBefore * 3) + 2).toInt()
            )
            checkResultUpdate(qtdBefore, result)
            assertEquals(
                result[(qtdBefore * 3).toInt()],
                ItemDataLocalState(
                    id = 1,
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_item_data_local",
                        currentProgress = percentage(((qtdBefore * 3) + 1), qtdTable)
                    )
                )
            )
            assertEquals(
                result[((qtdBefore * 3) + 1).toInt()],
                ItemDataLocalState(
                    id = 1,
                    status = UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "ItemDataLocalViewModel.updateAllDatabase -> CleanItemDataLocal -> java.lang.NullPointerException",
                    )
                )
            )
            viewModel.updateDatabase()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagProgress,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "ItemDataLocalViewModel.updateDatabase -> ItemDataLocalViewModel.updateAllDatabase -> CleanItemDataLocal -> java.lang.NullPointerException"
            )
        }

    @Test
    fun `updateDatabase - Check return failure if have error in CleanOptionDataLocal`() =
        runTest {
            val qtdBefore = 1f
            wheneverSuccess(qtdBefore)
            whenever(
                updateTableOptionDataLocal(
                    sizeAll = sizeUpdate(qtdTable),
                    count = (qtdBefore + 1)
                )
            ).thenReturn(
                flowOf(
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_option_data_local",
                        currentProgress = percentage(((qtdBefore * 3) + 1), qtdTable)
                    ),
                    UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "CleanOptionDataLocal -> java.lang.NullPointerException",
                    )
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(
                result.count(),
                ((qtdBefore * 3) + 2).toInt()
            )
            checkResultUpdate(qtdBefore, result)
            assertEquals(
                result[(qtdBefore * 3).toInt()],
                ItemDataLocalState(
                    id = 1,
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_option_data_local",
                        currentProgress = percentage(((qtdBefore * 3) + 1), qtdTable)
                    )
                )
            )
            assertEquals(
                result[((qtdBefore * 3) + 1).toInt()],
                ItemDataLocalState(
                    id = 1,
                    status = UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "ItemDataLocalViewModel.updateAllDatabase -> CleanOptionDataLocal -> java.lang.NullPointerException",
                    )
                )
            )
            viewModel.updateDatabase()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagProgress,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "ItemDataLocalViewModel.updateDatabase -> ItemDataLocalViewModel.updateAllDatabase -> CleanOptionDataLocal -> java.lang.NullPointerException"
            )
        }

    @Test
    fun `updateDatabase - Check return failure if have error in CleanROptionItemDataLocal`() =
        runTest {
            val qtdBefore = 2f
            wheneverSuccess(qtdBefore)
            whenever(
                updateTableROptionItemDataLocal(
                    sizeAll = sizeUpdate(qtdTable),
                    count = (qtdBefore + 1)
                )
            ).thenReturn(
                flowOf(
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_r_option_item_data_local",
                        currentProgress = percentage(((qtdBefore * 3) + 1), qtdTable)
                    ),
                    UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "CleanROptionItemDataLocal -> java.lang.NullPointerException",
                    )
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(
                result.count(),
                ((qtdBefore * 3) + 2).toInt()
            )
            checkResultUpdate(qtdBefore, result)
            assertEquals(
                result[(qtdBefore * 3).toInt()],
                ItemDataLocalState(
                    id = 1,
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_r_option_item_data_local",
                        currentProgress = percentage(((qtdBefore * 3) + 1), qtdTable)
                    )
                )
            )
            assertEquals(
                result[((qtdBefore * 3) + 1).toInt()],
                ItemDataLocalState(
                    id = 1,
                    status = UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "ItemDataLocalViewModel.updateAllDatabase -> CleanROptionItemDataLocal -> java.lang.NullPointerException",
                    )
                )
            )
            viewModel.updateDatabase()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagProgress,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "ItemDataLocalViewModel.updateDatabase -> ItemDataLocalViewModel.updateAllDatabase -> CleanROptionItemDataLocal -> java.lang.NullPointerException"
            )
        }

    @Test
    fun `updateDatabase - Check return success in updateAllDatabase`() =
        runTest {
            whenever(
                listItemDataLocal(1)
            ).thenReturn(
                Result.success(
                    emptyList()
                )
            )
            val qtdBefore = 3f
            wheneverSuccess(qtdBefore)
            val result = viewModel.updateAllDatabase().toList()
            val qtd = sizeUpdate(qtdBefore)
            assertEquals(
                result.count(),
                qtd.toInt()
            )
            checkResultUpdate(qtdBefore, result)
            assertEquals(
                result[9],
                ItemDataLocalState(
                    id = 1,
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
            assertEquals(
                viewModel.uiState.value.status.flagProgress,
                false
            )
            assertEquals(
                viewModel.uiState.value.status.levelUpdate,
                LevelUpdate.FINISH_UPDATE_COMPLETED
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
    fun `save - Check return failure if have error in SetDataLocalList`() =
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
                setDataLocalList(
                    idOption = 1,
                    list = list
                )
            ).thenReturn(
                resultFailure(
                    context = "SetDataLocalList",
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
                "ItemDataLocalViewModel.save -> SetDataLocalList -> java.lang.Exception"
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
    fun `save - Check return true if SetListNature execute successfully`() =
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

    ///////////////////////////////////////////////////////////////////////////////////////

    private fun wheneverSuccess(posTable: Float) =
        runTest {
            var contUpdate = 0f
            var contWhenever = 0f

            val sizeAll = sizeUpdate(qtdTable)
            tableList = mutableListOf(
                "tb_item_data_local", "tb_option_data_local", "tb_r_option_item_data_local",
            )

            val updateFunctions = mutableListOf<
                    suspend (Float, Float) -> Flow<UpdateStatusState>
                    >(
                { sizeAll, count -> updateTableItemDataLocal(sizeAll, count) },
                { sizeAll, count -> updateTableOptionDataLocal(sizeAll, count) },
                { sizeAll, count -> updateTableROptionItemDataLocal(sizeAll, count) },
            )

            for(func in updateFunctions) {
                whenever(
                    func(
                        sizeAll,
                        ++contUpdate
                    )
                ).thenReturn(
                    flowOf(
                        UpdateStatusState(
                            flagProgress = true,
                            levelUpdate = LevelUpdate.RECOVERY,
                            tableUpdate = tableList[contUpdate.toInt() - 1],
                            currentProgress = percentage(++contWhenever, sizeAll)
                        ),
                        UpdateStatusState(
                            flagProgress = true,
                            levelUpdate = LevelUpdate.CLEAN,
                            tableUpdate = tableList[contUpdate.toInt() - 1],
                            currentProgress = percentage(++contWhenever, sizeAll)
                        ),
                        UpdateStatusState(
                            flagProgress = true,
                            levelUpdate = LevelUpdate.SAVE,
                            tableUpdate = tableList[contUpdate.toInt() - 1],
                            currentProgress = percentage(++contWhenever, sizeAll)
                        ),
                    )
                )
                if(posTable == contUpdate) break
            }
        }

    private fun checkResultUpdate(posTable: Float, result: List<ItemDataLocalState>) =
        runTest {
            val sizeAll = sizeUpdate(qtdTable)
            var contUpdate = 0f
            var cont = 0
            for(table in tableList) {
                assertEquals(
                    result[cont++],
                    ItemDataLocalState(
                        id = 1,
                        status = UpdateStatusState(
                            flagProgress = true,
                            levelUpdate = LevelUpdate.RECOVERY,
                            tableUpdate = table,
                            currentProgress = percentage(cont.toFloat(), sizeAll)
                        )
                    )
                )
                assertEquals(
                    result[cont++],
                    ItemDataLocalState(
                        id = 1,
                        status = UpdateStatusState(
                            flagProgress = true,
                            levelUpdate = LevelUpdate.CLEAN,
                            tableUpdate = table,
                            currentProgress = percentage(cont.toFloat(), sizeAll)
                        )
                    )
                )
                assertEquals(
                    result[cont++],
                    ItemDataLocalState(
                        id = 1,
                        status = UpdateStatusState(
                            flagProgress = true,
                            levelUpdate = LevelUpdate.SAVE,
                            tableUpdate = table,
                            currentProgress = percentage(cont.toFloat(), sizeAll)
                        )
                    )
                )
                ++contUpdate
                if(posTable == contUpdate) break
            }
        }

}