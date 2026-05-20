package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.colab

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
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeButton
import br.com.usinasantafe.cav.presenter.theme.ButtonsGenericNumeric
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgUpdate
import br.com.usinasantafe.cav.presenter.theme.Progress
import br.com.usinasantafe.cav.presenter.theme.TextFieldDesign
import br.com.usinasantafe.cav.utils.UpdateStatusState

@Composable
fun ColabScreen(
    viewModel: ColabViewModel = hiltViewModel(),
    onNavEquipSecList:  () -> Unit,
    onNavPassengerList:  () -> Unit,
    onNavState:  () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ColabContent(
                option = uiState.option,
                regColab = uiState.regColab,
                setTextField = viewModel::setTextField,
                flagAccess = uiState.flagAccess,
                setCloseDialog = viewModel::setCloseDialog,
                status = uiState.status,
                onNavEquipSecList = onNavEquipSecList,
                onNavPassengerList = onNavPassengerList,
                onNavState = onNavState,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ColabContent(
    option: Option,
    regColab: String,
    setTextField: (String, TypeButton) -> Unit,
    flagAccess: Boolean,
    setCloseDialog: () -> Unit,
    status: UpdateStatusState,
    onNavEquipSecList:  () -> Unit,
    onNavPassengerList:  () -> Unit,
    onNavState:  () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_title_driver_colab
            )
        )
        TextFieldDesign(
            value = regColab
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        ButtonsGenericNumeric(
            setActionButton = setTextField
        )
        BackHandler {
            when(option){
                Option.INSERT -> onNavEquipSecList()
                Option.EDIT -> onNavState()
            }
        }

        if (status.flagDialog) {
            MsgUpdate(status = status, onClickOk = setCloseDialog, value = stringResource(id = R.string.text_title_attendant))
        }

        if (status.flagProgress) {
            Progress(status)
        }
    }

    LaunchedEffect(flagAccess) {
        if(flagAccess) {
            when(option){
                Option.INSERT -> onNavPassengerList()
                Option.EDIT -> onNavState()
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
                regColab = "",
                setTextField = { _, _ -> },
                flagAccess = false,
                setCloseDialog = {},
                status = UpdateStatusState(
                    flagDialog = false,
                    flagFailure = false,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                    flagProgress = false,
                    levelUpdate = null,
                    tableUpdate = "",
                    currentProgress = 0f,
                ),
                onNavEquipSecList = {},
                onNavPassengerList = {},
                onNavState = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}