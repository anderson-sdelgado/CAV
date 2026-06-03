package br.com.usinasantafe.cav.presenter.view.card.state

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.utils.UiStatusState

@Composable
fun StateScreen(
    viewModel: StateViewModel = hiltViewModel(),
    onNavColab: () -> Unit,
    onNavName: () -> Unit,
    onNavDetail: () -> Unit,
    onNavDataColab: () -> Unit,
    onNavDataInvolved: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            StateContent(
                option = uiState.option,
                flowNote = uiState.flowNote,
                idSelection = uiState.idSelection,
                onSelection = viewModel::onSelection,
                onCloseDialog = viewModel::onCloseDialog,
                set = viewModel::set,
                status = uiState.status,
                onNavColab = onNavColab,
                onNavName = onNavName,
                onNavDetail = onNavDetail,
                onNavDataColab = onNavDataColab,
                onNavDataInvolved = onNavDataInvolved,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun StateContent(
    option: Option,
    flowNote: FlowNote,
    idSelection: Int,
    onSelection: (Int) -> Unit,
    onCloseDialog: () -> Unit,
    set: () -> Unit,
    status: UiStatusState,
    onNavColab: () -> Unit,
    onNavName: () -> Unit,
    onNavDetail: () -> Unit,
    onNavDataColab: () -> Unit,
    onNavDataInvolved: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_state
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .selectable(
                            selected = (idSelection == 1),
                            onClick = { onSelection(1) },
                            role = Role.RadioButton
                        )
                ) {
                    RadioButton(
                        selected = (idSelection == 1),
                        onClick = null,
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                    Text(
                        text = stringResource(
                            id = R.string.text_item_unharmed
                        ),
                        fontSize = 22.sp,
                    )
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .selectable(
                            selected = (idSelection == 2),
                            onClick = { onSelection(2) },
                            role = Role.RadioButton
                        )
                ) {
                    RadioButton(
                        selected = (idSelection == 2),
                        onClick = null,
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                    Text(
                        text = stringResource(
                            id = R.string.text_item_injured
                        ),
                        fontSize = 22.sp,
                    )
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .selectable(
                            selected = (idSelection == 3),
                            onClick = { onSelection(3) },
                            role = Role.RadioButton
                        )
                ) {
                    RadioButton(
                        selected = (idSelection == 3),
                        onClick = null,
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                    Text(
                        text = stringResource(
                            id = R.string.text_item_dead
                        ),
                        fontSize = 22.sp,
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {
                    when(option){
                        Option.INSERT -> {
                            when(flowNote) {
                                FlowNote.COLAB,
                                FlowNote.PASSENGER_COLAB -> onNavColab()
                                FlowNote.DRIVER,
                                FlowNote.PASSENGER_INVOLVED,
                                FlowNote.WITNESS,
                                FlowNote.INVOLVED -> onNavName()
                                FlowNote.EQUIP,
                                FlowNote.EQUIP_SEC,
                                FlowNote.VEHICLE -> {}

                            }
                        }
                        Option.EDIT -> {
                            when(flowNote){
                                FlowNote.COLAB,
                                FlowNote.PASSENGER_COLAB -> onNavDataColab()
                                FlowNote.DRIVER,
                                FlowNote.PASSENGER_INVOLVED,
                                FlowNote.INVOLVED,
                                FlowNote.WITNESS -> onNavDataInvolved()
                                FlowNote.EQUIP,
                                FlowNote.EQUIP_SEC,
                                FlowNote.VEHICLE -> {}
                            }
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_return),
                    font = 18
                )
            }
            Button(
                onClick = set,
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_save),
                    font = 18
                )
            }
        }
        BackHandler {}

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }
    }

    LaunchedEffect(status.flagAccess) {
        if(status.flagAccess) {
            when(option){
                Option.INSERT -> onNavDetail()
                Option.EDIT -> {
                    when(flowNote){
                        FlowNote.COLAB,
                        FlowNote.PASSENGER_COLAB -> onNavDataColab()
                        FlowNote.DRIVER,
                        FlowNote.PASSENGER_INVOLVED,
                        FlowNote.INVOLVED,
                        FlowNote.WITNESS -> onNavDataInvolved()
                        FlowNote.EQUIP,
                        FlowNote.EQUIP_SEC,
                        FlowNote.VEHICLE -> {}
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun StatePagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            StateContent(
                option = Option.INSERT,
                flowNote = FlowNote.DRIVER,
                idSelection = 1,
                onSelection = {},
                onCloseDialog = {},
                set = {},
                status = UiStatusState(),
                onNavColab = {},
                onNavDetail = {},
                onNavDataColab = {},
                onNavName = {},
                onNavDataInvolved = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}