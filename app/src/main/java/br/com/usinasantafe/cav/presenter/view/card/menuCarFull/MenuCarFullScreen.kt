package br.com.usinasantafe.cav.presenter.view.card.menuCarFull

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme

@Composable
fun MenuCarFullScreen() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MenuCarFullContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun MenuCarFullContent(
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
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray.copy(alpha = 0.2f))
                        .padding(12.dp),
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.text_item_local
                        ),
                        fontWeight = FontWeight.Bold
                    )
                    Button(
                        onClick = {},
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.text_pattern_edit
                            ),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuCarFullPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MenuCarFullContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}