package hu.bme.aut.it9p0z.fixkin.navigation.graphs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import hu.bme.aut.it9p0z.authentication_ui.login.LoginScreen
import hu.bme.aut.it9p0z.authentication_ui.register.diseases.DiseasesScreen
import hu.bme.aut.it9p0z.authentication_ui.register.identity.IdentityScreen
import hu.bme.aut.it9p0z.authentication_ui.register.medicines.MedicinesScreen
import hu.bme.aut.it9p0z.authentication_ui.register.weightandheight.WeightAndHeightScreen
import hu.bme.aut.it9p0z.fixkin.navigation.screens.*

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
) {
    navigation(
        route = Graph.AUTH,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                snackbarHostState = snackbarHostState,
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                onRegisterClick = { navController.navigate(Screen.RegisterIdentity.route) }
            )
        }
        composable(route = Screen.RegisterIdentity.route) {
            IdentityScreen(
                snackbarHostState = snackbarHostState,
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                },
                onNextClick = { navController.navigate(Screen.RegisterWeightAndHeight.route) })
        }
        composable(route = Screen.RegisterWeightAndHeight.route) {
            WeightAndHeightScreen(
                snackbarHostState = snackbarHostState,
                onNextClick = { navController.navigate(Screen.RegisterDiseases.route) }
            )
        }
        composable(route = Screen.RegisterDiseases.route) {
            DiseasesScreen(onNextClick = { navController.navigate(Screen.RegisterMedicines.route) })
        }
        composable(route = Screen.RegisterMedicines.route) {
            MedicinesScreen(
                snackbarHostState = snackbarHostState,
                onNextClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                }
            )
        }
    }
}
