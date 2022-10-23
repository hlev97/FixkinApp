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

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                snackbarHostState = snackbarHostState,
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                onRegisterClick = { navController.navigate(AuthScreen.RegisterIdentity.route) }
            )
        }
        composable(route = AuthScreen.RegisterIdentity.route) {
            IdentityScreen(
                snackbarHostState = snackbarHostState,
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(AuthScreen.Login.route)
                },
                onNextClick = { navController.navigate(AuthScreen.RegisterWeightAndHeight.route) })
        }
        composable(route = AuthScreen.RegisterWeightAndHeight.route) {
            WeightAndHeightScreen(
                snackbarHostState = snackbarHostState,
                onNextClick = { navController.navigate(AuthScreen.RegisterDiseases.route) }
            )
        }
        composable(route = AuthScreen.RegisterDiseases.route) {
            DiseasesScreen(onNextClick = { navController.navigate(AuthScreen.RegisterMedicines.route) })
        }
        composable(route = AuthScreen.RegisterMedicines.route) {
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

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "login")
    object RegisterIdentity : AuthScreen(route = "register/identity")
    object RegisterWeightAndHeight : AuthScreen(route = "register/weight_and_height")
    object RegisterDiseases : AuthScreen(route = "register/diseases")
    object RegisterMedicines : AuthScreen(route = "register/medicines")
}
