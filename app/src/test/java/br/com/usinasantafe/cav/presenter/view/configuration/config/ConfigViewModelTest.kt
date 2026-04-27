package br.com.usinasantafe.cav.presenter.view.configuration.config

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.entities.variable.Config
import br.com.usinasantafe.cav.domain.usecases.config.GetConfig
import br.com.usinasantafe.cav.domain.usecases.config.SaveConfig
import br.com.usinasantafe.cav.domain.usecases.config.SendConfig
import br.com.usinasantafe.cav.domain.usecases.config.SetFinishUpdateAllTable
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableColab
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableEquip
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.presenter.model.ConfigModel
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
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.text.toInt

@ExperimentalCoroutinesApi
class ConfigViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getConfig = mock<GetConfig>()
    private val saveConfig = mock<SaveConfig>()
    private val sendConfig = mock<SendConfig>()
    private val setFinishUpdateAllTable = mock<SetFinishUpdateAllTable>()
    private val updateTableColab = mock<UpdateTableColab>()
    private val updateTableEquip = mock<UpdateTableEquip>()
    private var tableList = mutableListOf<String>()
    private val viewModel = ConfigViewModel(
        getConfig = getConfig,
        saveConfig = saveConfig,
        sendConfig = sendConfig,
        setFinishUpdateAllTable = setFinishUpdateAllTable,
        updateTableColab = updateTableColab,
        updateTableEquip = updateTableEquip
    )

    @Test
    fun `returnConfig - Check return failure if have error in GetConfig`() =
        runTest {
            whenever(
                getConfig()
            ).thenReturn(
                resultFailure(
                    context = "GetConfig",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.returnConfig()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "ConfigViewModel.returnConfig -> GetConfig -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
        }

    @Test
    fun `returnConfig - Check return null if GetConfig execute successfully and return null`() =
        runTest {
            whenever(
                getConfig()
            ).thenReturn(
                Result.success(null)
            )
            viewModel.returnConfig()
            assertEquals(
                viewModel.uiState.value.number,
                ""
            )
            assertEquals(
                viewModel.uiState.value.password,
                ""
            )
        }

    @Test
    fun `returnConfig - Check return correct if GetConfig execute successfully`() =
        runTest {
            whenever(
                getConfig()
            ).thenReturn(
                Result.success(
                    ConfigModel(
                        number = "16997417840",
                        password = "12345"
                    )
                )
            )
            viewModel.returnConfig()
            assertEquals(
                viewModel.uiState.value.number,
                "16997417840"
            )
            assertEquals(
                viewModel.uiState.value.password,
                "12345"
            )
        }

    @Test
    fun `saveTokenAndUpdate - Check return failure if number, password or nroEquip is empty`() =
        runTest {
            viewModel.onSaveAndUpdate()
            val uiState = viewModel.uiState.value
            assertEquals(
                uiState.status.flagDialog,
                true
            )
            assertEquals(
                uiState.status.flagFailure,
                true
            )
            assertEquals(
                uiState.status.errors,
                Errors.FIELD_EMPTY
            )
        }

    @Test
    fun `saveTokenAndUpdate - Check return failure if have error in SendConfig`() =
        runTest {
            whenever(
                sendConfig(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00"
                )
            ).thenReturn(
                resultFailure(
                    "SendConfig",
                    "-",
                    Exception()
                )
            )
            viewModel.onNumberChanged("16997417840")
            viewModel.onPasswordChanged("12345")
            viewModel.setConfigMain(
                version = "1.00",
            )
            val result = viewModel.token().toList()
            assertEquals(result.count(), 2)
            assertEquals(
                result[0],
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.GET_TOKEN,
                        currentProgress = percentage(1f, 3f)
                    )
                )
            )
            assertEquals(
                result[1],
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        errors = Errors.TOKEN,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "ConfigViewModel.token -> SendConfig -> java.lang.Exception",
                        currentProgress = 1f,
                    )
                )
            )
            viewModel.onSaveAndUpdate()
            assertEquals(
                viewModel.uiState.value.status.failure,
                "ConfigViewModel.onSaveAndUpdate -> ConfigViewModel.token -> SendConfig -> java.lang.Exception"
            )
        }

    @Test
    fun `saveTokenAndUpdate - Check return failure if SendConfig return idServ null`() =
        runTest {
            whenever(
                sendConfig(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00"
                )
            ).thenReturn(
                Result.success(
                    Config()
                )
            )
            viewModel.onNumberChanged("16997417840")
            viewModel.onPasswordChanged("12345")
            viewModel.setConfigMain(
                version = "1.00",
            )
            val result = viewModel.token().toList()
            assertEquals(result.count(), 3)
            assertEquals(
                result[0],
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.GET_TOKEN,
                        currentProgress = percentage(1f, 3f)
                    )
                )
            )
            assertEquals(
                result[1],
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.SAVE_TOKEN,
                        currentProgress = percentage(2f, 3f)
                    )
                )
            )
            assertEquals(
                result[2],
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        errors = Errors.TOKEN,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "ConfigViewModel.token -> idServ is required -> null",
                        currentProgress = 1f,
                    )
                )
            )
            viewModel.onSaveAndUpdate()
            assertEquals(
                viewModel.uiState.value.status.failure,
                "ConfigViewModel.onSaveAndUpdate -> ConfigViewModel.token -> idServ is required -> null"
            )
        }

    @Test
    fun `saveTokenAndUpdate - Check return failure if have error in SaveConfig`() =
        runTest {
            whenever(
                sendConfig(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00"
                )
            ).thenReturn(
                Result.success(
                    Config(
                        idServ = 1
                    )
                )
            )
            whenever(
                saveConfig(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    idServ = 1
                )
            ).thenReturn(
                resultFailure(
                    "SaveConfig",
                    "-",
                    Exception()
                )
            )
            viewModel.onNumberChanged("16997417840")
            viewModel.onPasswordChanged("12345")
            viewModel.setConfigMain(
                version = "1.00",
            )
            val result = viewModel.token().toList()
            assertEquals(result.count(), 3)
            assertEquals(
                result[0],
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.GET_TOKEN,
                        currentProgress = percentage(1f, 3f)
                    )
                )
            )
            assertEquals(
                result[1],
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.SAVE_TOKEN,
                        currentProgress = percentage(2f, 3f)
                    )
                )
            )
            assertEquals(
                result[2],
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        errors = Errors.TOKEN,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "ConfigViewModel.token -> SaveConfig -> java.lang.Exception",
                        currentProgress = 1f,
                    )
                )
            )
            viewModel.onSaveAndUpdate()
            assertEquals(
                viewModel.uiState.value.status.failure,
                "ConfigViewModel.onSaveAndUpdate -> ConfigViewModel.token -> SaveConfig -> java.lang.Exception"
            )
        }

    @Test
    fun `saveTokenAndUpdate - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                sendConfig(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00"
                )
            ).thenReturn(
                Result.success(
                    Config(
                        idServ = 1
                    )
                )
            )
            viewModel.onNumberChanged("16997417840")
            viewModel.onPasswordChanged("12345")
            viewModel.setConfigMain(
                version = "1.00",
            )
            val result = viewModel.token().toList()
            verify(saveConfig, atLeastOnce()).invoke("16997417840", "12345", "1.00", 1)
            assertEquals(result.count(), 3)
            assertEquals(
                result[0],
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.GET_TOKEN,
                        currentProgress = percentage(1f, 3f)
                    )
                )
            )
            assertEquals(
                result[1],
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.SAVE_TOKEN,
                        currentProgress = percentage(2f, 3f)
                    )
                )
            )
            assertEquals(
                result[2],
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.FINISH_UPDATE_INITIAL,
                        currentProgress = 1f,
                    )
                )
            )
        }

    @Test
    fun `update - Check return failure if have error in UpdateTableColab`() =
        runTest {
            val qtdBefore = 0f
            whenever(
                updateTableColab(
                    sizeAll = sizeUpdate(2f),
                    count = (qtdBefore + 1)
                )
            ).thenReturn(
                flowOf(
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_colab",
                        currentProgress = percentage(((qtdBefore * 3) + 1), 2f)
                    ),
                    UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "CleanColab -> java.lang.NullPointerException",
                    )
                )
            )
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(
                result.count(),
                ((qtdBefore * 3) + 2).toInt()
            )
            assertEquals(
                result[(qtdBefore * 3).toInt()],
                ConfigState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_colab",
                        currentProgress = percentage(((qtdBefore * 3) + 1), 2f)
                    )
                )
            )
            assertEquals(
                result[((qtdBefore * 3) + 1).toInt()],
                ConfigState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "ConfigViewModel.updateAllDatabase -> CleanColab -> java.lang.NullPointerException",
                    )
                )
            )
        }

    @Test
    fun `update - Check return failure if have error in UpdateTableEquip`() =
        runTest {
            val qtdBefore = 1f
            wheneverSuccess(qtdBefore)
            whenever(
                updateTableEquip(
                    sizeAll = sizeUpdate(2f),
                    count = (qtdBefore + 1)
                )
            ).thenReturn(
                flowOf(
                    UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_equip",
                        currentProgress = percentage(((qtdBefore * 3) + 1), 2f)
                    ),
                    UpdateStatusState(
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "CleanEquip -> java.lang.NullPointerException",
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
                ConfigState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        levelUpdate = LevelUpdate.RECOVERY,
                        tableUpdate = "tb_equip",
                        currentProgress = percentage(((qtdBefore * 3) + 1), 2f)
                    )
                )
            )
            assertEquals(
                result[((qtdBefore * 3) + 1).toInt()],
                ConfigState(
                    status = UpdateStatusState(
                        flagProgress = true,
                        errors = Errors.UPDATE,
                        flagDialog = true,
                        flagFailure = true,
                        failure = "ConfigViewModel.updateAllDatabase -> CleanEquip -> java.lang.NullPointerException",
                    )
                )
            )
        }

    @Test
    fun `update - Check updateAllDatabase execute correctly`() =
        runTest {
            val qtdBefore = 2f
            wheneverSuccess(qtdBefore)
            val result = viewModel.updateAllDatabase().toList()
            val qtd = sizeUpdate(qtdBefore) - 1f
            assertEquals(
                result.count(),
                qtd.toInt()
            )
            checkResultUpdate(qtdBefore, result)
        }

    @Test
    fun `update - Check return failure if have error in SetCheckUpdateAllTable`() =
        runTest {
            whenever(
                sendConfig(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00"
                )
            ).thenReturn(
                Result.success(
                    Config(
                        idServ = 1
                    )
                )
            )
            whenever(
                setFinishUpdateAllTable()
            ).thenReturn(
                resultFailure(
                    "ISetFinishUpdateAllTable",
                    "-",
                    Exception()
                )
            )
            viewModel.onNumberChanged("16997417840")
            viewModel.onPasswordChanged("12345")
            viewModel.setConfigMain(
                version = "1.00",
            )
            wheneverSuccess(3f)
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(
                result.count(),
                ((2f * 3)).toInt()
            )
            checkResultUpdateAll(result)
            viewModel.onSaveAndUpdate()
            val configState = viewModel.uiState.value
            assertEquals(
                configState,
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        errors = Errors.EXCEPTION,
                        flagFailure = true,
                        flagDialog = true,
                        flagProgress = true,
                        currentProgress = 1f,
                        levelUpdate = LevelUpdate.FINISH_UPDATE_INITIAL,
                        tableUpdate = "",
                        failure = "ConfigViewModel.token -> ConfigViewModel.onSaveAndUpdate -> ISetFinishUpdateAllTable -> java.lang.Exception",
                    )
                )
            )
        }

    @Test
    fun `update - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                sendConfig(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00"
                )
            ).thenReturn(
                Result.success(
                    Config(
                        idServ = 1
                    )
                )
            )
            whenever(
                saveConfig(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    idServ = 1
                )
            ).thenReturn(
                Result.success(Unit)
            )
            whenever(
                setFinishUpdateAllTable()
            ).thenReturn(
                Result.success(Unit)
            )
            viewModel.onNumberChanged("16997417840")
            viewModel.onPasswordChanged("12345")
            viewModel.setConfigMain(
                version = "1.00"
            )
            wheneverSuccess(3f)
            val result = viewModel.updateAllDatabase().toList()
            assertEquals(
                result.count(),
                ((2f * 3)).toInt()
            )
            checkResultUpdateAll(result)
            viewModel.onSaveAndUpdate()
            val configState = viewModel.uiState.value
            assertEquals(
                configState,
                ConfigState(
                    number = "16997417840",
                    password = "12345",
                    version = "1.00",
                    status = UpdateStatusState(
                        flagDialog = true,
                        flagProgress = true,
                        flagFailure = false,
                        levelUpdate = LevelUpdate.FINISH_UPDATE_COMPLETED,
                        currentProgress = 1f,
                    )
                )
            )
        }

    ///////////////////////////////////////////////////////////////////////////////////////

    private fun wheneverSuccess(posTable: Float) =
        runTest {
            var contUpdate = 0f
            var contWhenever = 0f

            val sizeAll = sizeUpdate(2f)
            tableList = mutableListOf(
                "tb_colab", "tb_equip"
            )

            val updateFunctions = mutableListOf<
                    suspend (Float, Float) -> Flow<UpdateStatusState>
                    >(
                { sizeAll, count -> updateTableColab(sizeAll, count) },
                { sizeAll, count -> updateTableEquip(sizeAll, count) },
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

    private fun checkResultUpdate(posTable: Float, result: List<ConfigState>) =
        runTest {
            val sizeAll = sizeUpdate(2f)
            var contUpdate = 0f
            var cont = 0
            for(table in tableList) {
                assertEquals(
                    result[cont++],
                    ConfigState(
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
                    ConfigState(
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
                    ConfigState(
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

    private fun checkResultUpdateAll(result: List<ConfigState>) =
        runTest {
            val qtd = 2f
            val sizeAll = sizeUpdate(qtd)
            var contUpdate = 0f
            var cont = 0
            for(table in tableList) {
                assertEquals(
                    result[cont++],
                    ConfigState(
                        number = "16997417840",
                        password = "12345",
                        version = "1.00",
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
                    ConfigState(
                        number = "16997417840",
                        password = "12345",
                        version = "1.00",
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
                    ConfigState(
                        number = "16997417840",
                        password = "12345",
                        version = "1.00",
                        status = UpdateStatusState(
                            flagProgress = true,
                            levelUpdate = LevelUpdate.SAVE,
                            tableUpdate = table,
                            currentProgress = percentage(cont.toFloat(), sizeAll)
                        )
                    )
                )
                ++contUpdate
            }
        }

}