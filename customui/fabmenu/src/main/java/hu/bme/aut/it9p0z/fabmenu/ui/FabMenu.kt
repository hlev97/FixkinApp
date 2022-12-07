package hu.bme.aut.it9p0z.fabmenu.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CircleNotifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import hu.bme.aut.it9p0z.fabmenu.model.FabMenuItemModel

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun FabMenu(
    state: Boolean,
    onClick: (Boolean) -> Unit,
    onItemClick: (String) -> Unit,
    menuItems: List<FabMenuItemModel>,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(if (state) 10.dp else 5.dp)

    Surface(
        modifier = modifier
            .wrapContentSize()
            .clip(shape)
            .clickable(
                enabled = !state,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) {
                onClick(!state)
            }
            .padding(5.dp),
        shadowElevation = 5.dp,
        tonalElevation = 5.dp,
        shape = shape,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        AnimatedContent(
            targetState = state,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    IntSize(targetSize.width, initialSize.height)
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    IntSize(initialSize.width, targetSize.height)
                                    durationMillis = 300
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    FabMenuItemsContainer(
                        menuItems,
                        onClick = {
                            onItemClick(it)
                            onClick(false)
                        }
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(10.dp)
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun FabMenu_Preview() {
    val state by remember { mutableStateOf(false) }
    FabMenu(
        state = state,
        onClick = { },
        menuItems =listOf(
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
            ),
        ),
        onItemClick = { }
    )
}

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Preview
@Composable
fun FabMenuScaffold_Preview() {
    val state = remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("Click me") }
    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    if (state.value) {
                        state.value = !state.value
                    }
                }
            )
        },
        floatingActionButton = {
            FabMenu(
                state = state.value,
                onClick = { newState ->
                    if (!state.value) {
                        state.value = newState
                    }
                },
                menuItems = listOf(
                    FabMenuItemModel(
                        title = "Example Menu Item",
                        icon = Icons.Rounded.CircleNotifications,
                        route = "Example Menu Item 1"
                    ),
                    FabMenuItemModel(
                        title = "Example Menu Item",
                        icon = Icons.Rounded.CircleNotifications,
                        route = "Example Menu Item 2"
                    ),
                    FabMenuItemModel(
                        title = "Example Menu Item",
                        icon = Icons.Rounded.CircleNotifications,
                        route = "Example Menu Item 3"
                    ),
                ),
                onItemClick = { text = it }
            )
        },
        bottomBar = {
            BottomAppBar {

            }
        }
    ) {
        val alpha = if (!state.value) 0f else 0.4f
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { text = "Clicked" }
                ) {
                    Text(text = text)
                }
            }
            if (state.value) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color.Black.copy(alpha)
                ) { }
            }
        }
    }
}