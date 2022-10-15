package hu.bme.aut.it9p0z.fabmenu.model

import androidx.compose.ui.graphics.vector.ImageVector

data class FabMenuItemModel(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val enabled: Boolean = true,
)