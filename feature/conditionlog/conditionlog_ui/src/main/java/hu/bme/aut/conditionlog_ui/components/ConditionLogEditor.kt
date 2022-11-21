package hu.bme.aut.conditionlog_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import hu.bme.aut.conditionlog_ui.R
import hu.bme.aut.it9p0z.ui.components.chipgroup.ChipGroup
import hu.bme.aut.it9p0z.ui.components.feelingslider.FeelingSlider
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.theme.largeFabHeight

@ExperimentalMaterial3Api
@Composable
fun ConditionLogEditor(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    foodTriggerChips: List<UiChip>,
    weatherTriggerChips: List<UiChip>,
    mentalTriggerChips: List<UiChip>,
    otherTriggerChips: List<UiChip>,
    sliderValue: Float,
    onSliderValueChange: (Float) -> Unit,
) {
    val scrollState = rememberScrollState(0)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FeelingSlider(
            sliderValue = sliderValue,
            enabled = enabled,
            onValueChange = onSliderValueChange,
        )
        ChipGroup(
            label = UiText.StringResource(R.string.chip_group_label_food_allergenes),
            chips = foodTriggerChips,
            enabled = enabled
        )
        ChipGroup(
            label = UiText.StringResource(R.string.chip_group_label_weather_conditions),
            chips = weatherTriggerChips,
            enabled = enabled
        )
        ChipGroup(
            label = UiText.StringResource(R.string.chip_group_label_mental_health_triggers),
            chips = mentalTriggerChips,
            enabled = enabled
        )
        ChipGroup(
            label = UiText.StringResource(R.string.chip_group_label_other_triggers),
            chips = otherTriggerChips,
            enabled = enabled
        )
        Spacer(
            modifier = Modifier.height(largeFabHeight * 3/4)
        )
    }
}