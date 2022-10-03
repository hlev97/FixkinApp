package hu.bme.aut.it9p0z.components.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.it9p0z.model.UiText
import hu.bme.aut.it9p0z.ui.theme.FixkinTheme
import hu.bme.aut.it9p0z.ui.theme.corner_radius_s

@ExperimentalMaterial3Api
@Composable
fun TextButton(
    uiText: UiText,
    modifier: Modifier = Modifier,
    shape: Shape,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors()
) {
    val context = LocalContext.current
    Button(
        shape = shape,
        onClick = onClick,
        modifier = modifier,
        colors = colors
    ) {
        Text(text = uiText.asString(context))
    }
}

@Preview
@ExperimentalMaterial3Api
@Composable
fun TextButton_Preview() {
    FixkinTheme {
        TextButton(
            uiText = UiText.DynamicString("Button"),
            shape = RoundedCornerShape(corner_radius_s),
            onClick = { /*TODO*/ }
        )
    }
}