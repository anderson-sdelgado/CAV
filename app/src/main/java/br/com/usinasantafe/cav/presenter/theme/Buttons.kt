package br.com.usinasantafe.cav.presenter.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.lib.TypeButton
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun addTextField(text: String, char: String): String {
    return text + char
}

fun addTextFieldComma(text: String, digit: String): String {
    val cleanText = text.filter { it.isDigit() } + digit
    val valueInTenths = cleanText.toLongOrNull() ?: 0L
    val value = valueInTenths / 10.0
    val format = DecimalFormat("#,##0.0")
    format.decimalFormatSymbols = DecimalFormatSymbols(Locale.Builder().setLanguage("pt").setRegion("BR").build())
    return format.format(value)
}

fun clearTextField(text: String): String {
    return if (text.length > 1) text.substring(0, text.length - 1) else ""
}

fun clearTextFieldComma(text: String): String {
    val cleanText = text.filter { it.isDigit() }
    val reducedText = if (cleanText.length > 1) cleanText.dropLast(1) else ""
    val valueInTenths = reducedText.toLongOrNull() ?: 0L
    val value = valueInTenths / 10.0
    val format = DecimalFormat("#,##0.0")
    format.decimalFormatSymbols = DecimalFormatSymbols(Locale.Builder().setLanguage("pt").setRegion("BR").build())
    return format.format(value)
}

@Composable
fun ButtonsGenericNumeric(
    onTextField: (
        text: String,
        typeButton: TypeButton,
    ) -> Unit,
    flagUpdate: Boolean = true,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val number1 = stringResource(id = R.string.text_number_1)
        val number2 = stringResource(id = R.string.text_number_2)
        val number3 = stringResource(id = R.string.text_number_3)
        val number4 = stringResource(id = R.string.text_number_4)
        val number5 = stringResource(id = R.string.text_number_5)
        val number6 = stringResource(id = R.string.text_number_6)
        val number7 = stringResource(id = R.string.text_number_7)
        val number8 = stringResource(id = R.string.text_number_8)
        val number9 = stringResource(id = R.string.text_number_9)
        val number0 = stringResource(id = R.string.text_number_0)
        val clean = stringResource(id = R.string.text_pattern_clean)
        val ok = stringResource(id = R.string.text_pattern_ok)
        val update = stringResource(id = R.string.text_pattern_update)
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            ButtonNumericDesign(
                {
                    TextButtonNumericDesign(
                        text = number1
                    )
                },
                {
                    onTextField(
                        number1,
                        TypeButton.NUMERIC
                    )
                },
                Modifier
                    .weight(1f)
            )
            ButtonNumericDesign(
                {
                    TextButtonNumericDesign(
                        text = number2
                    )
                },
                {
                    onTextField(
                        number2,
                        TypeButton.NUMERIC
                    )
                },
                Modifier
                    .weight(1f)
            )
            ButtonNumericDesign(
                {
                    TextButtonNumericDesign(
                        text = number3
                    )
                },
                {
                    onTextField(
                        number3,
                        TypeButton.NUMERIC
                    )
                },
                Modifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ButtonNumericDesign(
                {
                    TextButtonNumericDesign(
                        text = number4
                    )
                },
                {
                    onTextField(
                        number4,
                        TypeButton.NUMERIC
                    )
                },
                Modifier
                    .weight(1f)
            )
            ButtonNumericDesign(
                {
                    TextButtonNumericDesign(
                        text = number5
                    )
                },
                {
                    onTextField(
                        number5,
                        TypeButton.NUMERIC
                    )
                },
                Modifier
                    .weight(1f)
            )
            ButtonNumericDesign(
                {
                    TextButtonNumericDesign(
                        text = number6
                    )
                },
                {
                    onTextField(
                        number6,
                        TypeButton.NUMERIC
                    )
                },
                Modifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ButtonNumericDesign(
                {
                    TextButtonNumericDesign(
                        text = number7
                    )
                },
                {
                    onTextField(
                        number7,
                        TypeButton.NUMERIC
                    )
                },
                Modifier
                    .weight(1f)
            )
            ButtonNumericDesign(
                {
                    TextButtonNumericDesign(
                        text = number8
                    )
                },
                {
                    onTextField(
                        number8,
                        TypeButton.NUMERIC
                    )
                },
                Modifier
                    .weight(1f)
            )
            ButtonNumericDesign(
                {
                    TextButtonNumericDesign(
                        text = number9
                    )
                },
                {
                    onTextField(
                        number9,
                        TypeButton.NUMERIC
                    )
                },
                Modifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ButtonNumericDesign(
                text = {
                    TextButtonCleanDesign(
                        text = clean
                    )
                },
                {
                    onTextField(
                        clean,
                        TypeButton.CLEAN
                    )
                },
                modifier = Modifier
                    .weight(1f)
            )
            ButtonNumericDesign(
                {
                    TextButtonNumericDesign(
                        text = number0
                    )
                },
                {
                    onTextField(
                        number0,
                        TypeButton.NUMERIC
                    )
                },
                Modifier
                    .weight(1f)
            )
            ButtonNumericDesign(
                {
                    TextButtonNumericDesign(
                        text = ok
                    )
                },
                {
                    onTextField(
                        ok,
                        TypeButton.OK
                    )
                },
                Modifier
                    .weight(1f)
            )
        }
        if(flagUpdate){
            Row(
                modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ButtonNumericDesign(
                    {
                        TextButtonNumericDesign(
                            text = update
                        )
                    },
                    {
                        onTextField(
                            update,
                            TypeButton.UPDATE
                        )
                    },
                    Modifier
                        .weight(1f)
                )
            }
        }
    }
}
