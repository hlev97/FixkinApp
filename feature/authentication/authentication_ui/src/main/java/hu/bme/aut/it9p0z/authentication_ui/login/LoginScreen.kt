package hu.bme.aut.it9p0z.authentication_ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SupervisedUserCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.it9p0z.authentication_ui.R
import hu.bme.aut.it9p0z.components.buttons.BottomTextButton
import hu.bme.aut.it9p0z.components.buttons.TextButton
import hu.bme.aut.it9p0z.components.textfields.NormalTextField
import hu.bme.aut.it9p0z.components.textfields.PasswordTextField
import hu.bme.aut.it9p0z.model.UiEvent
import hu.bme.aut.it9p0z.model.UiIcon
import hu.bme.aut.it9p0z.model.UiText
import hu.bme.aut.it9p0z.ui.theme.corner_radius_m
import hu.bme.aut.it9p0z.ui.theme.corner_radius_s
import hu.bme.aut.it9p0z.ui.theme.dp_m

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onLoginClick()
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize(),
    ) {
        val (formRef, registerRef) = createRefs()

        Column(
            modifier = Modifier.constrainAs(formRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(registerRef.top)
            }
        ) {
            NormalTextField(
                value = viewModel.username,
                label = UiText.StringResource(id = R.string.label_username),
                onValueChange = { viewModel.onUsernameChanged(it) },
                leadingIcon = UiIcon.Image(Icons.Rounded.SupervisedUserCircle),
                onTrailingIconClick = { },
                trailingIcon = null,
                isError = viewModel.usernameError,
                onErrorStateChange = { viewModel.onUsernameErrorStateChanged() },
                onDone = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
                modifier = Modifier.padding(bottom = dp_m)
            )
            PasswordTextField(
                value = viewModel.password,
                label = UiText.StringResource(id = R.string.label_password),
                onValueChange = { viewModel.onPasswordChanged(it) },
                trailingIcon = null,
                isError = viewModel.passwordError,
                onErrorStateChange = { viewModel.onPasswordErrorStateChanged() },
                onDone = {
                    focusManager.moveFocus(FocusDirection.Exit)
                    keyboardController?.hide()
                },
                isVisible = viewModel.passwordVisibility,
                onVisibilityChanged = { viewModel.onPasswordVisibilityChanged() },
                modifier = Modifier.padding(bottom = dp_m),
            )
            TextButton(
                uiText = UiText.StringResource(id = R.string.button_login),
                shape = RoundedCornerShape(corner_radius_s),
                onClick = { viewModel.preformLogin() },
                modifier = Modifier.width(TextFieldDefaults.MinWidth)
            )
        }

        BottomTextButton(
            uiText = UiText.StringResource(id = R.string.button_no_account_yet),
            onClick = onRegisterClick,
            modifier = Modifier.constrainAs(registerRef) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}