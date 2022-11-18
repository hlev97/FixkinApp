package hu.bme.aut.it9p0z.ui.model

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

sealed class UiIcon {
    data class Image(val image: ImageVector) : UiIcon()
    data class ImageResource(val id: Int) : UiIcon()

    @Composable
    fun AsImage(
        modifier: Modifier = Modifier,
        tint: Color = MaterialTheme.colorScheme.surfaceTint
    ) {
        return when(this) {
            is Image -> Icon(
                imageVector = this.image,
                contentDescription = null,
                modifier = modifier,
                tint = tint
            )
            is ImageResource -> Icon(
                painter = painterResource(id = id),
                contentDescription = null,
                modifier = modifier,
                tint = tint
            )
        }
    }
}