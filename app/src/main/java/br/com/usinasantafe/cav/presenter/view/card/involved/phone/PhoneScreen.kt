package br.com.usinasantafe.cav.presenter.view.card.involved.phone

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
fun PhoneScreen(
    viewModel: PhoneViewModel = hiltViewModel(),
    onNavName: () -> Unit,
    onNavState: () -> Unit,
    onNavDataInvolved: () -> Unit,
    onNavDetail: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            PhoneContent(
                option = uiState.option,
                flowNote = uiState.flowNote,
                text = uiState.text,
                onTextField = viewModel::onTextField,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavName = onNavName,
                onNavState = onNavState,
                onNavDataInvolved = onNavDataInvolved,
                onNavDetail = onNavDetail,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun PhoneContent(
    option: Option,
    flowNote: FlowNote,
    text: String,
    onTextField: (String, TypeButton) -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavName: () -> Unit,
    onNavState: () -> Unit,
    onNavDataInvolved: () -> Unit,
    onNavDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_phone
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
                Option.INSERT -> onNavName()
                Option.EDIT -> onNavDataInvolved()
            }
        }

        if(status.flagDialog) {
            MsgErrors(
                errors =  status.errors,
                onClickOk = onCloseDialog,
                failure = status.failure,
                value = stringResource(id = R.string.text_phone)
            )
        }

    }

    LaunchedEffect(status.flagAccess) {
        if(status.flagAccess) {
            when(option){
                Option.INSERT -> {
                    when(flowNote){
                        FlowNote.WITNESS -> onNavDetail()
                        else -> onNavState()
                    }

                }
                Option.EDIT -> onNavDataInvolved()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhonePagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PhoneContent(
                option = Option.INSERT,
                flowNote = FlowNote.WITNESS,
                text = "",
                onTextField = { _, _ -> },
                onCloseDialog = {},
                status = UiStatusState(),
                onNavName = {},
                onNavState = {},
                onNavDataInvolved = {},
                onNavDetail = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}