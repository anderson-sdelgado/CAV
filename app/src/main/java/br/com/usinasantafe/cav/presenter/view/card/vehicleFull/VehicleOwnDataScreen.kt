package br.com.usinasantafe.cav.presenter.view.card.vehicleFull

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultEditListScreenModel
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.utils.UiStatusState

const val TAG_VEHICLE_OWN_EDIT_BUTTON = "tag_vehicle_own_edit_button"
const val TAG_VEHICLE_SEC_OWN_EDIT_BUTTON = "tag_vehicle_sec_own_edit_button"
const val TAG_DRIVER_OWN_EDIT_BUTTON = "tag_driver_own_edit_button"
const val TAG_PASSENGERS_OWN_EDIT_BUTTON = "tag_passengers_own_edit_button"

@Composable
fun VehicleOwnDataScreen(
    viewModel: VehicleOwnDataViewModel = hiltViewModel(),
    onNavEquip: () -> Unit,
    onNavColab: () -> Unit,
    onNavEquipSecList: () -> Unit,
    onNavPassengerList: () -> Unit,
    onNavMenu: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            VehicleOwnDataContent(
                equip = uiState.equip,
                equipSec = uiState.equipSec,
                driver = uiState.driver,
                passengers = uiState.passengers,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavColab = onNavColab,
                onNavEquip = onNavEquip,
                onNavPassengerList = onNavPassengerList,
                onNavEquipSecList = onNavEquipSecList,
                onNavMenu = onNavMenu,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun VehicleOwnDataContent(
    equip: String,
    equipSec: String,
    driver: String,
    passengers: String,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavEquip: () -> Unit,
    onNavColab: () -> Unit,
    onNavEquipSecList: () -> Unit,
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
                id = R.string.text_title_data_vehicle_own
            )
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_equip,
                    desc = equip,
                    tag = TAG_VEHICLE_OWN_EDIT_BUTTON,
                    onClickEdit = onNavEquip
                )
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_equip_sec,
                    desc = equipSec,
                    tag = TAG_VEHICLE_SEC_OWN_EDIT_BUTTON,
                    onClickEdit = onNavEquipSecList
                )
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_driver,
                    desc = driver,
                    tag = TAG_DRIVER_OWN_EDIT_BUTTON,
                    onClickEdit = onNavColab
                )
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.text_title_passenger
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = passengers)
                    }
                    Button(
                        onClick = onNavPassengerList,
                        Modifier
                            .testTag(TAG_PASSENGERS_OWN_EDIT_BUTTON)
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
        ButtonMaxWidth(R.string.text_pattern_return) { onNavMenu() }


        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun VehicleOwnDataPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            VehicleOwnDataContent(
                equip = "2200 - CAMINHAO",
                equipSec = "200 - CARRETA\n201 - CARRETA",
                driver = "19759 - ANDERSON DA SILVA DELGADO",
                passengers = "18019 - RONALDO GOMES\n123457 - JOSE PAULO",
                onCloseDialog = {},
                status = UiStatusState(),
                onNavEquip = {},
                onNavEquipSecList = {},
                onNavColab = {},
                onNavPassengerList = {},
                onNavMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}