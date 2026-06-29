package br.com.usinasantafe.cav.presenter.view.card.equip.equip

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
fun EquipScreen(
    viewModel: EquipViewModel = hiltViewModel(),
    onNavMenu:  () -> Unit,
    onNavDetail: () -> Unit,
    onNavEquipSecList: () -> Unit,
    onNavDataEquip: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            EquipContent(
                option = uiState.option,
                flowNote = uiState.flowNote,
                nroEquip = uiState.text,
                onTextField = viewModel::onTextField,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavMenu = onNavMenu,
                onNavDetail = onNavDetail,
                onNavEquipSecList = onNavEquipSecList,
                onNavDataEquip = onNavDataEquip,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun EquipContent(
    option: Option,
    flowNote: FlowNote,
    nroEquip: String,
    onTextField: (String, TypeButton) -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusStateUpdate,
    onNavMenu:  () -> Unit,
    onNavDetail: () -> Unit,
    onNavEquipSecList: () -> Unit,
    onNavDataEquip: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_edit_equip
            )
        )
        TextFieldDesign(
            value = nroEquip
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        ButtonsGenericNumeric(
            onTextField = onTextField
        )
        BackHandler {
            when(option) {
                Option.INSERT -> {
                    when(flowNote) {
                        FlowNote.EQUIP -> onNavMenu()
                        FlowNote.EQUIP_SEC -> onNavEquipSecList()
                        else -> {}
                    }
                }
                Option.EDIT -> onNavDataEquip()
            }
        }

        if (status.flagDialog) {
            MsgUpdate(status = status, onClickOk = onCloseDialog, value = stringResource(id = R.string.text_edit_equip))
        }

        if (status.flagProgress) {
            Progress(status)
        }
    }

    LaunchedEffect(status.flagAccess) {
        if(status.flagAccess) {
            when(option){
                Option.INSERT -> onNavDetail()
                Option.EDIT -> onNavDataEquip()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EquipPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            EquipContent(
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP,
                nroEquip = "",
                onTextField = { _, _ -> },
                onCloseDialog = {},
                status = UiStatusStateUpdate(
                    flagDialog = false,
                    flagFailure = false,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                    flagProgress = false,
                    levelUpdate = null,
                    tableUpdate = "",
                    currentProgress = 0f,
                ),
                onNavMenu = {},
                onNavDetail = {},
                onNavEquipSecList = {},
                onNavDataEquip = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}