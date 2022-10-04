package hu.bme.aut.it9p0z.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.it9p0z.model.UiText
import hu.bme.aut.it9p0z.ui.theme.dp_l
import hu.bme.aut.it9p0z.ui.theme.dp_m

@Composable
fun BottomTextButton(
    uiText: UiText,
    modifier: Modifier = Modifier,
    enabled: Boolean= true,
    shape: Shape = RoundedCornerShape(topStart = dp_m, topEnd = dp_m),
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = shape)
            .clickable(
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            ),
        shape = shape,
        color = MaterialTheme.colorScheme.primary
    ) {
        Text(
            text = uiText.asString(context),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(dp_l),
            maxLines = 1,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
fun BottomTextButton_Preview() {
    BottomTextButton(
        uiText = UiText.DynamicString("Button"),
        onClick = {}
    )
}