package br.com.usinasantafe.cav.presenter.view.card.colab.data

import androidx.activity.compose.BackHandler
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
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.presenter.theme.AlertDialogCheckDesign
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultEditListScreenModel
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.utils.UiStatusState


const val TAG_COLAB_DATA_COLAB_EDIT_BUTTON = "tag_colab_data_colab_edit_button"
const val TAG_STATE_DATA_COLAB_EDIT_BUTTON = "tag_state_data_colab_edit_button"
const val TAG_DETAIL_DATA_COLAB_EDIT_BUTTON = "tag_detail_data_colab_edit_button"
const val TAG_BREATHALYZER_DATA_COLAB_EDIT_BUTTON = "tag_breathalyzer_data_colab_edit_button"

@Composable
fun ColabDataScreen(
    viewModel: ColabDataViewModel = hiltViewModel(),
    onNavColab: () -> Unit,
    onNavState: () -> Unit,
    onNavDetail: () -> Unit,
    onNavCheckBreathalyzer: () -> Unit,
    onNavDataVehicleOwn: () -> Unit,
    onNavPassengerList: () -> Unit,
    onNavMenu: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            ColabDataContent(
                flowNote = uiState.flowNote,
                colab = uiState.colab,
                state = uiState.state,
                detail = uiState.detail,
                breathalyzer = uiState.breathalyzer,
                flagDialogCheck = uiState.flagDialogCheck,
                onDialogCheck = viewModel::onDialogCheck,
                delete = viewModel::delete,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavColab = onNavColab,
                onNavState = onNavState,
                onNavDetail = onNavDetail,
                onNavCheckBreathalyzer = onNavCheckBreathalyzer,
                onNavDataVehicleOwn = onNavDataVehicleOwn,
                onNavPassengerList = onNavPassengerList,
                onNavMenu = onNavMenu,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ColabDataContent(
    flowNote: FlowNote,
    colab: String,
    state: State,
    detail: String,
    breathalyzer: String,
    flagDialogCheck: Boolean,
    onDialogCheck: (Boolean) -> Unit,
    delete: () -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavColab: () -> Unit,
    onNavState: () -> Unit,
    onNavDetail: () -> Unit,
    onNavCheckBreathalyzer: () -> Unit,
    onNavDataVehicleOwn: () -> Unit,
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
                id = when(flowNote) {
                    FlowNote.COLAB -> R.string.text_data_colab
                    FlowNote.INVOLVED_COLAB -> R.string.text_data_involved
                    FlowNote.WITNESS_COLAB -> R.string.text_data_witness
                    else -> R.string.text_data_passenger
                }
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
            if(flowNote != FlowNote.WITNESS_COLAB) {
                item {
                    ItemDefaultEditListScreenModel(
                        id = R.string.text_state,
                        desc = when (state) {
                            State.UNHARMED -> stringResource(
                                id = R.string.text_item_unharmed
                            )

                            State.INJURED -> stringResource(
                                id = R.string.text_item_injured
                            )

                            State.DEAD -> stringResource(
                                id = R.string.text_item_dead
                            )
                        },
                        tag = TAG_STATE_DATA_COLAB_EDIT_BUTTON,
                        onClickEdit = onNavState
                    )
                }
            }
            if(flowNote == FlowNote.COLAB) {
                item {
                    ItemDefaultEditListScreenModel(
                        id = R.string.text_breathalyzer,
                        desc = breathalyzer,
                        tag = TAG_BREATHALYZER_DATA_COLAB_EDIT_BUTTON,
                        onClickEdit = onNavCheckBreathalyzer
                    )
                }
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
        if(flowNote != FlowNote.COLAB){
            ButtonMaxWidth(
                id = R.string.text_pattern_delete,
                flagDelete = true
            ) {
                onDialogCheck(true)
            }
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
        }
        ButtonMaxWidth(R.string.text_pattern_return) {
            when(flowNote) {
                FlowNote.COLAB -> onNavDataVehicleOwn()
                FlowNote.PASSENGER_COLAB -> onNavPassengerList()
                else -> onNavMenu()
            }
        }
        BackHandler {}

        if(flagDialogCheck){
            AlertDialogCheckDesign(
                text = stringResource(
                    id = R.string.text_check_delete_passenger,
                    colab
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
                when(flowNote) {
                    FlowNote.COLAB -> onNavDataVehicleOwn()
                    FlowNote.PASSENGER_COLAB -> onNavPassengerList()
                    else -> onNavMenu()
                }
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
                flowNote = FlowNote.EQUIP,
                colab = "19759 - ANDERSON DA SILVA DELGADO",
                state = State.INJURED,
                detail = "PERNA MACHUCADA",
                breathalyzer = "",
                flagDialogCheck = false,
                onDialogCheck = {},
                delete = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavColab = {},
                onNavState = {},
                onNavDetail = {},
                onNavDataVehicleOwn = {},
                onNavPassengerList = {},
                onNavCheckBreathalyzer = {},
                onNavMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColabDataPagePreviewWithCheck() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ColabDataContent(
                flowNote = FlowNote.COLAB,
                colab = "19759 - ANDERSON DA SILVA DELGADO",
                state = State.INJURED,
                detail = "PERNA MACHUCADA",
                breathalyzer = "BAFÔMETRO: NÃO REALIZADO",
                flagDialogCheck = true,
                onDialogCheck = {},
                delete = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavColab = {},
                onNavState = {},
                onNavDetail = {},
                onNavDataVehicleOwn = {},
                onNavPassengerList = {},
                onNavCheckBreathalyzer = {},
                onNavMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}