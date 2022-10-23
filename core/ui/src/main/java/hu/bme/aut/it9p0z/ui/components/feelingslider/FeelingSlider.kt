package hu.bme.aut.it9p0z.ui.components.feelingslider

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.theme.dp_m
import hu.bme.aut.it9p0z.ui.R
import hu.bme.aut.it9p0z.ui.components.chipgroup.ChipGroupDefaults
import hu.bme.aut.it9p0z.ui.data.FeelingUi

@Composable
fun FeelingSlider(
    modifier: Modifier = Modifier,
    sliderValue: Float,
    enabled: Boolean = true,
    onValueChange: (Float) -> Unit,
) {
    val context = LocalContext.current
    val feelings = listOf(
        FeelingUi.Sad,
        FeelingUi.Unhappy,
        FeelingUi.Neutral,
        FeelingUi.Happy,
        FeelingUi.Joyful
    )
    Text(
        text = UiText.StringResource(id = R.string.feeling_slider_label).asString(context),
        maxLines = ChipGroupDefaults.CHIP_GROUP_TITLE_MAX_LINES,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Start,
        modifier = modifier.fillMaxWidth(0.95f).padding(vertical = dp_m)
    )
    Slider(
        value = sliderValue,
        valueRange = 0f..4f,
        steps = 3,
        enabled = enabled,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(0.95f)
    )
    Row(
        modifier = modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = dp_m),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        feelings.forEach { feeling ->
            feeling.icon.AsImage()
        }
    }
}

@Preview
@Composable
fun FeelingSlider_Preview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FeelingSlider(
            sliderValue = 2f,
            onValueChange = {},
        )
    }
}