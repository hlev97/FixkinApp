package hu.bme.aut.it9p0z.ui.components.appbars

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.it9p0z.ui.model.UiIcon
import hu.bme.aut.it9p0z.ui.model.UiText

@ExperimentalMaterial3Api
@Composable
fun NormalTopAppBar(
    title: UiText,
    navigationIcon: UiIcon = UiIcon.Image(Icons.Outlined.ArrowBack),
    onNavigation: () -> Unit,
    actions: @Composable RowScope.() -> Unit = { },
) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(text = title.asString(context))
        },
        navigationIcon = {
            IconButton(onClick = onNavigation) {
                navigationIcon.AsImage()
            }
        },
        actions = actions
    )
}

@ExperimentalMaterial3Api
@Composable
@Preview
fun NormalTopAppBar_Preview() {
    NormalTopAppBar(
        title = UiText.DynamicString("App bar"),
        onNavigation = { /*TODO*/ },
        actions = { }
    )
}