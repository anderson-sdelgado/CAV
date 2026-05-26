package br.com.usinasantafe.cav.presenter.view.card.attendant

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.theme.ButtonsGenericNumeric
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgUpdate
import br.com.usinasantafe.cav.presenter.theme.Progress
import br.com.usinasantafe.cav.presenter.theme.TextFieldDesign
import br.com.usinasantafe.cav.utils.UiStatusStateUpdate

@Composable
fun AttendantScreen(
    viewModel: AttendantViewModel = hiltViewModel(),
    onNavInitialMenu: () -> Unit,
    onNavCar: () -> Unit,
    onNavMenu: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            AttendantContent(
                option = uiState.option,
                regColab = uiState.regColab,
                setTextField = viewModel::onTextField,
                setCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavInitialMenu = onNavInitialMenu,
                onNavCar = onNavCar,
                onNavMenu = onNavMenu,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun AttendantContent(
    option: Option,
    regColab: String,
    setTextField: (String, TypeButton) -> Unit,
    setCloseDialog: () -> Unit,
    status: UiStatusStateUpdate,
    onNavInitialMenu: () -> Unit,
    onNavCar: () -> Unit,
    onNavMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_title_attendant
            )
        )
        TextFieldDesign(
            value = regColab
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        ButtonsGenericNumeric(
            onTextField = setTextField
        )
        BackHandler {
            when(option){
                Option.INSERT -> onNavInitialMenu()
                Option.EDIT -> onNavMenu()
            }
        }

        if (status.flagDialog) {
            MsgUpdate(status = status, onClickOk = setCloseDialog, value = stringResource(id = R.string.text_title_attendant))
        }

        if (status.flagProgress) {
            Progress(status)
        }
    }

    LaunchedEffect(status.flagAccess) {
        if(status.flagAccess) {
            when(option){
                Option.INSERT -> onNavCar()
                Option.EDIT -> onNavMenu()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AttendantPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AttendantContent(
                option = Option.INSERT,
                regColab = "",
                setTextField = { _, _ -> },
                setCloseDialog = {},
                status = UiStatusStateUpdate(
                    flagAccess = false,
                    flagDialog = false,
                    flagFailure = false,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                    flagProgress = false,
                    levelUpdate = null,
                    tableUpdate = "",
                    currentProgress = 0f,
                ),
                onNavInitialMenu = {},
                onNavCar = {},
                onNavMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AttendantPagePreviewWithData() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AttendantContent(
                option = Option.INSERT,
                regColab = "19759",
                setTextField = { _, _ -> },
                setCloseDialog = {},
                status = UiStatusStateUpdate(
                    flagAccess = false,
                    flagDialog = false,
                    flagFailure = false,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                    flagProgress = false,
                    levelUpdate = null,
                    tableUpdate = "",
                    currentProgress = 0f,
                ),
                onNavInitialMenu = {},
                onNavCar = {},
                onNavMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AttendantPagePreviewWithMsgEmpty() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AttendantContent(
                option = Option.INSERT,
                regColab = "",
                setTextField = { _, _ -> },
                setCloseDialog = {},
                status = UiStatusStateUpdate(
                    flagAccess = false,
                    flagDialog = true,
                    flagFailure = true,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                    flagProgress = false,
                    levelUpdate = null,
                    tableUpdate = "",
                    currentProgress = 0f,
                ),
                onNavInitialMenu = {},
                onNavCar = {},
                onNavMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AttendantPagePreviewUpdate() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AttendantContent(
                option = Option.INSERT,
                regColab = "",
                setTextField = { _, _ -> },
                setCloseDialog = {},
                status = UiStatusStateUpdate(
                    flagAccess = false,
                    flagDialog = false,
                    failure = "",
                    flagFailure = false,
                    errors = Errors.FIELD_EMPTY,
                    levelUpdate = LevelUpdate.RECOVERY,
                    tableUpdate = "tb_colab",
                    flagProgress = true,
                    currentProgress = 0.3333334f,
                ),
                onNavInitialMenu = {},
                onNavCar = {},
                onNavMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}