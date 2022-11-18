package hu.bme.aut.it9p0z.home_ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.flowlayout.FlowRow
import hu.bme.aut.it9p0z.ui.components.conditionloglistitem.TriggerUiChip
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.foodTriggerChips
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.theme.corner_radius_m
import hu.bme.aut.it9p0z.ui.theme.dp_m
import hu.bme.aut.it9p0z.ui.theme.dp_xs

@ExperimentalMaterial3Api
@Composable
fun MostCommonTriggers(
    title: UiText,
    triggers: List<UiChip>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(dp_m),
        shape = RoundedCornerShape(corner_radius_m),
        border = BorderStroke(dp_xs, MaterialTheme.colorScheme.surfaceTint)
    ) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .padding(dp_m),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = title.asString(context),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.surfaceTint
            )
            Spacer(modifier = Modifier.height(dp_m))
            val lastIndex = if (triggers.lastIndex < 5) triggers.lastIndex else 5
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                triggers.subList(0,lastIndex).forEach { trigger ->
                    TriggerUiChip(trigger = trigger)
                }
            }
        }

    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MostCommonTriggers_Preview() {
    MostCommonTriggers(
        title = UiText.DynamicString("Your top 5 food trigger is: "),
        triggers = foodTriggerChips
    )
}