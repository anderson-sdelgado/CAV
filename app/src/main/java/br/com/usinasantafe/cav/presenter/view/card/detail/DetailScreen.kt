package br.com.usinasantafe.cav.presenter.view.card.detail

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
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.utils.UiStatusState

const val TAG_DETAIL_VEHICLE_OWN_TEXT_FIELD = "tag_detail_vehicle_own_text_field"

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onNavState: () -> Unit,
    onNavEquip: () -> Unit,
    onNavColab: () -> Unit,
    onNavDataEquip: () -> Unit,
    onNavDataColab: () -> Unit,
    onNavEquipSecList: () -> Unit,
    onNavPassengerList: () -> Unit,
    onNavBrand: () -> Unit,
    onNavDocument: () -> Unit,
    onNavDataVehicle: () -> Unit,
    onNavDataInvolvedEdit: () -> Unit,
    onNavDataVehicleOwnInsert: (Int) -> Unit,
    onNavDataVehicleInvolvedInsert: (Int) -> Unit,
    onNavDataInvolvedInsert: (Int) -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            DetailContent(
                option = uiState.option,
                flowNote = uiState.flowNote,
                id = uiState.id,
                text = uiState.text,
                onTextChanged = viewModel::onTextChanged,
                set = viewModel::set,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavState = onNavState,
                onNavEquip = onNavEquip,
                onNavColab = onNavColab,
                onNavDataEquip = onNavDataEquip,
                onNavDataColab = onNavDataColab,
                onNavEquipSecList = onNavEquipSecList,
                onNavPassengerList = onNavPassengerList,
                onNavBrand = onNavBrand,
                onNavDocument = onNavDocument,
                onNavDataVehicle = onNavDataVehicle,
                onNavDataInvolvedEdit = onNavDataInvolvedEdit,
                onNavDataVehicleOwnInsert = onNavDataVehicleOwnInsert,
                onNavDataVehicleInvolvedInsert = onNavDataVehicleInvolvedInsert,
                onNavDataInvolvedInsert = onNavDataInvolvedInsert,
                modifier = Modifier.padding(innerPadding)
            )

        }
    }
}

@Composable
fun DetailContent(
    option: Option,
    flowNote: FlowNote,
    id: Int,
    text: String,
    onTextChanged: (String) -> Unit,
    set: () -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavState: () -> Unit,
    onNavEquip: () -> Unit,
    onNavColab: () -> Unit,
    onNavDataEquip: () -> Unit,
    onNavDataColab: () -> Unit,
    onNavEquipSecList: () -> Unit,
    onNavPassengerList: () -> Unit,
    onNavBrand: () -> Unit,
    onNavDocument: () -> Unit,
    onNavDataVehicle: () -> Unit,
    onNavDataInvolvedEdit: () -> Unit,
    onNavDataVehicleOwnInsert: (Int) -> Unit,
    onNavDataVehicleInvolvedInsert: (Int) -> Unit,
    onNavDataInvolvedInsert: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_detail
            )
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .testTag(TAG_DETAIL_VEHICLE_OWN_TEXT_FIELD),
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
                        Option.INSERT -> {
                            when(flowNote) {
                                FlowNote.EQUIP,
                                FlowNote.EQUIP_SEC -> onNavEquip()
                                FlowNote.VEHICLE -> onNavBrand()
                                FlowNote.COLAB,
                                FlowNote.PASSENGER_COLAB,
                                FlowNote.DRIVER,
                                FlowNote.PASSENGER_INVOLVED,
                                FlowNote.INVOLVED,
                                FlowNote.WITNESS -> onNavState()
                            }
                        }
                        Option.EDIT -> {
                            when(flowNote) {
                                FlowNote.EQUIP,
                                FlowNote.EQUIP_SEC -> onNavDataEquip()
                                FlowNote.VEHICLE -> onNavDataVehicle()
                                FlowNote.COLAB,
                                FlowNote.PASSENGER_COLAB -> onNavDataColab()
                                FlowNote.DRIVER,
                                FlowNote.PASSENGER_INVOLVED,
                                FlowNote.INVOLVED,
                                FlowNote.WITNESS -> onNavDataInvolvedEdit()
                            }
                        }
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
            when(option){
                Option.INSERT -> {
                    when(flowNote) {
                        FlowNote.EQUIP -> onNavColab()
                        FlowNote.EQUIP_SEC -> onNavEquipSecList()
                        FlowNote.VEHICLE -> onNavDocument()
                        FlowNote.COLAB -> onNavDataVehicleOwnInsert(id)
                        FlowNote.INVOLVED,
                        FlowNote.WITNESS -> onNavDataInvolvedInsert(id)
                        FlowNote.DRIVER -> onNavDataVehicleInvolvedInsert(id)
                        FlowNote.PASSENGER_COLAB,
                        FlowNote.PASSENGER_INVOLVED -> onNavPassengerList()
                    }
                }
                Option.EDIT -> {
                    when(flowNote) {
                        FlowNote.EQUIP,
                        FlowNote.EQUIP_SEC -> onNavDataEquip()
                        FlowNote.COLAB,
                        FlowNote.PASSENGER_COLAB -> onNavDataColab()
                        FlowNote.VEHICLE -> onNavDataVehicle()
                        FlowNote.DRIVER,
                        FlowNote.PASSENGER_INVOLVED,
                        FlowNote.INVOLVED,
                        FlowNote.WITNESS -> onNavDataInvolvedEdit()
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DetailContent(
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP,
                id = 1,
                text = "Text",
                onTextChanged = {},
                set = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavState = {},
                onNavEquip = {},
                onNavColab = {},
                onNavDataEquip = {},
                onNavDataColab = {},
                onNavEquipSecList = {},
                onNavPassengerList = {},
                onNavBrand = {},
                onNavDocument = {},
                onNavDataVehicle = {},
                onNavDataInvolvedEdit = {},
                onNavDataVehicleOwnInsert = {},
                onNavDataVehicleInvolvedInsert = {},
                onNavDataInvolvedInsert = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}