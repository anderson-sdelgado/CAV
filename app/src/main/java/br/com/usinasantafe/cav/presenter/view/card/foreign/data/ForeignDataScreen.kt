package br.com.usinasantafe.cav.presenter.view.card.foreign.data

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
import br.com.usinasantafe.cav.lib.TypePeople
import br.com.usinasantafe.cav.presenter.theme.ButtonMaxWidth
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.ItemDefaultEditListScreenModel
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.utils.UiStatusState

const val TAG_DOCUMENT_DATA_INVOLVED_EDIT_BUTTON = "tag_document_data_involved_edit_button"
const val TAG_NAME_DATA_INVOLVED_EDIT_BUTTON = "tag_name_data_involved_edit_button"
const val TAG_STATE_DATA_INVOLVED_EDIT_BUTTON = "tag_state_data_involved_edit_button"
const val TAG_PHONE_DATA_INVOLVED_EDIT_BUTTON = "tag_phone_data_involved_edit_button"
const val TAG_ADDRESS_DATA_INVOLVED_EDIT_BUTTON = "tag_address_data_involved_edit_button"
const val TAG_DETAIL_DATA_INVOLVED_EDIT_BUTTON = "tag_detail_data_involved_edit_button"

@Composable
fun ForeignDataScreen(
    viewModel: ForeignDataViewModel = hiltViewModel(),
    onNavDocument: () -> Unit,
    onNavName: () -> Unit,
    onNavState: () -> Unit,
    onNavPhone: () -> Unit,
    onNavAddress: () -> Unit,
    onNavDetail: () -> Unit,
    onNavData: () -> Unit,
    onNavPassengerList: () -> Unit,
    onNavMenu: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            ForeignDataContent(
                typePeople = uiState.typePeople,
                document = uiState.document,
                name = uiState.name,
                state = uiState.state,
                phone = uiState.phone,
                address = uiState.address,
                detail = uiState.detail,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavDocument = onNavDocument,
                onNavName = onNavName,
                onNavState = onNavState,
                onNavPhone = onNavPhone,
                onNavAddress = onNavAddress,
                onNavDetail = onNavDetail,
                onNavData = onNavData,
                onNavPassengerList = onNavPassengerList,
                onNavMenu = onNavMenu,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ForeignDataContent(
    typePeople: TypePeople,
    document: String,
    name: String,
    state: String,
    phone: String,
    address: String,
    detail: String,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavDocument: () -> Unit,
    onNavName: () -> Unit,
    onNavState: () -> Unit,
    onNavPhone: () -> Unit,
    onNavAddress: () -> Unit,
    onNavDetail: () -> Unit,
    onNavData: () -> Unit,
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
                id = R.string.text_data_people
            )
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_document,
                    desc = document,
                    tag = TAG_DOCUMENT_DATA_INVOLVED_EDIT_BUTTON,
                    onClickEdit = onNavDocument
                )
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_name,
                    desc = name,
                    tag = TAG_NAME_DATA_INVOLVED_EDIT_BUTTON,
                    onClickEdit = onNavName
                )
            }
            if(typePeople != TypePeople.PEDESTRIAN){
                item {
                    ItemDefaultEditListScreenModel(
                        id = R.string.text_state,
                        desc = state,
                        tag = TAG_STATE_DATA_INVOLVED_EDIT_BUTTON,
                        onClickEdit = onNavState
                    )
                }
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_phone,
                    desc = phone,
                    tag = TAG_PHONE_DATA_INVOLVED_EDIT_BUTTON,
                    onClickEdit = onNavPhone
                )
            }
            if(typePeople != TypePeople.PEDESTRIAN) {
                item {
                    ItemDefaultEditListScreenModel(
                        id = R.string.text_address,
                        desc = address,
                        tag = TAG_ADDRESS_DATA_INVOLVED_EDIT_BUTTON,
                        onClickEdit = onNavAddress
                    )
                }
            }
            item {
                ItemDefaultEditListScreenModel(
                    id = R.string.text_detail,
                    desc = detail,
                    tag = TAG_DETAIL_DATA_INVOLVED_EDIT_BUTTON,
                    onClickEdit = onNavDetail
                )
            }
        }
        ButtonMaxWidth(R.string.text_pattern_return) {
            when(typePeople) {
                TypePeople.DRIVER -> onNavData()
                TypePeople.PASSENGER -> onNavPassengerList()
                TypePeople.PEDESTRIAN,
                TypePeople.INVOLVED -> onNavMenu()
            }
        }

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ForeignDataPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ForeignDataContent(
                typePeople = TypePeople.DRIVER,
                document = "123.456.789-00",
                name = "ANDERSON DA SILVA DELGADO",
                state = "ILESO",
                phone = "(16) 99999-1234",
                address = "RUA TESTE, 123 - JARDIM TESTE2",
                detail = "-",
                onCloseDialog = {},
                status = UiStatusState(),
                onNavDocument = {},
                onNavName = {},
                onNavPhone = {},
                onNavDetail = {},
                onNavState = {},
                onNavAddress = {},
                onNavData = {},
                onNavPassengerList = {},
                onNavMenu = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}