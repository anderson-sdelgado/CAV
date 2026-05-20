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
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign

@Composable
fun VehicleFullScreen(
    viewModel: VehicleFullViewModel = hiltViewModel(),
    onNavLocalSupport: () -> Unit,
    onNavInvolvedWitness: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            VehicleFullContent(
                vehicleOwnList = uiState.vehicleOwnList,
                vehicleForeignList = uiState.vehicleForeignList,
                onNavLocalSupport = onNavLocalSupport,
                onNavInvolvedWitness = onNavInvolvedWitness,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun VehicleFullContent(
    vehicleOwnList: List<VehicleScreenModel>,
    vehicleForeignList: List<VehicleScreenModel>,
    onNavLocalSupport: () -> Unit,
    onNavInvolvedWitness: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TitleDesign(
            text = stringResource(
                id = R.string.text_title_card
            )
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                VehicleOwnSection( vehicleOwnList)
            }
            item {
                VehicleForeignSection(vehicleForeignList)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = onNavLocalSupport,
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(
                    text = stringResource(
                        id = R.string.text_pattern_return
                    ),
                    font = 18
                )
            }
            Button(
                onClick = onNavInvolvedWitness,
                modifier = Modifier.weight(1f),
            ) {
                TextButtonDesign(
                    text = stringResource(
                        id = R.string.text_pattern_next
                    ),
                    font = 18
                )
            }
        }
    }
}


@Composable
fun VehicleOwnSection(
    vehicleList: List<VehicleScreenModel>
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
            text = stringResource(id = R.string.text_equip_own),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        if(vehicleList.isEmpty()){
            Text("-")
        } else {
            vehicleList.forEach {
                CarItem(true, it)
            }
        }

        Button(
            onClick = {},
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
fun VehicleForeignSection(
    vehicleList: List<VehicleScreenModel>
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
            text = stringResource(id = R.string.text_vehicle_third),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        if(vehicleList.isEmpty()){
            Text("-")
        } else {
            vehicleList.forEach {
                CarItem(false, it)
            }
        }

        Button(
            onClick = {},
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
fun CarItem(
    type: Boolean,
    model: VehicleScreenModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.White)
            .padding(8.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val title = if(type)
            stringResource(id = R.string.text_equip)
        else
            stringResource(id = R.string.text_vehicle)
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(model.vehicle)
            Text(text = stringResource(id = R.string.text_driver), fontWeight = FontWeight.Bold)
            Text(model.driver)
        }

        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
        ) {
            Button(onClick = {}, modifier = Modifier.fillMaxWidth().padding(1.dp)) {
                Text(text = stringResource(id = R.string.text_pattern_edit))
            }
            Button(onClick = {}, modifier = Modifier.fillMaxWidth().padding(1.dp)) {
                Text(text = stringResource(id = R.string.text_pattern_delete))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleFullPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            VehicleFullContent(
                vehicleOwnList = emptyList(),
                vehicleForeignList = emptyList(),
                onNavLocalSupport = {},
                onNavInvolvedWitness = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleFullPagePreviewWithData() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            VehicleFullContent(
                vehicleOwnList = listOf(
                    VehicleScreenModel(
                        id = 1,
                        vehicle = "2200 - CAMINHÃO",
                        driver = "19759 - ANDERSON DA SILVA DELGADO"
                    )
                ),
                vehicleForeignList = listOf(
                    VehicleScreenModel(
                        id = 1,
                        vehicle = "ABC1234 - GOL",
                        driver = "123.456.789-00 - ANDERSON DA SILVA DELGADO"
                    )
                ),
                onNavLocalSupport = {},
                onNavInvolvedWitness = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}