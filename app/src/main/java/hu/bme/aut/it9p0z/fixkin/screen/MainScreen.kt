package hu.bme.aut.it9p0z.fixkin.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Note
import androidx.compose.material.icons.rounded.Psychology
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import hu.bme.aut.it9p0z.fabmenu.model.FabMenuItemModel
import hu.bme.aut.it9p0z.fabmenu.ui.FabMenu
import hu.bme.aut.it9p0z.fixkin.R
import hu.bme.aut.it9p0z.fixkin.navigation.graphs.MainNavGraph
import hu.bme.aut.it9p0z.fixkin.screen.model.BottomNavItem
import hu.bme.aut.it9p0z.ui.model.UiText
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.it9p0z.fixkin.navigation.screens.*
import hu.bme.aut.it9p0z.ui.model.UiIcon
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun MainScreen(
    state: Boolean,
    stateChanged: () -> Unit,
    onLogOut: () -> Unit,
    isHidden: Boolean,
    visibilityChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()

    BackHandler {

    }

    var selectedItemIndex by remember { mutableStateOf(0) }
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.History,
        BottomNavItem.Statistics
    )

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val createConditionLogEnabled by viewModel.createConditionLogEnabled.collectAsState()
    val createSurveyLogEnabled by viewModel.createSurveyLogEnabled.collectAsState()

    val context = LocalContext.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                TextButton(
                    onClick = {
                        viewModel.logOut()
                        onLogOut()
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = UiText.StringResource(R.string.log_out).asString(context)

                    )
                }
            }
        }) {
        Scaffold(
            topBar = {
                if (!isHidden) {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                UiIcon.Image(Icons.Rounded.Menu).AsImage()
                            }
                        },
                        title = { }
                    )
                }
            },
            modifier = modifier.fillMaxSize(),
            bottomBar = {
                if (!isHidden) {
                    NavigationBar {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index,
                                onClick = {
                                    selectedItemIndex = index
                                    navController.navigate(item.route)
                                },
                                label = if (selectedItemIndex != index) {
                                    { Text(text = item.label.asString(context)) }
                                } else {
                                    null
                                },
                                icon = { item.icon.AsImage() }
                            )
                        }
                    }
                }
            },
            floatingActionButton = {
                if (!isHidden) {
                    FabMenu(
                        state = state,
                        onClick = { newState ->
                            if (!state) {
                                stateChanged()
                            }
                        },
                        menuItems = listOf(
                            FabMenuItemModel(
                                title = UiText.StringResource(
                                    R.string.fab_menu_item_label_create_condition_log
                                ).asString(context),
                                icon = Icons.Rounded.Note,
                                route = Screen.CreateLog.route,
                                enabled = createConditionLogEnabled
                            ),
                            FabMenuItemModel(
                                title = UiText.StringResource(
                                    R.string.fab_menu_item_label_fill_out_survey
                                ).asString(context),
                                icon = Icons.Rounded.Psychology,
                                route = Screen.Survey.route,
                                enabled = createSurveyLogEnabled
                            ),
                        ),
                        onItemClick = {
                            visibilityChanged(true)
                            stateChanged()
                            navController.navigate(route = it)
                        },
                        modifier = Modifier.testTag("fabMenu")
                    )
                }
            }
        ) {
            val alpha = if (!state) 0f else 0.4f
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                MainNavGraph(
                    visibilityChanged = visibilityChanged,
                    navController = navController,
                    modifier = Modifier.fillMaxSize(),
                    onBack = { route ->
                        val index = when (route) {
                            Screen.Home.route -> 0
                            Screen.History.route -> 1
                            Screen.Statistics.route -> 2
                            else -> 0
                        }
                        selectedItemIndex = index
                    },
                    onSave = viewModel::loadLastDates
                )
                if (state) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { stateChanged() },
                        color = Color.Black.copy(alpha)
                    ) { }
                }
            }
        }
    }
}