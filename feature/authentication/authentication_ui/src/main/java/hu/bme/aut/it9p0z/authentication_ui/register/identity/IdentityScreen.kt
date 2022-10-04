package hu.bme.aut.it9p0z.authentication_ui.register.identity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.SupervisedUserCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.it9p0z.authentication_ui.R
import hu.bme.aut.it9p0z.ui.components.buttons.BottomTextButton
import hu.bme.aut.it9p0z.ui.components.buttons.TextButton
import hu.bme.aut.it9p0z.ui.components.textfields.NormalTextField
import hu.bme.aut.it9p0z.ui.components.textfields.PasswordTextField
import hu.bme.aut.it9p0z.ui.model.UiEvent
import hu.bme.aut.it9p0z.ui.model.UiIcon
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.theme.*

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun IdentityScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onNextClick: () -> Unit,
    viewModel: IdentityViewModel = hiltViewModel()
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

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize(),
    ) {
        val (formRef, passwordReqCheckRef, loginRef) = createRefs()

        Column(
            modifier = Modifier.constrainAs(formRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(passwordReqCheckRef.top)
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
            NormalTextField(
                value = viewModel.fullName,
                label = UiText.StringResource(id = R.string.label_fullName),
                onValueChange = { viewModel.onFullNameChanged(it) },
                leadingIcon = null,
                onTrailingIconClick = { },
                trailingIcon = null,
                isError = viewModel.fullNameError,
                onErrorStateChange = { viewModel.onFullNameErrorStateChanged() },
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
                    focusManager.moveFocus(FocusDirection.Down)
                },
                isVisible = viewModel.passwordVisibility,
                onVisibilityChanged = { viewModel.onPasswordVisibilityChanged() },
                modifier = Modifier.padding(bottom = dp_m),
            )
            PasswordTextField(
                value = viewModel.confirmPassword,
                label = UiText.StringResource(id = R.string.label_confirm_password),
                onValueChange = { viewModel.onConfirmPasswordChanged(it) },
                trailingIcon = null,
                isError = viewModel.confirmPasswordError,
                onErrorStateChange = { viewModel.onConfirmPasswordErrorStateChanged() },
                onDone = {
                    focusManager.moveFocus(FocusDirection.Exit)
                    keyboardController?.hide()
                },
                isVisible = viewModel.confirmPasswordVisibility,
                onVisibilityChanged = { viewModel.onConfirmPasswordVisibilityChanged() },
                modifier = Modifier.padding(bottom = dp_m),
            )
            TextButton(
                uiText = UiText.StringResource(id = R.string.button_next),
                shape = RoundedCornerShape(corner_radius_s),
                onClick = { viewModel.preformNextClick() },
                modifier = Modifier.width(TextFieldDefaults.MinWidth)
            )
        }

        PasswordRequirementsChecker(
            isLowerCaseLetterSatisfied = viewModel.isLowerCaseSatisfied ,
            isUpperCaseLetterSatisfied = viewModel.isUpperCaseSatisfied,
            isDigitSatisfied = viewModel.isDigitSatisfied,
            isSpecialCharSatisfied = viewModel.isSpecialCharSatisfied,
            isCorrectLengthSatisfied = viewModel.isCorrectLengthSatisfied,
            modifier = Modifier.constrainAs(passwordReqCheckRef) {
                top.linkTo(formRef.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(loginRef.top)
            }.fillMaxWidth(0.9f)
        )

        BottomTextButton(
            uiText = UiText.StringResource(id = R.string.button_already_have_account),
            onClick = onLoginClick,
            modifier = Modifier.constrainAs(loginRef) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
fun PasswordRequirementsChecker(
    modifier: Modifier = Modifier,
    isLowerCaseLetterSatisfied: Boolean,
    isUpperCaseLetterSatisfied: Boolean,
    isDigitSatisfied: Boolean,
    isSpecialCharSatisfied: Boolean,
    isCorrectLengthSatisfied: Boolean,
) {
    Surface(
        modifier = modifier.padding(dp_m).wrapContentHeight(),
        shape = RoundedCornerShape(corner_radius_m),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = dp_s
    ) {
        Column(modifier = Modifier
            .padding(dp_m)
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
            PasswordRequirement(
                passwordRequirementText = UiText.StringResource(id = R.string.text_password_requirement_has_lowercase),
                isSatisfied = isLowerCaseLetterSatisfied
            )
            PasswordRequirement(
                passwordRequirementText = UiText.StringResource(id = R.string.text_password_requirement_has_uppercase),
                isSatisfied = isUpperCaseLetterSatisfied
            )
            PasswordRequirement(
                passwordRequirementText = UiText.StringResource(id = R.string.text_password_requirement_has_digit),
                isSatisfied = isDigitSatisfied
            )
            PasswordRequirement(
                passwordRequirementText = UiText.StringResource(id = R.string.text_password_requirement_has_special_char),
                isSatisfied = isSpecialCharSatisfied
            )
            PasswordRequirement(
                passwordRequirementText = UiText.StringResource(id = R.string.text_password_requirement_has_correct_length),
                isSatisfied = isCorrectLengthSatisfied
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PasswordRequirementsChecker_Preview() {
    PasswordRequirementsChecker(
        isLowerCaseLetterSatisfied = false,
        isUpperCaseLetterSatisfied = true,
        isDigitSatisfied = false,
        isSpecialCharSatisfied = true,
        isCorrectLengthSatisfied = false
    )
}

@Composable
fun PasswordRequirement(
    passwordRequirementText: UiText,
    isSatisfied: Boolean,
) {
    val context = LocalContext.current
    val color = if (isSatisfied) MaterialTheme.colorScheme.satisfiedColor
                else MaterialTheme.colorScheme.idleColor

    Row(
        modifier = Modifier
            .padding(vertical = dp_m)
            .padding(bottom = dp_xs),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.Check,
            contentDescription = null,
            tint = color,
            modifier = Modifier.padding(end = dp_m)
        )
        Text(
            text = passwordRequirementText.asString(context),
            color = color,
            textAlign = TextAlign.Start
        )
    }
}


@Preview
@Composable
fun UiPasswordRequirement_Preview() {
    val isSatisfied by remember { mutableStateOf(false) }
    PasswordRequirement(
        passwordRequirementText = UiText.DynamicString("Password requirement"),
        isSatisfied = isSatisfied
    )
}