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
fun InvolvedWitnessScreen() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            InvolvedWitnessContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun InvolvedWitnessContent(
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
                InvolvedSection()
            }
            item{
                WitnessSection()
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
fun InvolvedSection() {
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

        InvolvedItem()
        InvolvedItem()

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
fun InvolvedItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.White)
            .padding(8.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = stringResource(id = R.string.text_involved), fontWeight = FontWeight.Bold)
            Text("123.456.789-88 - ANDERSON DA SILVA DELGADO")
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

@Composable
fun WitnessSection() {
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

        WitnessItem()

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
fun WitnessItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.White)
            .padding(8.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = stringResource(id = R.string.text_witness), fontWeight = FontWeight.Bold)
            Text("123.456.789-88 - ANDERSON DA SILVA DELGADO")
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
fun InvolvedWitnessPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            InvolvedWitnessContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}