package hu.bme.aut.it9p0z.fixkin.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import hu.bme.aut.conditionlog_ui.create.CreateConditionLogScreen
import hu.bme.aut.conditionlog_ui.edit.EditConditionLogScreen
import hu.bme.aut.it9p0z.fixkin.screen.MainScreen
import hu.bme.aut.survey_ui.screen.SurveyScreen

@ExperimentalPagerApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState,
) {
    var isHidden by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navController,snackbarHostState)
        composable(Graph.HOME) {
            MainScreen(
                modifier = Modifier,
                isHidden = isHidden,
                state = state,
                stateChanged = { state = !state },
                visibilityChanged = { isHidden = it }
            )
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val HOME = "home_graph"
}

