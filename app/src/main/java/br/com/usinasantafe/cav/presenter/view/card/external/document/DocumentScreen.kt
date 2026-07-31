package br.com.usinasantafe.cav.presenter.view.card.external.document

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
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.theme.ButtonsGenericNumeric
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextFieldDesign
import br.com.usinasantafe.cav.utils.UiStatusState

@Composable
fun DocumentScreen(
    viewModel: DocumentViewModel = hiltViewModel(),
    onNavDetail: () -> Unit,
    onNavDataInvolved: () -> Unit,
    onNavName: () -> Unit,
    onNavPassenger: () -> Unit,
    onNavMenu: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            DocumentContent(
                option = uiState.option,
                flowNote = uiState.flowNote,
                text = uiState.text,
                onTextField = viewModel::onTextField,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavDetail = onNavDetail,
                onNavDataInvolved = onNavDataInvolved,
                onNavName = onNavName,
                onNavPassenger = onNavPassenger,
                onNavMenu = onNavMenu,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun DocumentContent(
    option: Option,
    flowNote: FlowNote,
    text: String,
    onTextField: (String, TypeButton) -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavDetail: () -> Unit,
    onNavDataInvolved: () -> Unit,
    onNavName: () -> Unit,
    onNavPassenger: () -> Unit,
    onNavMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_title_document
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
                    when(flowNote) {
                        FlowNote.INVOLVED_EXTERNAL,
                        FlowNote.WITNESS_EXTERNAL -> onNavMenu()
                        FlowNote.PASSENGER_EXTERNAL -> onNavPassenger()
                        else -> onNavDetail()
                    }
                }
                Option.EDIT -> onNavDataInvolved()
            }
        }


        if(status.flagDialog) {
            MsgErrors(
                errors = status.errors,
                onClickOk = onCloseDialog,
                failure = status.failure,
                value = stringResource(id = R.string.text_title_document)
            )
        }

    }

    LaunchedEffect(status.flagAccess) {
        if(status.flagAccess) {
            when(option) {
                Option.INSERT -> onNavName()
                Option.EDIT -> onNavDataInvolved()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DocumentPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DocumentContent(
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_EXTERNAL,
                text = "",
                onTextField = { _, _ -> },
                onCloseDialog = {},
                status = UiStatusState(),
                onNavDetail = {},
                onNavDataInvolved = {},
                onNavName = {},
                onNavPassenger = {},
                onNavMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}