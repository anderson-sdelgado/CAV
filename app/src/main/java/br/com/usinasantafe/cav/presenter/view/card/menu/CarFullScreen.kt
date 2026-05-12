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
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign

@Composable
fun CarFullScreen() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CarFullContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun CarFullContent(
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
                CarSection(true)
            }
            item {
                CarSection(false)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(text = stringResource(id = R.string.text_pattern_return))
            }
            Button(
                onClick = {},
                modifier = Modifier.weight(1f),
            ) {
                TextButtonDesign(text = stringResource(id = R.string.text_pattern_next))
            }
        }
    }
}


@Composable
fun CarSection(type: Boolean) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray.copy(alpha = 0.2f))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val title = if(type)
            stringResource(id = R.string.text_equip_own)
        else
            stringResource(id = R.string.text_vehicle_third)
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        if(type) {
            CarItem(true)
            CarItem(true)
        } else {
            CarItem(false)
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
fun CarItem(type: Boolean) {
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
            Text("200 - CAMINHÃO")
            Text(text = stringResource(id = R.string.text_driver), fontWeight = FontWeight.Bold)
            Text("19759 - ANDERSON DA SILVA DELGADO")
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
fun CarFullPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CarFullContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}