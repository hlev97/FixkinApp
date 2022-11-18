package hu.bme.aut.it9p0z.authentication_ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.it9p0z.authentication_domain.usecases.CheckUsernameIsFreeUseCase
import hu.bme.aut.it9p0z.authentication_domain.usecases.LoginUserUseCase
import hu.bme.aut.it9p0z.authentication_ui.R
import hu.bme.aut.it9p0z.ui.model.UiEvent
import hu.bme.aut.it9p0z.ui.model.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val isUserNameFree: CheckUsernameIsFreeUseCase,
    private val loginUser: LoginUserUseCase
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

    var password by mutableStateOf("")
        private set

    var passwordError by mutableStateOf(false)
        private set

    var passwordVisibility by mutableStateOf(false)
        private set

    fun onPasswordChanged(value: String) {
        if (passwordError) passwordError = !passwordError
        password = value.trim()
    }

    fun onPasswordErrorStateChanged() {
        passwordError = !passwordError
    }

    fun onPasswordVisibilityChanged() {
        passwordVisibility = !passwordVisibility
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun preformLogin() {
        viewModelScope.launch {
            try {
                if (!isUserNameFree(username)) {
                    val result = loginUser(username, password)
                    if (!result) throw Exception()
                    _uiEvent.send(UiEvent.Success)
                } else _uiEvent.send(
                    UiEvent.ShowSnackbar(UiText.StringResource(id = R.string.error_invalid_username))
                )
            } catch (e: Exception) {
                _uiEvent.send(UiEvent.ShowSnackbar(UiText.StringResource(id = R.string.error_invalid_password)))
            }
        }
    }
}