package hu.bme.aut.it9p0z.fabmenu.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CircleNotifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.it9p0z.fabmenu.model.FabMenuItemModel

@ExperimentalMaterial3Api
@Composable
fun ColumnScope.FabMenuItemsContainer(
    menuItems: List<FabMenuItemModel>,
    onClick: (String) -> Unit
) {
    menuItems.forEachIndexed { index, menuItem ->

        if (index == 0) {
            Spacer(modifier = Modifier.height(10.dp))
        }

        FabMenuItem(
            menuItem = menuItem,
            onClick = onClick
        )

        Spacer(modifier = Modifier.height(10.dp))

    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun FabMenuItemsContainer_Preview() {
    Column {
        FabMenuItemsContainer(
            listOf(
                FabMenuItemModel(
                    title = "Example Menu Item",
                    icon = Icons.Rounded.CircleNotifications,
                    route = ""
                ),
                FabMenuItemModel(
                    title = "Example Menu Item",
                    icon = Icons.Rounded.CircleNotifications,
                    route = ""
                ),
                FabMenuItemModel(
                    title = "Example Menu Item",
                    icon = Icons.Rounded.CircleNotifications,
                    route = ""
                )
            ),
            onClick = { }
        )
    }
}