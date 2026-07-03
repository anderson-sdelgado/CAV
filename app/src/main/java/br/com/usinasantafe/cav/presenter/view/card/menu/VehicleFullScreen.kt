package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.lib.TypeVehicle
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import br.com.usinasantafe.cav.presenter.theme.AlertDialogCheckDesign
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.utils.UiStatusState


const val TAG_VEHICLE_OWN_FULL_INSERT_BUTTON = "tag_vehicle_own_full_insert_button"
const val TAG_VEHICLE_OWN_FULL_EDIT_BUTTON = "tag_vehicle_own_full_edit_button"
const val TAG_VEHICLE_INVOLVED_FULL_INSERT_BUTTON = "tag_vehicle_involved_full_insert_button"
const val TAG_VEHICLE_INVOLVED_FULL_EDIT_BUTTON = "tag_vehicle_involved_full_edit_button"

@Composable
fun VehicleFullScreen(
    viewModel: VehicleFullViewModel = hiltViewModel(),
    onNavLocalSupport: () -> Unit,
    onNavInvolvedWitness: () -> Unit,
    onNavEquip: () -> Unit,
    onNavDataVehicleOwn: (Int) -> Unit,
    onNavPlate: () -> Unit,
    onNavDataVehicleInvolved: (Int) -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            VehicleFullContent(
                vehicleOwnList = uiState.vehicleOwnList,
                vehicleInvolvedList = uiState.vehicleInvolvedList,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavLocalSupport = onNavLocalSupport,
                onNavInvolvedWitness = onNavInvolvedWitness,
                onNavEquip = onNavEquip,
                onNavDataVehicleOwn = onNavDataVehicleOwn,
                onNavPlate = onNavPlate,
                onNavDataVehicleInvolved = onNavDataVehicleInvolved,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun VehicleFullContent(
    vehicleOwnList: List<VehicleScreenModel>,
    vehicleInvolvedList: List<VehicleScreenModel>,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavLocalSupport: () -> Unit,
    onNavInvolvedWitness: () -> Unit,
    onNavEquip: () -> Unit,
    onNavDataVehicleOwn: (Int) -> Unit,
    onNavPlate: () -> Unit,
    onNavDataVehicleInvolved: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_card
            )
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                VehicleOwnSection(
                    vehicleList = vehicleOwnList,
                    onNavEquip = onNavEquip,
                    onNavDataVehicleOwn = onNavDataVehicleOwn,
                )
            }
            item {
                VehicleInvolvedSection(
                    vehicleList = vehicleInvolvedList,
                    onNavPlate = onNavPlate,
                    onNavDataVehicleInvolved = onNavDataVehicleInvolved
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = onNavLocalSupport,
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_return),
                    padding = 10
                )
            }
            Button(
                onClick = onNavInvolvedWitness,
                modifier = Modifier.weight(1f),
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_next),
                    padding = 10
                )
            }
        }
        BackHandler {}

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }
}


@Composable
fun VehicleOwnSection(
    vehicleList: List<VehicleScreenModel>,
    onNavEquip: () -> Unit,
    onNavDataVehicleOwn: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray.copy(alpha = 0.2f))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = stringResource(id = R.string.text_equip_own),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        if(vehicleList.isEmpty()){
            Text("-")
        } else {
            vehicleList.forEach {
                CarItem(
                    type = TypeVehicle.OWN,
                    model = it,
                    onClickEdit = {
                        onNavDataVehicleOwn(it.id)
                    },
                )
            }
        }
        Button(
            onClick = onNavEquip,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(TAG_VEHICLE_OWN_FULL_INSERT_BUTTON)
        ) {
            Text(
                text = stringResource(id = R.string.text_pattern_insert),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun VehicleInvolvedSection(
    vehicleList: List<VehicleScreenModel>,
    onNavPlate: () -> Unit,
    onNavDataVehicleInvolved: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray.copy(alpha = 0.2f))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = stringResource(id = R.string.text_vehicle_third),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        if(vehicleList.isEmpty()){
            Text("-")
        } else {
            vehicleList.forEach {
                CarItem(
                    type = TypeVehicle.INVOLVED,
                    model = it,
                    onClickEdit = { onNavDataVehicleInvolved(it.id) },
                )
            }
        }

        Button(
            onClick = onNavPlate,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(TAG_VEHICLE_INVOLVED_FULL_INSERT_BUTTON)
        ) {
            Text(
                text = stringResource(id = R.string.text_pattern_insert),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun CarItem(
    type: TypeVehicle,
    model: VehicleScreenModel,
    onClickEdit: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.White)
            .padding(8.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val title = when(type){
            TypeVehicle.OWN -> stringResource(id = R.string.text_equip)
            TypeVehicle.INVOLVED -> stringResource(id = R.string.text_vehicle)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(model.vehicle)
            Text(text = stringResource(id = R.string.text_driver), fontWeight = FontWeight.Bold)
            Text(model.driver)
        }

        val testTagEdit = when(type){
            TypeVehicle.OWN -> "$TAG_VEHICLE_OWN_FULL_EDIT_BUTTON${model.id}"
            TypeVehicle.INVOLVED -> "$TAG_VEHICLE_INVOLVED_FULL_EDIT_BUTTON${model.id}"
        }

        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
        ) {
            IconButton(
                onClick = onClickEdit,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.LightGray
                ),
                modifier = Modifier.testTag(testTagEdit)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(id = R.string.text_pattern_edit)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleFullPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            VehicleFullContent(
                vehicleOwnList = emptyList(),
                vehicleInvolvedList = emptyList(),
                onCloseDialog = {},
                status = UiStatusState(),
                onNavLocalSupport = {},
                onNavInvolvedWitness = {},
                onNavEquip = {},
                onNavDataVehicleOwn = {},
                onNavPlate = {},
                onNavDataVehicleInvolved = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleFullPagePreviewWithData() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            VehicleFullContent(
                vehicleOwnList = listOf(
                    VehicleScreenModel(
                        id = 1,
                        vehicle = "2200 - CAMINHÃO",
                        driver = "19759 - ANDERSON DA SILVA DELGADO"
                    )
                ),
                vehicleInvolvedList = listOf(
                    VehicleScreenModel(
                        id = 1,
                        vehicle = "ABC1234 - GOL",
                        driver = "123.456.789-00 - ANDERSON DA SILVA DELGADO"
                    )
                ),
                onCloseDialog = {},
                status = UiStatusState(),
                onNavLocalSupport = {},
                onNavInvolvedWitness = {},
                onNavEquip = {},
                onNavDataVehicleOwn = {},
                onNavPlate = {},
                onNavDataVehicleInvolved = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}