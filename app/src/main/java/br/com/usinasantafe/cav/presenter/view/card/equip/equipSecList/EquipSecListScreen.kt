package br.com.usinasantafe.cav.presenter.view.card.equip.equipSecList

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.presenter.theme.AlertDialogCheckDesign
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultEditDelListScreenModel
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.utils.UiStatusState

@Composable
fun EquipSecListScreen(
    viewModel: EquipSecListOwnViewModel = hiltViewModel(),
    onNavDetail: () -> Unit,
    onNavColab: () -> Unit,
    onNavData: () -> Unit,
    onNavEquip: (Int) -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            EquipSecListContent(
                option = uiState.option,
                list = uiState.list,
                idSelection = uiState.idSelection,
                onSelectionDelete = viewModel::onSelectionDelete,
                onCloseDialog = viewModel::onCloseDialog,
                flagDialogCheck = uiState.flagDialogCheck,
                onDialogCheck = viewModel::onDialogCheck,
                delete = viewModel::delete,
                status = uiState.status,
                onNavDetail = onNavDetail,
                onNavColab = onNavColab,
                onNavData = onNavData,
                onNavEquip = onNavEquip,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun EquipSecListContent(
    option: Option,
    list: List<ItemListScreenModel>,
    idSelection: Int,
    onSelectionDelete: (Int) -> Unit,
    flagDialogCheck: Boolean,
    onDialogCheck: (Boolean) -> Unit,
    onCloseDialog: () -> Unit,
    delete: () -> Unit,
    status: UiStatusState,
    onNavDetail: () -> Unit,
    onNavColab: () -> Unit,
    onNavData: () -> Unit,
    onNavEquip: (Int) -> Unit,
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
                        onNavEquip(item.id)
                    },
                    onClickDel = {
                        onSelectionDelete(item.id)
                    }
                )
            }
        }
        ButtonMaxWidth(R.string.text_pattern_insert) {
            onNavEquip(0)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {
                    when(option) {
                        Option.INSERT -> onNavDetail()
                        Option.EDIT -> onNavData()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(text = stringResource(id = R.string.text_pattern_cancel))
            }
            Button(
                onClick = {
                    when(option) {
                        Option.INSERT -> onNavColab()
                        Option.EDIT -> onNavData()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(text = stringResource(id = R.string.text_pattern_next))
            }
        }
        BackHandler {}

        if(flagDialogCheck){
            val desc = list.first{ it.id == idSelection }.desc
            AlertDialogCheckDesign(
                text = stringResource(
                    id = R.string.text_check_delete_equip_sec,
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
fun EquipSecListPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            EquipSecListContent(
                option = Option.INSERT,
                list = emptyList(),
                idSelection = 0,
                onSelectionDelete = {},
                flagDialogCheck = true,
                onDialogCheck = {},
                delete = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavColab = {},
                onNavDetail = {},
                onNavData = {},
                onNavEquip = {},
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
                option = Option.INSERT,
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
                idSelection = 0,
                onSelectionDelete = {},
                flagDialogCheck = true,
                onDialogCheck = {},
                delete = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavColab = {},
                onNavDetail = {},
                onNavData = {},
                onNavEquip = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}