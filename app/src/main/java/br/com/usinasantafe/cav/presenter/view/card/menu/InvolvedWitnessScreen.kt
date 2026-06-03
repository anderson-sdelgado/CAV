package br.com.usinasantafe.cav.presenter.view.card.menu

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
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.TypePeople
import br.com.usinasantafe.cav.lib.TypeVehicle
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import br.com.usinasantafe.cav.presenter.theme.AlertDialogCheckDesign
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.utils.UiStatusState

@Composable
fun InvolvedWitnessScreen(
    viewModel: InvolvedWitnessViewModel = hiltViewModel(),
    onNavVehicleFull: () -> Unit,
    onNavObs: () -> Unit,
    onNavDocument: (FlowNote) -> Unit,
    onNavDataInvolved: (FlowNote, Int) -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            InvolvedWitnessContent(
                typePeople = uiState.typePeople,
                involvedList = uiState.involvedList,
                witnessList = uiState.witnessList,
                idSelection = uiState.idSelection,
                onSelectionDelete = viewModel::onSelectionDelete,
                flagDialogCheck = uiState.flagDialogCheck,
                onDialogCheck = viewModel::onDialogCheck,
                delete = viewModel::delete,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavVehicleFull = onNavVehicleFull,
                onNavObs = onNavObs,
                onNavDataInvolved = onNavDataInvolved,
                onNavDocument = onNavDocument,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun InvolvedWitnessContent(
    typePeople: TypePeople,
    involvedList: List<ItemListScreenModel>,
    witnessList: List<ItemListScreenModel>,
    idSelection: Int,
    onSelectionDelete: (Int, TypePeople) -> Unit,
    flagDialogCheck: Boolean,
    onDialogCheck: (Boolean) -> Unit,
    delete: () -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavVehicleFull: () -> Unit,
    onNavObs: () -> Unit,
    onNavDocument: (FlowNote) -> Unit,
    onNavDataInvolved: (FlowNote, Int) -> Unit,
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
                InvolvedSection(
                    involvedList = involvedList,
                    onSelectionDelete = onSelectionDelete,
                    onNavDataInvolved = onNavDataInvolved,
                    onNavDocument = onNavDocument,
                )
            }
            item{
                WitnessSection(
                    witnessList = witnessList,
                    onSelectionDelete = onSelectionDelete,
                    onNavDataInvolved = onNavDataInvolved,
                    onNavDocument = onNavDocument,
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
                onClick = onNavVehicleFull,
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(text = stringResource(id = R.string.text_pattern_return))
            }
            Button(
                onClick = onNavObs,
                modifier = Modifier.weight(1f),
            ) {
                TextButtonDesign(text = stringResource(id = R.string.text_pattern_next))
            }
        }

        if(flagDialogCheck){
            var list: List<ItemListScreenModel>
            var id: Int
            when(typePeople){
                TypePeople.INVOLVED -> {
                    list = involvedList
                    id = R.string.text_check_delete_involved
                }

                TypePeople.WITNESS -> {
                    list = witnessList
                    id = R.string.text_check_delete_witness
                }
            }
            val desc = list.first{ it.id == idSelection }.desc
            AlertDialogCheckDesign(
                text = stringResource(
                    id = id,
                    desc
                ),
                onClickDismiss = { onDialogCheck(false) },
                onClickYes = delete
            )
        }

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }
}

@Composable
fun InvolvedSection(
    involvedList: List<ItemListScreenModel>,
    onSelectionDelete: (Int, TypePeople) -> Unit,
    onNavDocument: (FlowNote) -> Unit,
    onNavDataInvolved: (FlowNote, Int) -> Unit,
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
            text = stringResource(id = R.string.text_title_involved),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        if(involvedList.isEmpty()){
            Text("-")
        } else {
            involvedList.forEach {
                PeopleItem(
                    type = TypePeople.INVOLVED,
                    model = it,
                    onClickEdit = {
                        onNavDataInvolved(FlowNote.INVOLVED, it.id)
                    },
                    onClickDel = {
                        onSelectionDelete(it.id, TypePeople.INVOLVED)
                    }
                )
            }
        }

        Button(
            onClick = { onNavDocument(FlowNote.INVOLVED) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.text_pattern_insert),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun WitnessSection(
    witnessList: List<ItemListScreenModel>,
    onSelectionDelete: (Int, TypePeople) -> Unit,
    onNavDocument: (FlowNote) -> Unit,
    onNavDataInvolved: (FlowNote, Int) -> Unit,
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
            text = stringResource(id = R.string.text_title_witness),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        if(witnessList.isEmpty()){
            Text("-")
        } else {
            witnessList.forEach {
                PeopleItem(
                    type = TypePeople.WITNESS,
                    model = it,
                    onClickEdit = {
                        onNavDataInvolved(FlowNote.WITNESS, it.id)
                    },
                    onClickDel = {
                        onSelectionDelete(it.id, TypePeople.WITNESS)
                    }
                )
            }
        }

        Button(
            onClick = { onNavDocument(FlowNote.INVOLVED) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.text_pattern_insert),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun PeopleItem(
    type: TypePeople,
    model: ItemListScreenModel,
    onClickEdit: () -> Unit,
    onClickDel: () -> Unit,
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
            TypePeople.INVOLVED -> stringResource(id = R.string.text_title_involved)
            TypePeople.WITNESS -> stringResource(id = R.string.text_involved)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(model.desc)
        }

        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
        ) {
            Button(onClick = onClickEdit, modifier = Modifier.fillMaxWidth().padding(1.dp)) {
                Text(text = stringResource(id = R.string.text_pattern_edit))
            }
            Button(onClick = onClickDel, modifier = Modifier.fillMaxWidth().padding(1.dp)) {
                Text(text = stringResource(id = R.string.text_pattern_delete))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InvolvedWitnessPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            InvolvedWitnessContent(
                typePeople = TypePeople.WITNESS,
                involvedList = listOf(
                    ItemListScreenModel(
                        id = 1,
                        "123.456.789-88 - ANDERSON DA SILVA DELGADO"
                    ),
                    ItemListScreenModel(
                        id = 2,
                        "123.456.789-88 - JOÃO PAULO"
                    )
                ),
                witnessList = listOf(
                    ItemListScreenModel(
                        id = 1,
                        "123.456.789-88 - MARIA PAULA"
                    )
                ),
                idSelection = 0,
                onSelectionDelete = { _, _ -> },
                flagDialogCheck = true,
                onDialogCheck = {},
                delete = {},
                onCloseDialog = {},
                status = UiStatusState(),
                onNavVehicleFull = {},
                onNavObs = {},
                onNavDocument = {},
                onNavDataInvolved = { _, _ -> },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}