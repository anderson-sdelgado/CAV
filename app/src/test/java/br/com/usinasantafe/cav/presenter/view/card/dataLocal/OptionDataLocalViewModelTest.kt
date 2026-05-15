package br.com.usinasantafe.cav.presenter.view.card.dataLocal

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.ListOptionDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableItemDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableOptionDataLocal
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableROptionItemDataLocal
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.presenter.view.card.attendant.AttendantState
import br.com.usinasantafe.cav.presenter.view.configuration.config.ConfigState
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
class OptionDataLocalViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private var tableList = mutableListOf<String>()
    private val qtdTable = 3f

    private val listOptionDataLocal = mock<ListOptionDataLocal>()
    private val updateTableItemDataLocal = mock<UpdateTableItemDataLocal>()
    private val updateTableOptionDataLocal = mock<UpdateTableOptionDataLocal>()
    private val updateTableROptionItemDataLocal = mock<UpdateTableROptionItemDataLocal>()
    private val viewModel = OptionDataLocalViewModel(
        listOptionDataLocal = listOptionDataLocal,
        updateTableItemDataLocal = updateTableItemDataLocal,
        updateTableOptionDataLocal = updateTableOptionDataLocal,
        updateTableROptionItemDataLocal = updateTableROptionItemDataLocal
    )

    @Test
    fun `list - Check return failure if have error in ListOptionDataLocal`() =
        runTest {
            whenever(
                listOptionDataLocal()
            ).thenReturn(
                resultFailure(
                    context = "ListOptionDataLocal",
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
                "OptionDataLocalViewModel.list -> ListOptionDataLocal -> java.lang.Exception"
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
    fun `list - Check return true if process execute successfully`() =
        runTest {
            whenever(
                listOptionDataLocal()
            ).thenReturn(
                Result.success(
                    listOf(
                        ItemListScreenModel(
                            id = 1,
                            description = "Test"
                        )
                    )
                )
            )
            viewModel.list()
            assertEquals(
                viewModel.uiState.value.list,
                listOf(
                    ItemListScreenModel(
                        id = 1,
                        description = "Test"
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
                OptionDataLocalState(
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
                OptionDataLocalState(
                    status = UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "OptionDataLocalViewModel.updateAllDatabase -> CleanItemDataLocal -> java.lang.NullPointerException",
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
                "OptionDataLocalViewModel.updateDatabase -> OptionDataLocalViewModel.updateAllDatabase -> CleanItemDataLocal -> java.lang.NullPointerException"
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
                OptionDataLocalState(
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
                OptionDataLocalState(
                    status = UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "OptionDataLocalViewModel.updateAllDatabase -> CleanOptionDataLocal -> java.lang.NullPointerException",
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
                "OptionDataLocalViewModel.updateDatabase -> OptionDataLocalViewModel.updateAllDatabase -> CleanOptionDataLocal -> java.lang.NullPointerException"
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
                OptionDataLocalState(
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
                OptionDataLocalState(
                    status = UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "OptionDataLocalViewModel.updateAllDatabase -> CleanROptionItemDataLocal -> java.lang.NullPointerException",
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
                "OptionDataLocalViewModel.updateDatabase -> OptionDataLocalViewModel.updateAllDatabase -> CleanROptionItemDataLocal -> java.lang.NullPointerException"
            )
        }

    @Test
    fun `updateDatabase - Check return success in updateAllDatabase`() =
        runTest {
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
                OptionDataLocalState(
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

    private fun checkResultUpdate(posTable: Float, result: List<OptionDataLocalState>) =
        runTest {
            val sizeAll = sizeUpdate(qtdTable)
            var contUpdate = 0f
            var cont = 0
            for(table in tableList) {
                assertEquals(
                    result[cont++],
                    OptionDataLocalState(
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
                    OptionDataLocalState(
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
                    OptionDataLocalState(
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