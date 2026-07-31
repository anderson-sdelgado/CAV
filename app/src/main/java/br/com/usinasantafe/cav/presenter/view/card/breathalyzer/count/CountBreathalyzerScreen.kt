package br.com.usinasantafe.cav.presenter.view.card.breathalyzer.count

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
import br.com.usinasantafe.cav.utils.UiStatusStateUpdate

@Composable
fun CountBreathalyzerScreen(
    viewModel: CountBreathalyzerViewModel = hiltViewModel(),
    onNavCheckBreathalyzer: () -> Unit,
    onNavDetail: () -> Unit,
    onNavDataColab: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            CountBreathalyzerContent(
                option = uiState.option,
                text = uiState.text,
                onTextField = viewModel::onTextField,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavCheckBreathalyzer = onNavCheckBreathalyzer,
                onNavDetail = onNavDetail,
                onNavDataColab = onNavDataColab,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun CountBreathalyzerContent(
    option: Option,
    text: String,
    onTextField: (String, TypeButton) -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavCheckBreathalyzer: () -> Unit,
    onNavDetail: () -> Unit,
    onNavDataColab: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_breathalyzer
            )
        )
        TextFieldDesign(
            value = text
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        ButtonsGenericNumeric(
            onTextField = onTextField,
            flagUpdate = false
        )
        BackHandler {
            when(option) {
                Option.INSERT -> onNavCheckBreathalyzer()
                Option.EDIT -> onNavDataColab()
            }
        }

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }

    LaunchedEffect(status.flagAccess) {
        if(status.flagAccess) {
            when(option){
                Option.INSERT -> onNavDetail()
                Option.EDIT -> onNavDataColab()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CountBreathalyzerPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CountBreathalyzerContent(
                option = Option.INSERT,
                text = "0,00",
                onTextField = { _, _ -> },
                onCloseDialog = {},
                status = UiStatusState(),
                onNavCheckBreathalyzer = {},
                onNavDetail = {},
                onNavDataColab = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}