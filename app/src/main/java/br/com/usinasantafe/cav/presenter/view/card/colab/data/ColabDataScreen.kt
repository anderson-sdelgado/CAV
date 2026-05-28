package br.com.usinasantafe.cav.presenter.view.card.colab.data

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
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultEditListScreenModel
import br.com.usinasantafe.cav.utils.UiStatusState


const val TAG_COLAB_DATA_COLAB_EDIT_BUTTON = "tag_colab_data_colab_edit_button"
const val TAG_STATE_DATA_COLAB_EDIT_BUTTON = "tag_state_data_colab_edit_button"
const val TAG_DETAIL_DATA_COLAB_EDIT_BUTTON = "tag_detail_data_colab_edit_button"

@Composable
fun ColabDataScreen(
    viewModel: ColabDataViewModel = hiltViewModel(),
    onNavColab: () -> Unit,
    onNavState: () -> Unit,
    onNavDetail: () -> Unit,
    onNavDataVehicleOwn: () -> Unit,
    onNavPassengerList: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            ColabDataContent(
                type = uiState.type,
                colab = uiState.colab,
                state = uiState.state,
                detail = uiState.detail,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavColab = onNavColab,
                onNavState = onNavState,
                onNavDetail = onNavDetail,
                onNavDataVehicleOwn = onNavDataVehicleOwn,
                onNavPassengerList = onNavPassengerList,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ColabDataContent(
    type: Type,
    colab: String,
    state: String,
    detail: String,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavColab: () -> Unit,
    onNavState: () -> Unit,
    onNavDetail: () -> Unit,
    onNavDataVehicleOwn: () -> Unit,
    onNavPassengerList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_data_colab
            )
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_colab,
                    desc = colab,
                    tag = TAG_COLAB_DATA_COLAB_EDIT_BUTTON,
                    onClickEdit = onNavColab
                )
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_state,
                    desc = state,
                    tag = TAG_STATE_DATA_COLAB_EDIT_BUTTON,
                    onClickEdit = onNavState
                )
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_detail,
                    desc = detail,
                    tag = TAG_DETAIL_DATA_COLAB_EDIT_BUTTON,
                    onClickEdit = onNavDetail
                )
            }
        }
        ButtonMaxWidth(R.string.text_pattern_return) {
            when(type) {
                Type.MAIN -> onNavDataVehicleOwn()
                Type.SECONDARY -> onNavPassengerList()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ColabDataPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ColabDataContent(
                type = Type.MAIN,
                colab = "19759 - ANDERSON DA SILVA DELGADO",
                state = "FERIDO",
                detail = "PERNA MACHUCADA",
                onCloseDialog = {},
                status = UiStatusState(),
                onNavColab = {},
                onNavState = {},
                onNavDetail = {},
                onNavDataVehicleOwn = {},
                onNavPassengerList = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}