package br.com.usinasantafe.cav.presenter.view.card.vehicle.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultEditListScreenModel
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.utils.UiStatusState

const val TAG_PLATE_DATA_VEHICLE_EDIT_BUTTON = "tag_plate_data_vehicle_edit_button"
const val TAG_BRAND_DATA_VEHICLE_EDIT_BUTTON = "tag_brand_data_vehicle_edit_button"
const val TAG_DETAIL_DATA_VEHICLE_EDIT_BUTTON = "tag_detail_data_vehicle_edit_button"

@Composable
fun VehicleDataScreen(
    viewModel: VehicleDataViewModel = hiltViewModel(),
    onNavPlate: () -> Unit,
    onNavBrand: () -> Unit,
    onNavDetail: () -> Unit,
    onNavDataVehicleInvolved: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            VehicleDataContent(
                plate = uiState.plate,
                brand = uiState.brand,
                detail = uiState.detail,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavPlate = onNavPlate,
                onNavBrand = onNavBrand,
                onNavDetail = onNavDetail,
                onNavData = onNavDataVehicleInvolved,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun VehicleDataContent(
    plate: String,
    brand: String,
    detail: String,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavPlate: () -> Unit,
    onNavBrand: () -> Unit,
    onNavDetail: () -> Unit,
    onNavData: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_data_vehicle
            )
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_plate,
                    desc = plate,
                    tag = TAG_PLATE_DATA_VEHICLE_EDIT_BUTTON,
                    onClickEdit = onNavPlate
                )
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_brand_desc,
                    desc = brand,
                    tag = TAG_BRAND_DATA_VEHICLE_EDIT_BUTTON,
                    onClickEdit = onNavBrand
                )
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_detail,
                    desc = detail,
                    tag = TAG_DETAIL_DATA_VEHICLE_EDIT_BUTTON,
                    onClickEdit = onNavDetail
                )
            }
        }
        ButtonMaxWidth(R.string.text_pattern_return) { onNavData() }

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun VehicleDataPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            VehicleDataContent(
                plate = "ABC1234",
                brand = "GOL",
                detail = "-",
                onCloseDialog = {},
                status = UiStatusState(),
                onNavPlate = {},
                onNavBrand = {},
                onNavDetail = {},
                onNavData = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}