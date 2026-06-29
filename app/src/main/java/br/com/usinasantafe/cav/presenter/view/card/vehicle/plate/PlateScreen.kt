package br.com.usinasantafe.cav.presenter.view.card.vehicle.plate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.utils.UiStatusState

const val TAG_PLATE_INVOLVED_TEXT_FIELD = "tag_plate_involved_text_field"

@Composable
fun PlateScreen(
    viewModel: PlateViewModel = hiltViewModel(),
    onNavMenu: () -> Unit,
    onNavDataVehicle: () -> Unit,
    onNavBrand: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            PlateContent(
                option = uiState.option,
                text = uiState.text,
                onTextChanged = viewModel::onTextChanged,
                set = viewModel::set,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavMenu = onNavMenu,
                onNavDataVehicle = onNavDataVehicle,
                onNavBrand = onNavBrand,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun PlateContent(
    option: Option,
    text: String,
    onTextChanged: (String) -> Unit,
    set: () -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavMenu: () -> Unit,
    onNavDataVehicle: () -> Unit,
    onNavBrand: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .imePadding()
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_plate
            )
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .testTag(TAG_PLATE_INVOLVED_TEXT_FIELD),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 28.sp
            ),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {
                    when(option){
                        Option.INSERT -> onNavMenu()
                        Option.EDIT -> onNavDataVehicle()
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

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }

    LaunchedEffect(status.flagAccess) {
        if (status.flagAccess) {
            when(option){
                Option.INSERT -> onNavBrand()
                Option.EDIT -> onNavDataVehicle()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlatePagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PlateContent(
                option = Option.INSERT,
                text = "Text",
                onTextChanged = {},
                set = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavMenu = {},
                onNavDataVehicle = {},
                onNavBrand = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}