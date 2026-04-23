package br.com.usinasantafe.cav.presenter.view.configuration.config

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.BuildConfig
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.utils.UpdateStatusState

@Composable
fun ConfigScreen(
    viewModel: ConfigViewModel = hiltViewModel(),
    onNavInitialMenu: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.returnDataConfig()
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
    status: UpdateStatusState,
    onNavInitialMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(text = "")
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