package hu.bme.aut.statistics_ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.theme.dp_l

@Composable
fun DataOverview(
    dataMap: Map<String, Float>,
    colors: List<Color>,
    title: UiText
) {
    val context = LocalContext.current
    val triggers = dataMap.keys
    var size by remember { mutableStateOf(IntSize.Zero) }
    Box(
        Modifier
            .fillMaxSize()
            .onSizeChanged {
                size = it
            }.padding(top = dp_l),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(size.width.dp * 1/8),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title.asString(context),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            triggers.forEachIndexed { i, trigger ->
                Trigger(triggerName = trigger, color = colors[i])
            }
        }
    }
}


@Composable
fun Trigger(triggerName: String, color: Color) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Circle,
            contentDescription = null,
            modifier = Modifier.size(8.dp),
            tint = color
        )
        Spacer(modifier = Modifier.width(width = 12.dp))
        Text(
            text = triggerName,
            textAlign = TextAlign.Start
        )
    }
}



