package br.com.usinasantafe.cav.presenter.view.card.dataLocal

import androidx.activity.compose.BackHandler
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
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultListDesign
import br.com.usinasantafe.cav.presenter.theme.MsgUpdate
import br.com.usinasantafe.cav.presenter.theme.Progress
import br.com.usinasantafe.cav.utils.UpdateStatusState

@Composable
fun OptionDataLocalScreen(
    viewModel: OptionDataLocalViewModel = hiltViewModel(),
    onNavMenu: () -> Unit,
    onNavItem: (Int) -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.list()
            }

            OptionDataLocalContent(
                list = uiState.list,
                updateDatabase = viewModel::updateDatabase,
                flagAccess = uiState.flagAccess,
                setCloseDialog = viewModel::setCloseDialog,
                status = uiState.status,
                onNavMenu = onNavMenu,
                onNavItem = onNavItem,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun OptionDataLocalContent(
    list: List<ItemListScreenModel>,
    updateDatabase: () -> Unit,
    flagAccess: Boolean,
    setCloseDialog: () -> Unit,
    status: UpdateStatusState,
    onNavMenu: () -> Unit,
    onNavItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_option_data_local
            )
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f),
        ) {
            items(list) { item ->
                ItemDefaultListDesign(
                    text = item.description,
                    setActionItem = {
                        onNavItem(item.id)
                    },
                    font = 26
                )
            }
        }
        ButtonMaxWidth(R.string.text_pattern_update) { updateDatabase() }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        ButtonMaxWidth(R.string.text_pattern_return) { onNavMenu() }

        BackHandler {}

        if (status.flagDialog) {
            MsgUpdate(status = status, setCloseDialog = setCloseDialog)
        }

        if (status.flagProgress) {
            Progress(status)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun OptionDataLocalPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            OptionDataLocalContent(
                list = emptyList(),
                updateDatabase = {},
                flagAccess = false,
                setCloseDialog = {},
                status = UpdateStatusState(
                    flagFailure = false,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                    flagProgress = false,
                    currentProgress = 0f,
                    levelUpdate = null,
                    tableUpdate = "",
                    flagDialog = false,
                ),
                onNavMenu = {},
                onNavItem = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OptionDataLocalPagePreviewWithData() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            OptionDataLocalContent(
                list = listOf(
                    ItemListScreenModel(
                        id = 1,
                        description = "TRAÇADO"
                    ),
                    ItemListScreenModel(
                        id = 2,
                        description = "PERFIL"
                    ),
                    ItemListScreenModel(
                        id = 3,
                        description = "LOMBADA"
                    )
                ),
                updateDatabase = {},
                flagAccess = false,
                setCloseDialog = {},
                status = UpdateStatusState(
                    flagFailure = false,
                    errors = Errors.FIELD_EMPTY,
                    failure = "",
                    flagProgress = false,
                    currentProgress = 0f,
                    levelUpdate = null,
                    tableUpdate = "",
                    flagDialog = false,
                ),
                onNavMenu = {},
                onNavItem = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OptionDataLocalPagePreviewWithFailureUpdate() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            OptionDataLocalContent(
                list = listOf(
                    ItemListScreenModel(
                        id = 1,
                        description = "TRAÇADO"
                    ),
                    ItemListScreenModel(
                        id = 2,
                        description = "PERFIL"
                    ),
                    ItemListScreenModel(
                        id = 3,
                        description = "LOMBADA"
                    )
                ),
                updateDatabase = {},
                flagAccess = false,
                setCloseDialog = {},
                status = UpdateStatusState(
                    flagFailure = true,
                    errors = Errors.UPDATE,
                    failure = "Failure",
                    flagProgress = false,
                    currentProgress = 0f,
                    levelUpdate = null,
                    tableUpdate = "",
                    flagDialog = true,
                ),
                onNavMenu = {},
                onNavItem = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OptionDataLocalPagePreviewWithProgressUpdate() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            OptionDataLocalContent(
                list = listOf(
                    ItemListScreenModel(
                        id = 1,
                        description = "TRAÇADO"
                    ),
                    ItemListScreenModel(
                        id = 2,
                        description = "PERFIL"
                    ),
                    ItemListScreenModel(
                        id = 3,
                        description = "LOMBADA"
                    )
                ),
                updateDatabase = {},
                flagAccess = false,
                setCloseDialog = {},
                status = UpdateStatusState(
                    flagFailure = false,
                    errors = Errors.UPDATE,
                    failure = "Failure",
                    flagProgress = true,
                    levelUpdate = LevelUpdate.RECOVERY,
                    tableUpdate = "tb_option",
                    currentProgress = 0.3333f,
                    flagDialog = false,
                ),
                onNavMenu = {},
                onNavItem = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OptionDataLocalPagePreviewWithFailureError() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            OptionDataLocalContent(
                list = listOf(
                    ItemListScreenModel(
                        id = 1,
                        description = "TRAÇADO"
                    ),
                    ItemListScreenModel(
                        id = 2,
                        description = "PERFIL"
                    ),
                    ItemListScreenModel(
                        id = 3,
                        description = "LOMBADA"
                    )
                ),
                updateDatabase = {},
                flagAccess = false,
                setCloseDialog = {},
                status = UpdateStatusState(
                    flagFailure = true,
                    errors = Errors.EXCEPTION,
                    failure = "Failure",
                    flagProgress = false,
                    currentProgress = 0f,
                    levelUpdate = null,
                    tableUpdate = "",
                    flagDialog = true,
                ),
                onNavMenu = {},
                onNavItem = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}