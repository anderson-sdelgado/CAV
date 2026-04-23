package br.com.usinasantafe.cav.presenter.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemDefaultListDesign(
    id: Int = 0,
    text: String,
    font: Int = 22,
    padding: Int = 8,
    setActionItem: () -> Unit
) {
    return Text(
        textAlign = TextAlign.Left,
        text = text,
        fontSize = font.sp,
        modifier = Modifier
            .padding(vertical = padding.dp)
            .fillMaxWidth()
            .clickable {
                setActionItem()
            }
            .testTag("item_list_$id")
    )
}

@Composable
fun TitleDesign(
    text: String,
    font: Int = 30,
    padding: Int = 8
) {
    return Text(
        textAlign = TextAlign.Center,
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = font.sp,
        modifier = Modifier
            .padding(vertical = padding.dp)
            .fillMaxWidth()
    )
}


const val TAG_BUTTON_OK_ALERT_DIALOG_SIMPLE = "tag_button_ok_alert_dialog_simple"
const val TAG_BUTTON_YES_ALERT_DIALOG_CHECK = "tag_button_yes_alert_dialog_check"
const val TAG_BUTTON_NO_ALERT_DIALOG_CHECK = "tag_button_no_alert_dialog_check"
@Composable
fun AlertDialogSimpleDesign(
    text: String,
    setCloseDialog: () -> Unit
) {
    return AlertDialog(
        title = {
            Text(
                text = "ATENÇÃO",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = text,
                modifier = Modifier.testTag("text_alert_dialog_simple")
            )
        },
        onDismissRequest = setCloseDialog,
        confirmButton = {
            Button(
                onClick = setCloseDialog,
                modifier = Modifier.testTag(TAG_BUTTON_OK_ALERT_DIALOG_SIMPLE)
            ) {
                Text("OK")
            }
        },
    )
}


@Composable
fun AlertDialogSimpleDesign(
    text: String,
    setCloseDialog: () -> Unit,
    setActionButtonOK: () -> Unit
) {
    return AlertDialog(
        title = {
            Text(
                text = "ATENÇÃO",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = text,
                modifier = Modifier.testTag("text_alert_dialog_simple")
            )
        },
        onDismissRequest = setCloseDialog,
        confirmButton = {
            Button(
                onClick = setActionButtonOK,
                modifier = Modifier.testTag("button_ok_alert_dialog_simple")
            ) {
                Text("OK")
            }
        },
    )
}

@Composable
fun TextFieldPasswordDesign(
    value: String,
    onValueChange: (String) -> Unit,
    tag: String = ""
) {
    return OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        textStyle = TextStyle(
            fontSize = 24.sp
        ),
        visualTransformation = PasswordVisualTransformation(),
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth().testTag(tag)
    )
}

@Composable
fun TextButtonDesign(text: String) {
    return Text(
        textAlign = TextAlign.Center,
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    )
}
