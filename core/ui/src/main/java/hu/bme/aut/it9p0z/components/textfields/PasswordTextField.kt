package hu.bme.aut.it9p0z.components.textfields

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.it9p0z.components.buttons.SmallIconButton
import hu.bme.aut.it9p0z.model.UiIcon
import hu.bme.aut.it9p0z.model.UiText

@ExperimentalMaterial3Api
@Composable
fun PasswordTextField(
    value: String,
    label: UiText,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    leadingIcon: UiIcon? = UiIcon.Image(Icons.Rounded.Key),
    trailingIcon: UiIcon?,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    onErrorStateChange: () -> Unit,
    onDone: (KeyboardActionScope.() -> Unit)?,
    isVisible: Boolean = true,
    onVisibilityChanged: () -> Unit,
) {
    val context = LocalContext.current

    val visibilityIcon = if (isVisible) {
        UiIcon.Image(Icons.Rounded.VisibilityOff)
    } else {
        UiIcon.Image(Icons.Rounded.Visibility)
    }
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
                SmallIconButton(icon = visibilityIcon, onClick = onVisibilityChanged)
            }
        },
        modifier = modifier,
        singleLine = true,
        readOnly = readOnly,
        isError = isError,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = onDone
        ),
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Preview
@Composable
fun PasswordTextField_Preview() {
    var value by remember { mutableStateOf("password") }
    val keyboardController = LocalSoftwareKeyboardController.current
    PasswordTextField(
        value = value,
        label = UiText.DynamicString("password"),
        onValueChange = { newValue -> value = newValue },
        onVisibilityChanged = { /*TODO*/ },
        trailingIcon = UiIcon.Image(Icons.Rounded.Close),
        onErrorStateChange = { /*TODO*/ },
        onDone = {
            keyboardController?.hide()
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Preview
@Composable
fun PasswordTextFieldInvisible_Preview() {
    var value by remember { mutableStateOf("password") }
    val keyboardController = LocalSoftwareKeyboardController.current
    PasswordTextField(
        value = value,
        label = UiText.DynamicString("password"),
        onValueChange = { newValue -> value = newValue },
        onVisibilityChanged = { /*TODO*/ },
        trailingIcon = UiIcon.Image(Icons.Rounded.Close),
        onErrorStateChange = { /*TODO*/ },
        onDone = {
            keyboardController?.hide()
        },
        isVisible = false
    )
}
