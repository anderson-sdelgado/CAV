package br.com.usinasantafe.cav.presenter.view.configuration.config

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.entities.variable.Config
import br.com.usinasantafe.cav.domain.usecases.config.GetConfig
import br.com.usinasantafe.cav.domain.usecases.config.SaveConfig
import br.com.usinasantafe.cav.domain.usecases.config.SendConfig
import br.com.usinasantafe.cav.domain.usecases.config.SetFinishUpdateAllTable
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.presenter.model.ConfigModel
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.percentage
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class ConfigViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getConfig = mock<GetConfig>()
    private val saveConfig = mock<SaveConfig>()
    private val sendConfig = mock<SendConfig>()
    private val setFinishUpdateAllTable = mock<SetFinishUpdateAllTable>()
    private val viewModel = ConfigViewModel(
        getConfig = getConfig,
        saveConfig = saveConfig,
        sendConfig = sendConfig,
        setFinishUpdateAllTable = setFinishUpdateAllTable
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
}