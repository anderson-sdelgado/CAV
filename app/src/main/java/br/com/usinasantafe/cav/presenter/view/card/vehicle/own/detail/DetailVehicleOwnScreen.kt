package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.detail

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
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeDetail
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.presenter.view.card.local.TAG_LOCAL_TEXT_FIELD

@Composable
fun DetailVehicleOwnScreen(
    viewModel: DetailVehicleOwnViewModel = hiltViewModel(),
    onNavState: () -> Unit,
    onNavEquip: () -> Unit,
    onNavPassengerList: () -> Unit,
    onNavEquipSecList: () -> Unit,
    onNavData: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            DetailVehicleOwnContent(
                option = uiState.option,
                typeDetail = uiState.typeDetail,
                text = uiState.text,
                onTextChanged = viewModel::onTextChanged,
                set = viewModel::set,
                setCloseDialog = viewModel::setCloseDialog,
                flagAccess = uiState.flagAccess,
                flagDialog = uiState.flagDialog,
                failure = uiState.failure,
                errors = uiState.errors,
                onNavState = onNavState,
                onNavEquip = onNavEquip,
                onNavPassengerList = onNavPassengerList,
                onNavEquipSecList = onNavEquipSecList,
                onNavData = onNavData,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun DetailVehicleOwnContent(
    option: Option,
    typeDetail: TypeDetail,
    text: String,
    onTextChanged: (String) -> Unit,
    set: () -> Unit,
    setCloseDialog: () -> Unit,
    flagAccess: Boolean,
    flagDialog: Boolean,
    failure: String,
    errors: Errors,
    onNavState: () -> Unit,
    onNavEquip: () -> Unit,
    onNavPassengerList: () -> Unit,
    onNavEquipSecList: () -> Unit,
    onNavData: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_title_detail
            )
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .testTag(TAG_LOCAL_TEXT_FIELD),
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
                    when(typeDetail){
                        TypeDetail.EQUIP_VEHICLE -> onNavEquip()
                        TypeDetail.EQUIP_VEHICLE_SEC -> onNavEquipSecList()
                        TypeDetail.DRIVER -> onNavState()
                        TypeDetail.PASSENGER -> onNavPassengerList()
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
                TextButtonDesign(text = stringResource(id = R.string.text_pattern_ok))
            }
        }

        if(flagDialog) {
            MsgErrors(errors, setCloseDialog, failure)
        }

    }


    LaunchedEffect(flagAccess) {
        if (flagAccess) {
            when(typeDetail){
                TypeDetail.EQUIP_VEHICLE -> {
                    when(option){
                        Option.INSERT -> onNavEquipSecList()
                        Option.EDIT -> onNavData()
                    }
                }
                TypeDetail.EQUIP_VEHICLE_SEC -> onNavEquipSecList()
                TypeDetail.DRIVER -> {
                    when(option){
                        Option.INSERT -> onNavPassengerList()
                        Option.EDIT -> onNavData()
                    }
                }
                TypeDetail.PASSENGER -> onNavPassengerList()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailVehicleOwnPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DetailVehicleOwnContent(
                option = Option.INSERT,
                typeDetail = TypeDetail.EQUIP_VEHICLE,
                text = "Text",
                onTextChanged = {},
                set = {},
                setCloseDialog = {},
                flagAccess = false,
                flagDialog = false,
                failure = "",
                errors = Errors.FIELD_EMPTY,
                onNavState = {},
                onNavEquip = {},
                onNavPassengerList = {},
                onNavEquipSecList = {},
                onNavData = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}