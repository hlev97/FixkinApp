package hu.bme.aut.it9p0z.fixkin.screen.model

import hu.bme.aut.it9p0z.fixkin.R
import hu.bme.aut.it9p0z.fixkin.navigation.screens.Screen
import hu.bme.aut.it9p0z.ui.model.UiIcon
import hu.bme.aut.it9p0z.ui.model.UiText

sealed class BottomNavItem(val route: String, val icon: UiIcon, val label: UiText) {
    object Home: BottomNavItem(
        route = Screen.Home.route,
        icon = UiIcon.ImageResource(id = R.drawable.ic_home_24),
        label = UiText.StringResource(id = R.string.nav_item_label_home)
    )
    object History: BottomNavItem(
        route = Screen.History.route,
        icon = UiIcon.ImageResource(id = R.drawable.ic_list_24),
        label = UiText.StringResource(id = R.string.nav_item_lavel_history)
    )
    object Statistics: BottomNavItem(
        route = Screen.Statistics.passGraphType("food_triggers"),
        icon = UiIcon.ImageResource(id = R.drawable.ic_chart_24),
        label = UiText.StringResource(id = R.string.nav_item_label_stats)
    )
}
