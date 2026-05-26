package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.presenter.theme.AlertDialogCheckDesign
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.utils.UiStatusState

const val TAG_LOCAL_EDIT_BUTTON = "tag_local_edit_button"
const val TAG_DATA_LOCAL_EDIT_BUTTON = "tag_data_local_edit_button"
const val TAG_SUPPORT_TEAMS_EDIT_BUTTON = "tag_support_teams_edit_button"

@Composable
fun LocalSupportScreen(
    viewModel: LocalSupportViewModel = hiltViewModel(),
    onNavDataInitial: () -> Unit,
    onNavLocal: () -> Unit,
    onNavDataLocal: () -> Unit,
    onNavSupportTeams: () -> Unit,
    onNavCarFull: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            LocalSupportContent(
                address = uiState.address,
                latitude = uiState.latitude,
                longitude = uiState.longitude,
                dataLocalList = uiState.dataLocalList,
                supportTeams = uiState.supportTeams,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavDataInitial = onNavDataInitial,
                onNavLocal = onNavLocal,
                onNavDataLocal = onNavDataLocal,
                onNavSupport = onNavSupportTeams,
                onNavCarFull = onNavCarFull,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun LocalSupportContent(
    address: String,
    latitude: String,
    longitude: String,
    dataLocalList: List<Pair<String, String>>,
    supportTeams: String,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavDataInitial: () -> Unit,
    onNavLocal: () -> Unit,
    onNavDataLocal: () -> Unit,
    onNavSupport: () -> Unit,
    onNavCarFull: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_title_card
            )
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray.copy(alpha = 0.2f))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.text_item_local
                            ),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        if(address.isEmpty() && latitude.isEmpty() && longitude.isEmpty()){
                            Text("-")
                        } else {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append(
                                            stringResource(
                                                id = R.string.text_address
                                            )
                                        )
                                    }
                                    append(" ")
                                    append(address.ifEmpty { "-" })
                                }
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append(
                                            stringResource(
                                                id = R.string.text_longitude
                                            )
                                        )
                                    }
                                    append(" ")
                                    append(longitude.ifEmpty { "-" })
                                }
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append(
                                            stringResource(
                                                id = R.string.text_latitude
                                            )
                                        )
                                    }
                                    append(" ")
                                    append(latitude.ifEmpty { "-" })
                                }
                            )

                        }
                    }
                    Button(
                        onClick = onNavLocal,
                        Modifier
                            .testTag(TAG_LOCAL_EDIT_BUTTON)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.text_pattern_edit
                            ),
                        )
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray.copy(alpha = 0.2f))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.text_title_data_local
                            ),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        if(dataLocalList.isEmpty()){
                            Text("-")
                        } else {
                            dataLocalList.forEach {
                                Text(buildAnnotatedString {
                                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                            append("${it.first}: ")
                                        }
                                        append(it.second)
                                    }
                                )
                            }
                        }

                    }
                    Button(
                        onClick = onNavDataLocal,
                        Modifier
                            .testTag(TAG_DATA_LOCAL_EDIT_BUTTON)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.text_pattern_edit
                            ),
                        )
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray.copy(alpha = 0.2f))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.text_support_teams
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Text(supportTeams)
                    }
                    Button(
                        onClick = onNavSupport,
                        Modifier
                            .testTag(TAG_SUPPORT_TEAMS_EDIT_BUTTON)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.text_pattern_edit
                            ),
                        )
                    }
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
                onClick = onNavDataInitial,
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_return),
                    font = 18
                )
            }
            Button(
                onClick = onNavCarFull,
                modifier = Modifier.weight(1f),
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_next),
                    font = 18
                )
            }
        }

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LocalSupportPagePreviewIsNull() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LocalSupportContent(
                address = "",
                latitude = "",
                longitude = "",
                dataLocalList = emptyList(),
                supportTeams = "-",
                onCloseDialog = {},
                status = UiStatusState(
                    flagDialog = false,
                    failure = "",
                    errors = Errors.FIELD_EMPTY,
                ),
                onNavDataInitial = {},
                onNavLocal = {},
                onNavDataLocal = {},
                onNavSupport = {},
                onNavCarFull = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LocalSupportPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LocalSupportContent(
                address = "RUA ANTONIA NABA DE ALMEIDA, 75 - TABATINGA - SP",
                latitude = "-25,35625",
                longitude = "-26,35665",
                dataLocalList = listOf("TRAÇADO" to "RETA", "PERFIL" to "ACENTUADO"),
                supportTeams = "GUINCHOS - BOMBEIROS",
                onCloseDialog = {},
                status = UiStatusState(
                    flagDialog = false,
                    failure = "",
                    errors = Errors.FIELD_EMPTY,
                ),
                onNavDataInitial = {},
                onNavLocal = {},
                onNavDataLocal = {},
                onNavSupport = {},
                onNavCarFull = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LocalSupportPagePreviewIsPartNull() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LocalSupportContent(
                address = "",
                latitude = "",
                longitude = "-27.15368",
                dataLocalList = emptyList(),
                supportTeams = "-",
                onCloseDialog = {},
                status = UiStatusState(
                    flagDialog = false,
                    failure = "",
                    errors = Errors.FIELD_EMPTY,
                ),
                onNavDataInitial = {},
                onNavLocal = {},
                onNavDataLocal = {},
                onNavSupport = {},
                onNavCarFull = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
