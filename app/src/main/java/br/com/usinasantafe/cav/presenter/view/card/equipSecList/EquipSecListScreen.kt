package br.com.usinasantafe.cav.presenter.view.card.equipSecList

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
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.presenter.theme.AlertDialogCheckDesign
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultEditDelListScreenModel
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.utils.UiStatusState

@Composable
fun EquipSecListScreen(
    viewModel: EquipSecListViewModel = hiltViewModel(),
    onNavDataVehicleOwn: () -> Unit,
    onNavEquip: () -> Unit,
    onNavDataEquip: (Int) -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            EquipSecListContent(
                list = uiState.list,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavDataVehicleOwn = onNavDataVehicleOwn,
                onNavEquip = onNavEquip,
                onNavDataEquip = onNavDataEquip,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun EquipSecListContent(
    list: List<ItemListScreenModel>,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavDataVehicleOwn: () -> Unit,
    onNavEquip: () -> Unit,
    onNavDataEquip: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_equip_sec
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
                        onNavDataEquip(item.id)
                    },
                )
            }
        }
        ButtonMaxWidth(R.string.text_pattern_insert, onClick = onNavEquip)
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        ButtonMaxWidth(R.string.text_pattern_return, onClick = onNavDataVehicleOwn)
        BackHandler {}

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

        LaunchedEffect(status.flagAccess) {
            if(status.flagAccess) {
                onNavDataVehicleOwn()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EquipSecListPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            EquipSecListContent(
                list = emptyList(),
                onCloseDialog = {},
                status = UiStatusState(),
                onNavDataVehicleOwn = {},
                onNavEquip = {},
                onNavDataEquip = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EquipSecListPagePreviewWithData() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            EquipSecListContent(
                list = listOf(
                    ItemListScreenModel(
                        id = 1,
                        desc = "ITEM 1"
                    ),
                    ItemListScreenModel(
                        id = 2,
                        desc = "ITEM 2"
                    ),
                    ItemListScreenModel(
                        id = 3,
                        desc = "ITEM 3"
                    )
                ),
                onCloseDialog = {},
                status = UiStatusState(),
                onNavDataVehicleOwn = {},
                onNavEquip = {},
                onNavDataEquip = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}