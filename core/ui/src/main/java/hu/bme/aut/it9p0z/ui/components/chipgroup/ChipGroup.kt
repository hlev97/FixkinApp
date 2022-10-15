package hu.bme.aut.it9p0z.ui.components.chipgroup

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Fireplace
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import hu.bme.aut.it9p0z.ui.components.chipgroup.ChipGroupDefaults.CHIP_GROUP_TITLE_MAX_LINES
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiChip.Companion.toBoolean
import hu.bme.aut.it9p0z.ui.model.UiIcon
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.theme.FixkinTheme
import hu.bme.aut.it9p0z.ui.theme.dp_m
import hu.bme.aut.it9p0z.ui.theme.dp_s

@ExperimentalMaterial3Api
@Composable
fun ChipGroup(
    label: UiText?,
    chips: List<UiChip>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val context = LocalContext.current
    if (label != null) {
        Text(
            text = label.asString(context),
            maxLines = CHIP_GROUP_TITLE_MAX_LINES,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start,
            modifier = modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = dp_m)
        )
    }
    FlowRow(
        modifier = modifier.fillMaxWidth(0.95f),
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
        lastLineMainAxisAlignment = FlowMainAxisAlignment.Start
    ) {
       chips.forEach { chip ->
           var selected by remember { mutableStateOf(chip.state.toBoolean()) }
           ElevatedFilterChip(
               selected = selected,
               onClick = {
                   chip.onChangeState()
                   selected = chip.state == UiChip.UiChipState.SELECTED
                 },
               enabled = enabled,
               label = { Text(text = chip.label.asString(context))},
               leadingIcon = if (chip.state == UiChip.UiChipState.SELECTED) {
                   {
                       Icon(
                           imageVector = Icons.Rounded.Check,
                           contentDescription = null,
                           modifier = Modifier.size(20.dp)
                       )
                   }
               } else {
                   {
                       chip.icon?.AsImage(
                           modifier = modifier.size(20.dp)
                       )
                   }
               },
               modifier = Modifier.padding(dp_s)
           )
       }
    }
}

object ChipGroupDefaults {
    const val CHIP_GROUP_TITLE_MAX_LINES = 2
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun ChipGroup_Preview() {
    val chips = listOf(
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
        UiChip(
            icon = UiIcon.Image(Icons.Rounded.Fireplace),
            label = UiText.DynamicString("fireplace")
        ),
    )
    FixkinTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChipGroup(
                label = UiText.DynamicString("Chip group"),
                chips = chips
            )
        }
    }
}