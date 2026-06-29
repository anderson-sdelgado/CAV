package br.com.usinasantafe.cav.presenter.view.card.colab.colab

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
import br.com.usinasantafe.cav.lib.FlowNote
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
fun ColabScreen(
    viewModel: ColabViewModel = hiltViewModel(),
    onNavPassengerList:  () -> Unit,
    onNavState: () -> Unit,
    onNavDataColab: () -> Unit,
    onNavDetail: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            ColabContent(
                option = uiState.option,
                flowNote = uiState.flowNote,
                text = uiState.text,
                onTextField = viewModel::onTextField,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavPassengerList = onNavPassengerList,
                onNavState = onNavState,
                onNavDataColab = onNavDataColab,
                onNavDetail = onNavDetail,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ColabContent(
    option: Option,
    flowNote: FlowNote,
    text: String,
    onTextField: (String, TypeButton) -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusStateUpdate,
    onNavPassengerList:  () -> Unit,
    onNavState:  () -> Unit,
    onNavDataColab: () -> Unit,
    onNavDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = if(flowNote == FlowNote.COLAB) R.string.text_edit_driver else  R.string.text_edit_passenger
            )
        )
        TextFieldDesign(
            value = text
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        ButtonsGenericNumeric(
            onTextField = onTextField
        )
        BackHandler {
            when(option) {
                Option.INSERT -> {
                    when(flowNote){
                        FlowNote.PASSENGER_COLAB -> onNavPassengerList()
                        else -> onNavDetail()
                    }
                }
                Option.EDIT -> onNavDataColab()
            }
        }

        if (status.flagDialog) {
            val value =  if(flowNote == FlowNote.COLAB) R.string.text_edit_driver else  R.string.text_edit_passenger
            MsgUpdate(status = status, onClickOk = onCloseDialog, value = stringResource(id = value))
        }

        if (status.flagProgress) {
            Progress(status)
        }
    }

    LaunchedEffect(status.flagAccess) {
        if(status.flagAccess) {
            when(option){
                Option.INSERT -> onNavState()
                Option.EDIT -> onNavDataColab()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColabPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ColabContent(
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                text = "",
                onTextField = { _, _ -> },
                onCloseDialog = {},
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
                onNavPassengerList = {},
                onNavState = {},
                onNavDataColab = {},
                onNavDetail = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}