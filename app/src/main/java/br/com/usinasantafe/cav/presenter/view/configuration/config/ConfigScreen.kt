package br.com.usinasantafe.cav.presenter.view.configuration.config

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.BuildConfig
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.lib.msg
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgUpdate
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.presenter.theme.TextFieldConfigDesign
import br.com.usinasantafe.cav.presenter.theme.TextFieldPasswordDesign
import br.com.usinasantafe.cav.utils.UpdateStatusState

const val TAG_NUMBER_TEXT_FIELD_CONFIG_SCREEN = "tag_number_text_field_config_screen"
const val TAG_PASSWORD_TEXT_FIELD_CONFIG_SCREEN = "tag_password_text_field_config_screen"

@Composable
fun ConfigScreen(
    viewModel: ConfigViewModel = hiltViewModel(),
    onNavInitialMenu: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.returnConfig()
                viewModel.setConfigMain(
                    BuildConfig.VERSION_NAME
                )
            }

            ConfigContent(
                number = uiState.number,
                onNumberChanged = viewModel::onNumberChanged,
                password = uiState.password,
                onPasswordChanged = viewModel::onPasswordChanged,
                onSaveAndUpdate = viewModel::onSaveAndUpdate,
                setCloseDialog = viewModel::setCloseDialog,
                flagAccess = uiState.flagAccess,
                status = uiState.status,
                onNavInitialMenu = onNavInitialMenu,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ConfigContent(
    number: String,
    onNumberChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onSaveAndUpdate: () -> Unit,
    setCloseDialog: () -> Unit,
    flagAccess: Boolean,
    status: UpdateStatusState,
    onNavInitialMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_title_number
            )
        )
        TextFieldConfigDesign(
            value = number,
            onValueChange = onNumberChanged,
            tag = TAG_NUMBER_TEXT_FIELD_CONFIG_SCREEN
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        TitleDesign(
            text = stringResource(id = R.string.text_title_password)
        )
        TextFieldPasswordDesign(
            value = password,
            onValueChange = onPasswordChanged,
            tag = TAG_PASSWORD_TEXT_FIELD_CONFIG_SCREEN
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        )  {
            Button(
                onClick = onNavInitialMenu,
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_cancel)
                )
            }
            Button(
                onClick = {
                    keyboardController?.hide()
                    onSaveAndUpdate()
                },
                modifier = Modifier.weight(1f),
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_save)
                )
            }
        }

        if (status.flagProgress) {
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            LinearProgressIndicator(
                progress = { status.currentProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            val msgProgress = msg(status.levelUpdate, status.failure, status.tableUpdate)
            Text(
                text = msgProgress,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        BackHandler {}

        if (status.flagDialog) {
            MsgUpdate(status = status, setCloseDialog = setCloseDialog)
        }
    }

    LaunchedEffect(flagAccess) {
        if(flagAccess) {
            onNavInitialMenu()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfigPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ConfigContent(
                number = "",
                onNumberChanged = {},
                password = "",
                onPasswordChanged = {},
                onSaveAndUpdate = {},
                setCloseDialog = {},
                flagAccess = false,
                status = UpdateStatusState(
                    flagProgress = false,
                    currentProgress = 0.0f,
                    levelUpdate = null,
                    tableUpdate = "",
                    flagDialog = false,
                    flagFailure = false,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                ),
                onNavInitialMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfigPagePreviewWithData() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ConfigContent(
                number = "16997417840",
                onNumberChanged = {},
                password = "12345",
                onPasswordChanged = {},
                onSaveAndUpdate = {},
                setCloseDialog = {},
                flagAccess = false,
                status = UpdateStatusState(
                    flagProgress = false,
                    currentProgress = 0.0f,
                    levelUpdate = null,
                    tableUpdate = "",
                    flagDialog = false,
                    flagFailure = false,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                ),
                onNavInitialMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfigPagePreviewShowProgress() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ConfigContent(
                number = "16997417840",
                onNumberChanged = {},
                password = "12345",
                onPasswordChanged = {},
                onSaveAndUpdate = {},
                setCloseDialog = {},
                flagAccess = false,
                status = UpdateStatusState(
                    flagProgress = true,
                    currentProgress = 0.2f,
                    levelUpdate = LevelUpdate.RECOVERY,
                    tableUpdate = "tb_colab",
                    flagDialog = false,
                    flagFailure = false,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                ),
                onNavInitialMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfigPagePreviewShowMsgFieldEmpty() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ConfigContent(
                number = "",
                onNumberChanged = {},
                password = "",
                onPasswordChanged = {},
                onSaveAndUpdate = {},
                setCloseDialog = {},
                flagAccess = false,
                status = UpdateStatusState(
                    flagProgress = false,
                    currentProgress = 0.0f,
                    levelUpdate = null,
                    tableUpdate = "",
                    flagDialog = true,
                    flagFailure = true,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                ),
                onNavInitialMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfigPagePreviewShowMsgSuccess() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ConfigContent(
                number = "16997417840",
                onNumberChanged = {},
                password = "12345",
                onPasswordChanged = {},
                onSaveAndUpdate = {},
                setCloseDialog = {},
                flagAccess = false,
                status = UpdateStatusState(
                    flagProgress = false,
                    currentProgress = 0.0f,
                    levelUpdate = LevelUpdate.FINISH_UPDATE_COMPLETED,
                    tableUpdate = "",
                    flagDialog = true,
                    flagFailure = false,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                ),
                onNavInitialMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
