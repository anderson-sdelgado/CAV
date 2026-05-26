package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.data

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
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.utils.UiStatusState

const val TAG_EQUIP_EDIT_BUTTON = "tag_equip_edit_button"
const val TAG_EQUIP_SEC_EDIT_BUTTON = "tag_equip_sec_edit_button"
const val TAG_DRIVER_EDIT_BUTTON = "tag_driver_edit_button"
const val TAG_PASSENGERS_EDIT_BUTTON = "tag_passengers_accident_edit_button"

@Composable
fun DataVehicleOwnScreen(
    viewModel: DataVehicleOwnViewModel = hiltViewModel(),
    onNavEquip: () -> Unit,
    onNavColab: () -> Unit,
    onNavEquipSecList: () -> Unit,
    onNavPassengerList: () -> Unit,
    onNavMenu: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            DataVehicleOwnContent(
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
fun DataVehicleOwnContent(
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.text_equip
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = equip)
                    }
                    Button(
                        onClick = onNavEquip,
                        Modifier
                            .testTag(TAG_EQUIP_EDIT_BUTTON)
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.text_equip_sec
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = equipSec)
                    }
                    Button(
                        onClick = onNavEquipSecList,
                        Modifier
                            .testTag(TAG_EQUIP_SEC_EDIT_BUTTON)
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.text_driver_colab
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = driver)
                    }
                    Button(
                        onClick = onNavColab,
                        Modifier
                            .testTag(TAG_DRIVER_EDIT_BUTTON)
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
                            .testTag(TAG_PASSENGERS_EDIT_BUTTON)
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
fun DataVehicleOwnPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DataVehicleOwnContent(
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