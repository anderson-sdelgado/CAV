package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.passengerList

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
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.presenter.theme.AlertDialogCheckDesign
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultEditDelListScreenModel
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign

@Composable
fun PassengerListScreen(
    viewModel: PassengerListViewModel = hiltViewModel(),
    onNavDetail: () -> Unit,
    onNavMenu: () -> Unit,
    onNavData: () -> Unit,
    onNavColab: (Int) -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            PassengerListContent(
                option = uiState.option,
                list = uiState.list,
                idSelection = uiState.idSelection,
                onCheckDelete = viewModel::onCheckDelete,
                flagDialogCheck = uiState.flagDialogCheck,
                onDialogCheck = viewModel::onDialogCheck,
                delete = viewModel::delete,
                onCloseDialog = viewModel::onCloseDialog,
                flagDialog = uiState.flagDialog,
                failure = uiState.failure,
                errors = uiState.errors,
                onNavDetail = onNavDetail,
                onNavMenu = onNavMenu,
                onNavData = onNavData,
                onNavColab = onNavColab,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun PassengerListContent(
    option: Option,
    list: List<ItemListScreenModel>,
    idSelection: Int,
    onCheckDelete: (Int) -> Unit,
    flagDialogCheck: Boolean,
    onDialogCheck: (Boolean) -> Unit,
    delete: () -> Unit,
    onCloseDialog: () -> Unit,
    flagDialog: Boolean,
    failure: String,
    errors: Errors,
    onNavDetail: () -> Unit,
    onNavMenu: () -> Unit,
    onNavData: () -> Unit,
    onNavColab: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_title_passenger
            )
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(list) { item ->
                ItemDefaultEditDelListScreenModel(
                    id = item.id,
                    desc = item.description,
                    onClickEdit = {
                        onNavColab(item.id)
                    },
                    onClickDel = {
                        onCheckDelete(item.id)
                    }
                )
            }
        }
        ButtonMaxWidth(R.string.text_pattern_insert) {
            onNavColab(0)
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
                        Option.INSERT -> onNavMenu()
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
            val desc = list.first{ it.id == idSelection }.description
            AlertDialogCheckDesign(
                text = stringResource(
                    id = R.string.text_check_delete_passenger,
                    desc
                ),
                onClickDismiss = { onDialogCheck(false) },
                onClickYes = delete
            )
        }

        if(flagDialog) {
            MsgErrors(errors, onCloseDialog, failure)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PassengerListPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PassengerListContent(
                option = Option.INSERT,
                list = emptyList(),
                idSelection = 0,
                onCheckDelete = {},
                flagDialogCheck = true,
                onDialogCheck = {},
                delete = {},
                onCloseDialog = {},
                flagDialog = false,
                failure = "",
                errors = Errors.FIELD_EMPTY,
                onNavDetail = {},
                onNavMenu = {},
                onNavData = {},
                onNavColab = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}