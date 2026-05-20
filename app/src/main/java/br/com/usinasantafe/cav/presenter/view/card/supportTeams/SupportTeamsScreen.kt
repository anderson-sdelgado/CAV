package br.com.usinasantafe.cav.presenter.view.card.supportTeams

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.CheckboxDefault
import br.com.usinasantafe.cav.presenter.theme.MsgUpdate
import br.com.usinasantafe.cav.presenter.theme.Progress
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.utils.UpdateStatusState

@Composable
fun SupportTeamsScreen(
    viewModel: SupportTeamsViewModel = hiltViewModel(),
    onNavMenu: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val list = viewModel.list

            LaunchedEffect(Unit) {
                viewModel.list()
            }

            SupportTeamsScreenContent(
                list = list,
                onCheckChange = viewModel::onCheckChange,
                updateDatabase = viewModel::updateDatabase,
                save = viewModel::save,
                flagAccess = uiState.flagAccess,
                setCloseDialog = viewModel::setCloseDialog,
                status = uiState.status,
                onNavMenu = onNavMenu,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun SupportTeamsScreenContent(
    list: List<ItemCheckBoxScreenModel>,
    onCheckChange: (Int, Boolean) -> Unit,
    updateDatabase: () -> Unit,
    save: () -> Unit,
    flagAccess: Boolean,
    setCloseDialog: () -> Unit,
    status: UpdateStatusState,
    onNavMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_support_teams
            )
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(list, key = { it.id }) { item ->
                CheckboxDefault(
                    id = item.id,
                    text = item.desc,
                    checked = item.flag,
                    onChecked = { isChecked ->
                        onCheckChange(item.id, isChecked)
                    }
                )
            }
        }
        ButtonMaxWidth(R.string.text_pattern_update) { updateDatabase() }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        )  {
            Button(
                onClick = onNavMenu,
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_return),
                    padding = 10
                )
            }
            Button(
                onClick = save,
                modifier = Modifier.weight(1f),
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_save),
                    padding = 10
                )
            }
        }

        BackHandler {}

        if (status.flagDialog) {
            MsgUpdate(status = status, onClickOk = setCloseDialog, value = stringResource(id = R.string.text_title_attendant))
        }

        if (status.flagProgress) {
            Progress(status)
        }

    }

    LaunchedEffect(flagAccess) {
        if (flagAccess) {
            onNavMenu()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SupportTeamsScreenPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SupportTeamsScreenContent(
                list = emptyList(),
                onCheckChange = { _, _ -> },
                updateDatabase = {},
                save = {},
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
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SupportTeamsScreenPagePreviewWithData() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SupportTeamsScreenContent(
                list = listOf(
                    ItemCheckBoxScreenModel(
                        id = 1,
                        desc = "GUINCHOS",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 2,
                        desc = "SOS - AMBULÂNCIA",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 3,
                        desc = "SOS - RESGATE",
                        flag = false
                    ),
                ),
                onCheckChange = { _, _ -> },
                updateDatabase = {},
                save = {},
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
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}