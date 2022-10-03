package hu.bme.aut.it9p0z.components.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hu.bme.aut.it9p0z.model.UiIcon


@Composable
fun SmallIconButton(
    icon: UiIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
) {
    IconButton(onClick = onClick) {
        icon.AsImage()
    }
}