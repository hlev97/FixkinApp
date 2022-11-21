package hu.bme.aut.it9p0z.fabmenu.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CircleNotifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.it9p0z.fabmenu.model.FabMenuItemModel

@ExperimentalMaterial3Api
@Composable
fun FabMenuItem(
    menuItem: FabMenuItemModel,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    PressIconButton(
        enabled = menuItem.enabled,
        onClick = { onClick(menuItem.route) },
        icon = {
            Icon(
                imageVector = menuItem.icon,
                contentDescription = null,
            )
        },
        text = {
            Text(text = menuItem.title)
        },
        modifier = modifier.testTag(menuItem.title)
    )
}

@Composable
fun PressIconButton(
    enabled: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        enabled = enabled,
        onClick = {
            onClick()
        },
        modifier = modifier
            .padding(horizontal = 10.dp),
        interactionSource = interactionSource
    ) {
        AnimatedVisibility(
            visible = isPressed
        ) {
            if (isPressed) {
                Row {
                    icon()
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }
            }
        }
        text()
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun FabMenuItem_Preview() {
    FabMenuItem(
        menuItem = FabMenuItemModel(
            title = "Example Menu Item",
            icon = Icons.Rounded.CircleNotifications,
            route = ""
        ),
        onClick = {}
    )
}