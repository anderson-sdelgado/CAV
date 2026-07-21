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
    onNavColab: () -> Unit,
    onNavDataColab: (Int) -> Unit,
    onNavDocument: () -> Unit,
    onNavDataInvolved: (Int) -> Unit,
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
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavDataVehicleOwn = onNavDataVehicleOwn,
                onNavDataVehicleInvolved = onNavDataVehicleInvolved,
                onNavColab = onNavColab,
                onNavDataColab = onNavDataColab,
                onNavDocument = onNavDocument,
                onNavDataInvolved = onNavDataInvolved,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun PassengerListContent(
    flowNote: FlowNote,
    list: List<ItemListScreenModel>,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavDataVehicleOwn: () -> Unit,
    onNavDataVehicleInvolved: () -> Unit,
    onNavColab: () -> Unit,
    onNavDataColab: (Int) -> Unit,
    onNavDocument: () -> Unit,
    onNavDataInvolved: (Int) -> Unit,
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
                        when(flowNote){
                            FlowNote.PASSENGER_COLAB -> onNavDataColab(item.id)
                            else -> onNavDataInvolved(item.id)
                        }
                    },
                )
            }
        }
        ButtonMaxWidth(R.string.text_pattern_insert) {
            when(flowNote){
                FlowNote.PASSENGER_COLAB -> onNavColab()
                else -> onNavDocument()
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        ButtonMaxWidth(R.string.text_pattern_return) {
            when(flowNote){
                FlowNote.PASSENGER_COLAB -> onNavDataVehicleOwn()
                else -> onNavDataVehicleInvolved()
            }
        }
        BackHandler {}

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
                onCloseDialog = {},
                status = UiStatusState(),
                onNavDataVehicleOwn = {},
                onNavDataVehicleInvolved = {},
                onNavColab = {},
                onNavDataColab = {},
                onNavDocument = {},
                onNavDataInvolved = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}