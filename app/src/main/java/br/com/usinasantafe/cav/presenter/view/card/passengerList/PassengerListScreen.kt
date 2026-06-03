package br.com.usinasantafe.cav.presenter.view.card.passengerList

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.presenter.theme.AlertDialogCheckDesign
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultEditDelListScreenModel
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.utils.UiStatusState

@Composable
fun PassengerListScreen(
    viewModel: PassengerListViewModel = hiltViewModel(),
    onNavDataVehicleOwn: () -> Unit,
    onNavDataVehicleInvolved: () -> Unit,
    onNavDataColab: (Int) -> Unit,
    onNavColab: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            PassengerListContent(
                flowNote = uiState.flowNote,
                list = uiState.list,
                idSelection = uiState.idSelection,
                onSelectionDelete = viewModel::onSelectionDelete,
                flagDialogCheck = uiState.flagDialogCheck,
                onDialogCheck = viewModel::onDialogCheck,
                delete = viewModel::delete,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavDataVehicleOwn = onNavDataVehicleOwn,
                onNavDataVehicleInvolved = onNavDataVehicleInvolved,
                onNavDataColab = onNavDataColab,
                onNavColab = onNavColab,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun PassengerListContent(
    flowNote: FlowNote,
    list: List<ItemListScreenModel>,
    idSelection: Int,
    onSelectionDelete: (Int) -> Unit,
    flagDialogCheck: Boolean,
    onDialogCheck: (Boolean) -> Unit,
    onCloseDialog: () -> Unit,
    delete: () -> Unit,
    status: UiStatusState,
    onNavDataVehicleOwn: () -> Unit,
    onNavDataVehicleInvolved: () -> Unit,
    onNavColab: () -> Unit,
    onNavDataColab: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_passenger
            )
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(list) { item ->
                ItemDefaultEditDelListScreenModel(
                    id = item.id,
                    desc = item.desc,
                    onClickEdit = {
                        onNavDataColab(item.id)
                    },
                    onClickDel = {
                        onSelectionDelete(item.id)
                    }
                )
            }
        }
        ButtonMaxWidth(R.string.text_pattern_insert, onClick = onNavColab)
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        ButtonMaxWidth(R.string.text_pattern_return) {
            when(flowNote){
                FlowNote.PASSENGER_COLAB -> onNavDataVehicleOwn()
                FlowNote.PASSENGER_INVOLVED -> onNavDataVehicleInvolved()
                else -> {}
            }
        }
        BackHandler {}

        if(flagDialogCheck){
            val desc = list.first{ it.id == idSelection }.desc
            AlertDialogCheckDesign(
                text = stringResource(
                    id = R.string.text_check_delete_passenger,
                    desc
                ),
                onClickDismiss = { onDialogCheck(false) },
                onClickYes = delete
            )
        }

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PassengerListPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PassengerListContent(
                flowNote = FlowNote.EQUIP,
                list = emptyList(),
                idSelection = 0,
                onSelectionDelete = {},
                flagDialogCheck = true,
                onDialogCheck = {},
                delete = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavDataVehicleOwn = {},
                onNavDataVehicleInvolved = {},
                onNavDataColab = {},
                onNavColab = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}