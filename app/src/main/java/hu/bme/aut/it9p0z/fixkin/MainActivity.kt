package hu.bme.aut.it9p0z.fixkin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.it9p0z.fixkin.navigation.graphs.RootNavGraph
import hu.bme.aut.it9p0z.ui.theme.FixkinTheme

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FixkinTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState)},
                ) {
                    RootNavGraph(
                        snackbarHostState = snackbarHostState,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}

