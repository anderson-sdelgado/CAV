package br.com.usinasantafe.cav.presenter.view.card.breathalyzer.check

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.CheckboxDefault
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultListDesign
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.utils.UiStatusState

@Composable
fun CheckBreathalyzerScreen(
    viewModel: CheckBreathalyzerViewModel = hiltViewModel(),
    onNavState: () -> Unit,
    onNavDetail: () -> Unit,
    onNavCountBreathalyzer: () -> Unit,
    onNavDataColab: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            CheckBreathalyzerContent(
                option = uiState.option,
                flagRealized = uiState.flagRealized,
                flagResult = uiState.flagResult,
                onChangeFlagRealized = viewModel::onChangeFlagRealized,
                onChangeFlagResult = viewModel::onChangeFlagResult,
                set = viewModel::set,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavState = onNavState,
                onNavDetail = onNavDetail,
                onNavCountBreathalyzer = onNavCountBreathalyzer,
                onNavDataColab = onNavDataColab,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun CheckBreathalyzerContent(
    option: Option,
    flagRealized: Boolean?,
    flagResult: Boolean?,
    onChangeFlagRealized: (Boolean) -> Unit,
    onChangeFlagResult: (Boolean) -> Unit,
    set: () -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavState: () -> Unit,
    onNavDetail: () -> Unit,
    onNavCountBreathalyzer: () -> Unit,
    onNavDataColab: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_breathalyzer
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            item {
                CheckboxDefault(
                    id = 1,
                    text = stringResource(
                        id = R.string.text_pattern_no
                    ),
                    checked = flagRealized == false,
                    onChecked = { onChangeFlagRealized(false) }
                )
            }
            item {
                CheckboxDefault(
                    id = 2,
                    text = stringResource(
                        id = R.string.text_pattern_yes
                    ),
                    checked = flagRealized == true,
                    onChecked = { onChangeFlagRealized(true) }
                )
            }
            if (flagRealized == true) {
                item {
                    CheckboxDefault(
                        id = 3,
                        text = stringResource(
                            id = R.string.text_pattern_negative
                        ),
                        checked = flagResult == false,
                        paddingStart = 20,
                        onChecked = { onChangeFlagResult(false) }
                    )
                }
                item {
                    CheckboxDefault(
                        id = 4,
                        text = stringResource(
                            id = R.string.text_pattern_positive
                        ),
                        checked = flagResult == true,
                        paddingStart = 20,
                        onChecked = { onChangeFlagResult(true) }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        )  {
            Button(
                onClick = {
                    when(option){
                        Option.INSERT -> onNavState()
                        Option.EDIT -> onNavDataColab()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_return),
                    padding = 10
                )
            }
            Button(
                onClick = set,
                modifier = Modifier.weight(1f),
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_save),
                    padding = 10
                )
            }
        }
        BackHandler {}

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }

    LaunchedEffect(status.flagAccess) {
        if (status.flagAccess) {
            if(flagResult == true) {
                onNavCountBreathalyzer()
            } else {
                when(option){
                    Option.INSERT -> onNavDetail()
                    Option.EDIT -> onNavDataColab()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBreathalyzerPagePreviewAllIsNull() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CheckBreathalyzerContent(
                option = Option.INSERT,
                flagRealized = null,
                flagResult = null,
                onChangeFlagRealized = {},
                onChangeFlagResult = {},
                set = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavState = {},
                onNavDetail = {},
                onNavCountBreathalyzer = {},
                onNavDataColab = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBreathalyzerPagePreviewRealizedIsNo() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CheckBreathalyzerContent(
                option = Option.INSERT,
                flagRealized = false,
                flagResult = null,
                onChangeFlagRealized = {},
                onChangeFlagResult = {},
                set = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavState = {},
                onNavDetail = {},
                onNavCountBreathalyzer = {},
                onNavDataColab = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBreathalyzerPagePreviewRealizedIsYes() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CheckBreathalyzerContent(
                option = Option.INSERT,
                flagRealized = true,
                flagResult = null,
                onChangeFlagRealized = {},
                onChangeFlagResult = {},
                set = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavState = {},
                onNavDetail = {},
                onNavCountBreathalyzer = {},
                onNavDataColab = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBreathalyzerPagePreviewRealizedIsYesAndResultIsNegative() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CheckBreathalyzerContent(
                option = Option.INSERT,
                flagRealized = true,
                flagResult = false,
                onChangeFlagRealized = {},
                onChangeFlagResult = {},
                set = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavState = {},
                onNavDetail = {},
                onNavCountBreathalyzer = {},
                onNavDataColab = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBreathalyzerPagePreviewRealizedIsYesAndResultIsPositive() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CheckBreathalyzerContent(
                option = Option.INSERT,
                flagRealized = true,
                flagResult = true,
                onChangeFlagRealized = {},
                onChangeFlagResult = {},
                set = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavState = {},
                onNavDetail = {},
                onNavCountBreathalyzer = {},
                onNavDataColab = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}