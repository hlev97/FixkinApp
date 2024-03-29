package hu.bme.aut.it9p0z.ui.components.textfields

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.it9p0z.ui.components.buttons.SmallIconButton
import hu.bme.aut.it9p0z.ui.model.UiIcon
import hu.bme.aut.it9p0z.ui.model.UiText

@ExperimentalMaterial3Api
@Composable
fun NumberTextField(
    value: String,
    label: UiText,
    onValueChange: (String) -> Unit,
    leadingIcon: UiIcon?,
    onTrailingIconClick: () -> Unit,
    trailingIcon: UiIcon?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    onErrorStateChange: () -> Unit,
    onDone: (KeyboardActionScope.() -> Unit)?
) {
    val context = LocalContext.current
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label.asString(context)) },
        leadingIcon = {
            leadingIcon?.AsImage()
        },
        trailingIcon = if (isError) {
            {
                SmallIconButton(
                    icon = UiIcon.Image(Icons.Rounded.ErrorOutline),
                    onClick = onErrorStateChange
                )
            }
        } else {
            {
                if (trailingIcon != null) {
                    SmallIconButton(icon = trailingIcon, onClick = onTrailingIconClick)
                }
            }
        },
        modifier = modifier,
        singleLine = true,
        readOnly = readOnly,
        isError = isError,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = onDone
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Preview
@Composable
fun NumberTextField_Preview() {
    var value by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    NumberTextField(
        value = value,
        label = UiText.DynamicString("height"),
        onValueChange = { newValue -> value = newValue },
        leadingIcon = UiIcon.Image(Icons.Rounded.Height),
        onTrailingIconClick = { /*TODO*/ },
        trailingIcon = null,
        onErrorStateChange = { /*TODO*/ },
        onDone = {
            keyboardController?.hide()
        }
    )
}
