package hu.bme.aut.it9p0z.fixkin.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import hu.bme.aut.conditionlog_ui.create.CreateConditionLogScreen
import hu.bme.aut.conditionlog_ui.edit.EditConditionLogScreen
import hu.bme.aut.it9p0z.fixkin.navigation.screens.*
import hu.bme.aut.it9p0z.history_ui.HistoryScreen
import hu.bme.aut.it9p0z.home_ui.home.HomeScreen
import hu.bme.aut.statistics_ui.screen.StatisticsScreen
import hu.bme.aut.survey_ui.screen.SurveyScreen

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun MainNavGraph(
    visibilityChanged: (Boolean) -> Unit,
    onSave: () -> Unit,
    navController: NavHostController,
    onBack: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(modifier = modifier)
        }
        composable(Screen.History.route) {
            HistoryScreen(
                modifier = modifier,
                onEdit = {
                    visibilityChanged(true)
                    onBack(Screen.Home.route)
                    navController.navigate(Screen.EditLog.passId(it))
                },
                onBack = {
                    navController.popBackStack(route = Screen.Home.route, inclusive = false)
                    onBack(Screen.Home.route)
                }
            )
        }
        composable(
            route = Screen.Statistics.route,
            arguments = listOf(
                navArgument("graphType") { type = NavType.StringType }
            )
        ) {
            StatisticsScreen(
                modifier = modifier,
                onTabItemClick = {
                    navController.navigate(Screen.Statistics.passGraphType(it))
                },
                onBack = {
                    navController.popBackStack(route = Screen.Home.route, inclusive = false)
                    onBack(Screen.Home.route)
                }
            )
        }
        composable(
            route = Screen.CreateLog.route,
        ) {
            CreateConditionLogScreen(
                onFabClick = {
                    visibilityChanged(false)
                    onBack(Screen.Home.route)
                    onSave()
                    navController.popBackStack(route = Screen.Home.route, inclusive = false)
                },
                onBack = {
                    visibilityChanged(false)
                    onBack(Screen.Home.route)
                    navController.popBackStack(route = Screen.Home.route, inclusive = false)
                }
            )
        }
        composable(
            route = Screen.EditLog.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType} )
        ) {
            EditConditionLogScreen(
                onFabClick = {
                    visibilityChanged(false)
                    onBack(Screen.Home.route)
                    navController.popBackStack(route = Screen.Home.route, inclusive = false)
                },
                onBack = {
                    visibilityChanged(false)
                    onBack(Screen.Home.route)
                    navController.popBackStack(route = Screen.Home.route, inclusive = false)
                }
            )
        }
        composable(Screen.Survey.route) {
            SurveyScreen(
                onOkButtonClick = {
                    visibilityChanged(false)
                    onBack(Screen.Home.route)
                    onSave()
                    navController.popBackStack(route = Screen.Home.route, inclusive = false)
                },
                onBack = {
                    visibilityChanged(false)
                    onBack(Screen.Home.route)
                    navController.popBackStack(route = Screen.Home.route, inclusive = false)
                }
            )
        }
    }
}