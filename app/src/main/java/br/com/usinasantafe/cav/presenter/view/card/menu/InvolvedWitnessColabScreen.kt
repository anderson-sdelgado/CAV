package br.com.usinasantafe.cav.presenter.view.card.menu

import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
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
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import br.com.usinasantafe.cav.utils.UiStatusState

const val TAG_INVOLVED_COLAB_FULL_INSERT_BUTTON = "tag_involved_colab_full_insert_button"
const val TAG_INVOLVED_COLAB_FULL_EDIT_BUTTON = "tag_involved_colab_full_edit_button"
const val TAG_WITNESS_COLAB_FULL_INSERT_BUTTON = "tag_witness_colab_full_insert_button"
const val TAG_WITNESS_COLAB_FULL_EDIT_BUTTON = "tag_witness_colab_full_edit_button"

@Composable
fun InvolvedWitnessColabScreen(
    viewModel: InvolvedWitnessColabViewModel = hiltViewModel(),
    onNavVehicleFull: () -> Unit,
    onNavInvolvedWitnessExternal: () -> Unit,
    onNavColab: (FlowNote) -> Unit,
    onNavDataColab: (FlowNote, Int) -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            InvolvedWitnessColabContent(
                involvedList = uiState.involvedList,
                witnessList = uiState.witnessList,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                onNavVehicleFull = onNavVehicleFull,
                onNavInvolvedWitnessExternal = onNavInvolvedWitnessExternal,
                onNavDataInvolved = onNavDataColab,
                onNavColab = onNavColab,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun InvolvedWitnessColabContent(
    involvedList: List<ItemListScreenModel>,
    witnessList: List<ItemListScreenModel>,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavVehicleFull: () -> Unit,
    onNavInvolvedWitnessExternal: () -> Unit,
    onNavColab: (FlowNote) -> Unit,
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
                InvolvedWitnessColabSection(
                    typePeople = TypePeople.INVOLVED,
                    list = involvedList,
                    onNavInsert = onNavColab,
                    onNavDataInvolved = onNavDataInvolved,
                )
            }
            item{
                InvolvedWitnessColabSection(
                    typePeople = TypePeople.WITNESS,
                    list = witnessList,
                    onNavInsert = onNavColab,
                    onNavDataInvolved = onNavDataInvolved,
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
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_return),
                    padding = 10
                )
            }
            Button(
                onClick = onNavInvolvedWitnessExternal,
                modifier = Modifier.weight(1f),
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_next),
                    padding = 10
                )
            }
        }
        BackHandler {}

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }
}

@Composable
fun InvolvedWitnessColabSection(
    typePeople: TypePeople,
    list: List<ItemListScreenModel>,
    onNavInsert: (FlowNote) -> Unit,
    onNavDataInvolved: (FlowNote, Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray.copy(alpha = 0.2f))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val text = when(typePeople){
            TypePeople.INVOLVED -> stringResource(id = R.string.text_title_involved_colab)
            TypePeople.WITNESS -> stringResource(id = R.string.text_title_witness_colab)
        }
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        if(list.isEmpty()){
            Text("-")
        } else {
            list.forEach {
                PeopleColabItem(
                    type = typePeople,
                    model = it,
                    onClickEdit = {
                        when(typePeople){
                            TypePeople.INVOLVED -> onNavDataInvolved(FlowNote.INVOLVED_COLAB, it.id)
                            TypePeople.WITNESS -> onNavDataInvolved(FlowNote.WITNESS_COLAB, it.id)
                        }
                    },
                )
            }
        }

        val testTag = when(typePeople){
            TypePeople.INVOLVED -> TAG_INVOLVED_COLAB_FULL_INSERT_BUTTON
            TypePeople.WITNESS -> TAG_WITNESS_COLAB_FULL_INSERT_BUTTON
        }
        Button(
            onClick = {
                onNavInsert(
                    when(typePeople){
                        TypePeople.INVOLVED -> FlowNote.INVOLVED_COLAB
                        TypePeople.WITNESS -> FlowNote.WITNESS_COLAB
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag(testTag)
        ) {
            Text(
                text = stringResource(id = R.string.text_pattern_insert),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun PeopleColabItem(
    type: TypePeople,
    model: ItemListScreenModel,
    onClickEdit: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.White)
            .padding(8.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(text = stringResource(id = R.string.text_item_involved), fontWeight = FontWeight.Bold)
            Text(model.desc)
        }

        val testTagEdit = when(type){
            TypePeople.INVOLVED -> "$TAG_INVOLVED_COLAB_FULL_EDIT_BUTTON${model.id}"
            TypePeople.WITNESS -> "$TAG_WITNESS_COLAB_FULL_EDIT_BUTTON${model.id}"
        }
        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
        ) {
            IconButton(
                onClick = onClickEdit,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.LightGray
                ),
                modifier = Modifier.testTag(testTagEdit)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(id = R.string.text_pattern_edit)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InvolvedWitnessColabPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            InvolvedWitnessColabContent(
                involvedList = listOf(
                    ItemListScreenModel(
                        id = 1,
                        "19759 - ANDERSON DA SILVA DELGADO"
                    ),
                    ItemListScreenModel(
                        id = 2,
                        "18017 - JOÃO PAULO"
                    )
                ),
                witnessList = listOf(
                    ItemListScreenModel(
                        id = 1,
                        "19899 - MARIA PAULA"
                    )
                ),
                onCloseDialog = {},
                status = UiStatusState(),
                onNavVehicleFull = {},
                onNavInvolvedWitnessExternal = {},
                onNavColab = {},
                onNavDataInvolved = { _, _ -> },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}