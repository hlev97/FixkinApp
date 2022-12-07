package hu.bme.aut.it9p0z.authentication_ui.register.identity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.it9p0z.authentication_domain.usecases.*
import hu.bme.aut.it9p0z.authentication_ui.R
import hu.bme.aut.it9p0z.ui.model.UiEvent
import hu.bme.aut.it9p0z.ui.model.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdentityViewModel @Inject constructor(
    private val usernameIsFree: CheckUsernameIsFreeUseCase,
    private val passwordRequirements: CheckPasswordRequirementsUseCase,
    private val saveUsername: SaveUsernameUseCase,
    private val saveFullName: SaveFullNameUseCase,
    private val savePassword: SavePasswordUseCase,
) : ViewModel() {

    var username by mutableStateOf("")
        private set

    fun onUsernameChanged(value: String) {
        if (usernameError) usernameError = !usernameError
        username = value.trim()
    }

    var usernameError by mutableStateOf(false)
        private set

    fun onUsernameErrorStateChanged() {
        usernameError = !usernameError
    }

    var fullName by mutableStateOf("")
        private set

    fun onFullNameChanged(value: String) {
        if (fullNameError) fullNameError = !fullNameError
        fullName = value.trim()
    }

    var fullNameError by mutableStateOf(false)
        private set

    fun onFullNameErrorStateChanged() {
        fullNameError = !fullNameError
    }

    var password by mutableStateOf("")
        private set

    var passwordError by mutableStateOf(false)
        private set

    var passwordVisibility by mutableStateOf(false)
        private set

    fun onPasswordChanged(value: String) {
        if (passwordError) passwordError = !passwordError
        password = value.trim()
        with(passwordRequirements) {
            isLowerCaseSatisfied = hasLowerCaseLetter(password)
            isUpperCaseSatisfied = hasUpperCaseLetter(password)
            isDigitSatisfied = hasDigit(password)
            isSpecialCharSatisfied = hasSpecialChar(password)
            isCorrectLengthSatisfied = hasCorrectLength(password)
        }
    }

    fun onPasswordErrorStateChanged() {
        passwordError = !passwordError
    }

    fun onPasswordVisibilityChanged() {
        passwordVisibility = !passwordVisibility
    }

    var confirmPassword by mutableStateOf("")
        private set

    var confirmPasswordError by mutableStateOf(false)
        private set

    var confirmPasswordVisibility by mutableStateOf(false)
        private set

    fun onConfirmPasswordChanged(value: String) {
        if (confirmPasswordError) confirmPasswordError = !confirmPasswordError
        confirmPassword = value.trim()
    }

    fun onConfirmPasswordErrorStateChanged() {
        confirmPasswordError = !confirmPasswordError
    }

    fun onConfirmPasswordVisibilityChanged() {
        confirmPasswordVisibility = !confirmPasswordVisibility
    }

    var isLowerCaseSatisfied by mutableStateOf(false)
        private set

    var isUpperCaseSatisfied by mutableStateOf(false)
        private set

    var isDigitSatisfied by mutableStateOf(false)
        private set

    var isSpecialCharSatisfied by mutableStateOf(false)
        private set

    var isCorrectLengthSatisfied by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun preformNextClick() {

        viewModelScope.launch {
            try {
                if (
                    username.isNotEmpty() && fullName.isNotEmpty() &&
                    password.isNotEmpty() && confirmPassword.isNotEmpty()
                ) {
                    if (usernameIsFree(username)) {
                        if (
                            isLowerCaseSatisfied && isUpperCaseSatisfied &&
                            isDigitSatisfied && isSpecialCharSatisfied && isCorrectLengthSatisfied
                        ) {
                            if (passwordRequirements.isPasswordAndConfirmPasswordSame(
                                    password,
                                    confirmPassword
                                )
                            ) {
                                saveUsername(username)
                                saveFullName(fullName)
                                savePassword(password)
                                _uiEvent.send(UiEvent.Success)
                            } else {
                                passwordError = true
                                confirmPasswordError = true
                                _uiEvent.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_password_does_not_match)))
                            }
                        } else {
                            passwordError = true
                            _uiEvent.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_password_requirements)))
                        }
                    } else {
                        usernameError = true
                        _uiEvent.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_username_is_already_in_use)))
                    }
                } else {
                    _uiEvent.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_empty_fields)))
                }
            } catch (e: Exception) {
                _uiEvent.send(UiEvent.ShowSnackbar(e.message?.let { UiText.DynamicString(it) }
                    ?: UiText.StringResource(R.string.some_error)))
            }
        }
    }
}