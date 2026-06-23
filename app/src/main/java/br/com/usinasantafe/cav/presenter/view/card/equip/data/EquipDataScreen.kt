package br.com.usinasantafe.cav.presenter.view.card.equip.data

import android.util.Log
import androidx.activity.compose.BackHandler
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
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultEditListScreenModel
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.utils.UiStatusState

const val TAG_EQUIP_DATA_EQUIP_EDIT_BUTTON = "tag_equip_data_equip_edit_button"
const val TAG_DETAIL_DATA_EQUIP_EDIT_BUTTON = "tag_detail_data_equip_edit_button"

@Composable
fun EquipDataScreen(
    viewModel: EquipDataViewModel = hiltViewModel(),
    onNavEquip: () -> Unit,
    onNavDetail: () -> Unit,
    onNavDataVehicleOwn: () -> Unit,
    onNavEquipSecList: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            EquipDataContent(
                flowNote = uiState.flowNote,
                equip = uiState.equip,
                detail = uiState.detail,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavEquip = onNavEquip,
                onNavDetail= onNavDetail,
                onNavDataVehicleOwn = onNavDataVehicleOwn,
                onNavEquipSecList = onNavEquipSecList,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun EquipDataContent(
    flowNote: FlowNote,
    equip: String,
    detail: String,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavEquip: () -> Unit,
    onNavDetail: () -> Unit,
    onNavDataVehicleOwn: () -> Unit,
    onNavEquipSecList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_data_equip
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
                    tag = TAG_EQUIP_DATA_EQUIP_EDIT_BUTTON,
                    onClickEdit = onNavEquip
                )
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_detail,
                    desc = detail,
                    tag = TAG_DETAIL_DATA_EQUIP_EDIT_BUTTON,
                    onClickEdit = onNavDetail
                )
            }
        }
        ButtonMaxWidth(R.string.text_pattern_return) {
            Log.d("TestDebug", "FLowNote: $flowNote")
            when(flowNote) {
                FlowNote.EQUIP -> onNavDataVehicleOwn()
                else -> onNavEquipSecList()
            }
        }
        BackHandler {}

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun EquipDataPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            EquipDataContent(
                flowNote = FlowNote.EQUIP,
                equip = "2200 - CAMINHAO",
                detail = "-",
                onCloseDialog = {},
                status = UiStatusState(),
                onNavEquip = {},
                onNavDetail = {},
                onNavDataVehicleOwn = {},
                onNavEquipSecList = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}