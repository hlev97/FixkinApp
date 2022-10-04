package hu.bme.aut.it9p0z.authentication_ui.register.weightandheight

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Height
import androidx.compose.material.icons.rounded.MonitorWeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.it9p0z.authentication_ui.R
import hu.bme.aut.it9p0z.components.buttons.TextButton
import hu.bme.aut.it9p0z.components.textfields.NumberTextField
import hu.bme.aut.it9p0z.model.UiEvent
import hu.bme.aut.it9p0z.model.UiIcon
import hu.bme.aut.it9p0z.model.UiText
import hu.bme.aut.it9p0z.ui.theme.corner_radius_s
import hu.bme.aut.it9p0z.ui.theme.dp_m

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun WeightAndHeightScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit,
    viewModel: WeightAndHeightViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumberTextField(
            value = viewModel.weight,
            label = UiText.StringResource(id = R.string.label_weight),
            onValueChange = { viewModel.onWeightChanged(it) },
            leadingIcon = UiIcon.Image(Icons.Rounded.MonitorWeight),
            onTrailingIconClick = { },
            trailingIcon = null,
            isError = viewModel.weightError,
            onErrorStateChange = { viewModel.onWeightErrorStateChanged() },
            onDone = {
                focusManager.moveFocus(FocusDirection.Down)
            },
            modifier = Modifier.padding(bottom = dp_m)
        )
        NumberTextField(
            value = viewModel.height,
            label = UiText.StringResource(id = R.string.label_height),
            onValueChange = { viewModel.onHeightChanged(it) },
            leadingIcon = UiIcon.Image(Icons.Rounded.Height),
            onTrailingIconClick = { },
            trailingIcon = null,
            isError = viewModel.heightError,
            onErrorStateChange = { viewModel.onHeightErrorStateChanged() },
            onDone = {
                focusManager.moveFocus(FocusDirection.Exit)
                keyboardController?.hide()
            },
            modifier = Modifier.padding(bottom = dp_m)
        )
        TextButton(
            uiText = UiText.StringResource(id = R.string.button_next),
            shape = RoundedCornerShape(corner_radius_s),
            onClick = { viewModel.preformNextClick() },
            modifier = Modifier.width(TextFieldDefaults.MinWidth)
        )
    }
}