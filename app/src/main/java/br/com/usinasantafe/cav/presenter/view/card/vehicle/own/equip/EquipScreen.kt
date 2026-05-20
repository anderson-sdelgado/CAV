package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.equip

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
fun EquipScreen(
    viewModel: EquipViewModel = hiltViewModel(),
    onNavMenu:  () -> Unit,
    onNavDetail: () -> Unit,
    onNavData: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            EquipContent(
                option = uiState.option,
                nroEquip = uiState.nroEquip,
                setTextField = viewModel::setTextField,
                flagAccess = uiState.flagAccess,
                setCloseDialog = viewModel::setCloseDialog,
                status = uiState.status,
                onNavMenu = onNavMenu,
                onNavDetail = onNavDetail,
                onNavData = onNavData,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun EquipContent(
    option: Option,
    nroEquip: String,
    setTextField: (String, TypeButton) -> Unit,
    flagAccess: Boolean,
    setCloseDialog: () -> Unit,
    status: UpdateStatusState,
    onNavMenu:  () -> Unit,
    onNavDetail: () -> Unit,
    onNavData: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_title_equip
            )
        )
        TextFieldDesign(
            value = nroEquip
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        ButtonsGenericNumeric(
            setActionButton = setTextField
        )
        BackHandler {
            when(option) {
                Option.INSERT -> onNavMenu()
                Option.EDIT -> onNavData()
            }
        }

        if (status.flagDialog) {
            MsgUpdate(status = status, onClickOk = setCloseDialog, value = stringResource(id = R.string.text_title_car))
        }

        if (status.flagProgress) {
            Progress(status)
        }
    }

    LaunchedEffect(flagAccess) {
        if(flagAccess) {
            when(option) {
                Option.INSERT -> onNavDetail()
                Option.EDIT -> onNavData()
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
                nroEquip = "",
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
                onNavMenu = {},
                onNavDetail = {},
                onNavData = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}