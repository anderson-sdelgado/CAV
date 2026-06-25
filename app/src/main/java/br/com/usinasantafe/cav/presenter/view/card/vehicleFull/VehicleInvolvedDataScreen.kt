package br.com.usinasantafe.cav.presenter.view.card.vehicleFull

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import br.com.usinasantafe.cav.presenter.theme.AlertDialogCheckDesign
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultEditListScreenModel
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.utils.UiStatusState

const val TAG_VEHICLE_FOREIGN_EDIT_BUTTON = "tag_vehicle_foreign_edit_button"
const val TAG_DRIVER_FOREIGN_EDIT_BUTTON = "tag_driver_foreign_edit_button"
const val TAG_PASSENGERS_FOREIGN_EDIT_BUTTON = "tag_passengers_foreign_edit_button"

@Composable
fun VehicleInvolvedDataScreen(
    viewModel: VehicleInvolvedDataViewModel = hiltViewModel(),
    onNavDataVehicle: () -> Unit,
    onNavDataInvolved: () -> Unit,
    onNavPassengerList: () -> Unit,
    onNavMenu: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            VehicleForeignDataContent(
                vehicle = uiState.vehicle,
                driver = uiState.driver,
                passengers = uiState.passengers,
                flagDialogCheck = uiState.flagDialogCheck,
                onDialogCheck = viewModel::onDialogCheck,
                delete = viewModel::delete,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavDataVehicle = onNavDataVehicle,
                onNavDataForeign = onNavDataInvolved,
                onNavPassengerList = onNavPassengerList,
                onNavMenu = onNavMenu,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun VehicleForeignDataContent(
    vehicle: String,
    driver: String,
    passengers: String,
    flagDialogCheck: Boolean,
    onDialogCheck: (Boolean) -> Unit,
    delete: () -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavDataVehicle: () -> Unit,
    onNavDataForeign: () -> Unit,
    onNavPassengerList: () -> Unit,
    onNavMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_data_vehicle_foreign
            )
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_vehicle,
                    desc = vehicle,
                    tag = TAG_VEHICLE_FOREIGN_EDIT_BUTTON,
                    onClickEdit = onNavDataVehicle
                )
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_driver,
                    desc = driver,
                    tag = TAG_DRIVER_FOREIGN_EDIT_BUTTON,
                    onClickEdit = onNavDataForeign
                )
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_passenger,
                    desc = passengers,
                    tag = TAG_PASSENGERS_FOREIGN_EDIT_BUTTON,
                    onClickEdit = onNavPassengerList
                )
            }
        }
        ButtonMaxWidth(
            id = R.string.text_pattern_delete,
            flagDelete = true
        ) {
            onDialogCheck(true)
        }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        ButtonMaxWidth(R.string.text_pattern_return) { onNavMenu() }

        if(flagDialogCheck){
            AlertDialogCheckDesign(
                text = stringResource(
                    id = R.string.text_check_delete_vehicle,
                    vehicle
                ),
                onClickDismiss = { onDialogCheck(false) },
                onClickYes = delete
            )
        }

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

        LaunchedEffect(status.flagAccess) {
            if(status.flagAccess) {
                onNavMenu()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun VehicleForeignDataPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            VehicleForeignDataContent(
                vehicle = "ABC1234 - GOL",
                driver = "123.468.789-00 - ANDERSON DA SILVA DELGADO",
                passengers = "123.456.789-00 - JOÃO HENRIQUE DA SILVA\n123.456.789-00 - PAULO GUSTAVO DA SILVA",
                flagDialogCheck = false,
                onDialogCheck = {},
                delete = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavDataVehicle = {},
                onNavDataForeign = {},
                onNavPassengerList = {},
                onNavMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleForeignDataPagePreviewWithCheck() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            VehicleForeignDataContent(
                vehicle = "ABC1234 - GOL",
                driver = "123.468.789-00 - ANDERSON DA SILVA DELGADO",
                passengers = "123.456.789-00 - JOÃO HENRIQUE DA SILVA\n123.456.789-00 - PAULO GUSTAVO DA SILVA",
                flagDialogCheck = true,
                onDialogCheck = {},
                delete = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavDataVehicle = {},
                onNavDataForeign = {},
                onNavPassengerList = {},
                onNavMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}