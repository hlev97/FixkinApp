package hu.bme.aut.it9p0z.fixkin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.it9p0z.fabmenu.model.FabMenuItemModel
import hu.bme.aut.it9p0z.fabmenu.ui.FabMenu
import hu.bme.aut.it9p0z.history_ui.HistoryScreen
import hu.bme.aut.it9p0z.home_ui.home.HomeScreen
import hu.bme.aut.it9p0z.ui.theme.FixkinTheme
import hu.bme.aut.survey_ui.screen.SurveyScreen

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

                val state = remember { mutableStateOf(false) }
                var text by remember { mutableStateOf("Click me") }
                
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState)},
                    floatingActionButton = {
                        FabMenu(
                            state = state.value,
                            onClick = { newState ->
                                if (!state.value) {
                                    state.value = newState
                                }
                            },
                            menuItems = listOf(
                                FabMenuItemModel(
                                    title = "Example Menu Item",
                                    icon = Icons.Default.CheckCircle,
                                    route = "Example Menu Item 1"
                                ),
                                FabMenuItemModel(
                                    title = "Example Menu Item",
                                    icon = Icons.Default.CheckCircle,
                                    route = "Example Menu Item 2"
                                ),
                                FabMenuItemModel(
                                    title = "Example Menu Item",
                                    icon = Icons.Default.CheckCircle,
                                    route = "Example Menu Item 3"
                                ),
                            ),
                            onItemClick = { text = it }
                        )
                    }
                ) {
                    SurveyScreen(modifier = Modifier.padding(it), onOkButtonClick = {})
//                    HomeScreen(modifier = Modifier.padding(it))
//                    HistoryScreen(modifier = Modifier.padding(it))
//                    EditConditionLogScreen(
//                        onFabClick = { /*TODO*/ },
//                        modifier = Modifier.padding(it)
//                    )
//                    CreateConditionLogScreen(
//                        onFabClick = { /*TODO*/ },
//                        modifier = Modifier.padding(it)
//                    )
                }
            }
        }
    }
}

