package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
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

const val TAG_ATTENDANT_EDIT_BUTTON = "tag_attendant_edit_button"
const val TAG_CAR_EDIT_BUTTON = "tag_car_edit_button"
const val TAG_NATURE_EDIT_BUTTON = "tag_nature_edit_button"
const val TAG_TYPE_ACCIDENT_EDIT_BUTTON = "tag_type_accident_edit_button"

@Composable
fun DataInitialScreen(
    viewModel: DataInitialViewModel = hiltViewModel(),
    onNavSplash: () -> Unit,
    onNavAttendant: () -> Unit,
    onNavCar: () -> Unit,
    onNavNature: () -> Unit,
    onNavTypeAccident: () -> Unit,
    onNavLocalSupport: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            DataInitialContent(
                attendant = uiState.attendant,
                car = uiState.car,
                nature = uiState.nature,
                typeAccident = uiState.typeAccident,
                onCloseDialog = viewModel::onCloseDialog,
                flagDialogCheck = uiState.flagDialogCheck,
                onDialogCheck = viewModel::onDialogCheck,
                flagCancel = uiState.flagCancel,
                cancel = viewModel::cancel,
                flagDialog = uiState.flagDialog,
                failure = uiState.failure,
                errors = uiState.errors,
                onNavSplash = onNavSplash,
                onNavAttendant = onNavAttendant,
                onNavCar = onNavCar,
                onNavNature = onNavNature,
                onNavTypeAccident = onNavTypeAccident,
                onNavLocalSupport = onNavLocalSupport,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun DataInitialContent(
    attendant: String,
    car: String,
    nature: String,
    typeAccident: String,
    onCloseDialog: () -> Unit,
    flagDialogCheck: Boolean,
    onDialogCheck: (Boolean) -> Unit,
    flagCancel: Boolean,
    cancel: () -> Unit,
    flagDialog: Boolean,
    failure: String,
    errors: Errors,
    onNavSplash: () -> Unit,
    onNavAttendant: () -> Unit,
    onNavCar: () -> Unit,
    onNavNature: () -> Unit,
    onNavTypeAccident: () -> Unit,
    onNavLocalSupport: () -> Unit,
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
                                id = R.string.text_item_attendant
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = attendant)
                    }
                    Button(
                        onClick = onNavAttendant,
                        Modifier
                            .testTag(TAG_ATTENDANT_EDIT_BUTTON)
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
                                id = R.string.text_item_car
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Text(car)
                    }
                    Button(
                        onClick = onNavCar,
                        Modifier
                            .testTag(TAG_CAR_EDIT_BUTTON)
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
                                id = R.string.text_nature
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Text(nature)
                    }
                    Button(
                        onClick = onNavNature,
                        Modifier
                            .testTag(TAG_NATURE_EDIT_BUTTON)
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
                                id = R.string.text_type_accident
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Text(typeAccident)
                    }
                    Button(
                        onClick = onNavTypeAccident,
                        Modifier
                            .testTag(TAG_TYPE_ACCIDENT_EDIT_BUTTON)
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
                onClick = { onDialogCheck(true) },
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(text = stringResource(id = R.string.text_pattern_cancel))
            }
            Button(
                onClick = onNavLocalSupport,
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(text = stringResource(id = R.string.text_pattern_next))
            }
        }

        if(flagDialog) {
            MsgErrors(errors, onCloseDialog, failure)
        }

        if(flagDialogCheck){
            AlertDialogCheckDesign(
                text = stringResource(id = R.string.text_close_card),
                setCloseDialog = { onDialogCheck(false) },
                setActionButtonYes = cancel
            )
        }
    }

    LaunchedEffect(flagCancel) {
        if(flagCancel) {
            onNavSplash()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DataInitialPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DataInitialContent(
                attendant = "19759 - ANDERSON DA SILVA DELGADO",
                car = "100 - AMBULANCIA",
                nature = "ACIDENTE - ANIMAIS",
                typeAccident = "ATROP. ANIMAL - COLISÃO LATERAL - INCÊNDIO",
                flagDialog = false,
                onCloseDialog = {},
                flagDialogCheck = false,
                onDialogCheck = {},
                flagCancel = false,
                cancel = {},
                failure = "",
                errors = Errors.FIELD_EMPTY,
                onNavSplash = {},
                onNavAttendant = {},
                onNavCar = {},
                onNavNature = {},
                onNavTypeAccident = {},
                onNavLocalSupport = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}