package br.com.usinasantafe.cav.presenter.view.card.involved.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeDetail
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.utils.UiStatusState

const val TAG_DETAIL_VEHICLE_FOREIGN_TEXT_FIELD = "tag_detail_vehicle_foreign_text_field"

@Composable
fun DetailVehicleInvolvedScreen(
    viewModel: DetailVehicleForeignViewModel = hiltViewModel(),
    onNavBrand: () -> Unit,
    onNavState: () -> Unit,
    onNavData: () -> Unit,
    onNavDocument: () -> Unit,
    onNavPassengerList: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            DetailVehicleInvolvedContent(
                option = uiState.option,
                typeDetail = uiState.typeDetail,
                text = uiState.text,
                onTextChanged = viewModel::onTextChanged,
                set = viewModel::set,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavBrand = onNavBrand,
                onNavState = onNavState,
                onNavData = onNavData,
                onNavDocument = onNavDocument,
                onNavPassengerList = onNavPassengerList,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun DetailVehicleInvolvedContent(
    option: Option,
    typeDetail: TypeDetail,
    text: String,
    onTextChanged: (String) -> Unit,
    set: () -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavBrand: () -> Unit,
    onNavState: () -> Unit,
    onNavData: () -> Unit,
    onNavDocument: () -> Unit,
    onNavPassengerList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_title_detail
            )
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .testTag(TAG_DETAIL_VEHICLE_FOREIGN_TEXT_FIELD),
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
                    when(typeDetail){
                        TypeDetail.VEHICLE,
                        TypeDetail.VEHICLE_SEC -> onNavBrand()
                        TypeDetail.PEOPLE,
                        TypeDetail.PASSENGER -> onNavState()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(text = stringResource(id = R.string.text_pattern_cancel))
            }
            Button(
                onClick = set,
                modifier = Modifier.weight(1f),
            ) {
                TextButtonDesign(text = stringResource(id = R.string.text_pattern_save))
            }
        }

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }

    LaunchedEffect(status.flagAccess) {
        if (status.flagAccess) {
            when(typeDetail){
                TypeDetail.VEHICLE,
                TypeDetail.VEHICLE_SEC -> {
                    when(option){
                        Option.INSERT -> onNavDocument()
                        Option.EDIT -> onNavData()
                    }
                }
                TypeDetail.PEOPLE -> {
                    when(option){
                        Option.INSERT -> onNavPassengerList()
                        Option.EDIT -> onNavData()
                    }
                }
                TypeDetail.PASSENGER -> onNavPassengerList()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailVehicleInvolvedPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DetailVehicleInvolvedContent(
                option = Option.INSERT,
                typeDetail = TypeDetail.VEHICLE,
                text = "Text",
                onTextChanged = {},
                set = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavBrand = {},
                onNavState = {},
                onNavData = {},
                onNavDocument = {},
                onNavPassengerList = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}