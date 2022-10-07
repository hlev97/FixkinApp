package hu.bme.aut.it9p0z.fixkin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.it9p0z.authentication_ui.login.LoginScreen
import hu.bme.aut.it9p0z.ui.theme.FixkinTheme

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FixkinTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState)}
                ) {
                    LoginScreen(
                        snackbarHostState = snackbarHostState,
                        onLoginClick = { /*TODO*/ },
                        onRegisterClick = {},
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}

