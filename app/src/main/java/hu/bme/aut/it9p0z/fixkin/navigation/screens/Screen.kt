package hu.bme.aut.it9p0z.fixkin.navigation.screens

sealed class Screen(val route: String) {
    object Login : Screen(route = "login")
    object RegisterIdentity : Screen(route = "register/identity")
    object RegisterWeightAndHeight : Screen(route = "register/weight_and_height")
    object RegisterDiseases : Screen(route = "register/diseases")
    object RegisterMedicines : Screen(route = "register/medicines")
    object Home : Screen(route = "home")
    object History : Screen(route = "history")
    object Statistics : Screen(route = "statistics/{graphType}") {
        fun passGraphType(graphType: String): String = "statistics/$graphType"
    }
    object CreateLog: Screen(route = "create_log")
    object EditLog: Screen(route = "edit_log/{id}") {
        fun passId(id: Int): String = "edit_log/$id"
    }
    object Survey: Screen(route = "survey")
}

