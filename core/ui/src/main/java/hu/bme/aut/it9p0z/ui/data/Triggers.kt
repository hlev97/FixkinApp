package hu.bme.aut.it9p0z.ui.data

import android.content.Context
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.ui.R
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiChip.Companion.toUiChipState
import hu.bme.aut.it9p0z.ui.model.UiIcon
import hu.bme.aut.it9p0z.ui.model.UiText

sealed class UiTrigger(val name: UiText, val icon: UiIcon? = null) {

    object Wheat: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_wheat),
        icon = UiIcon.ImageResource(id = R.drawable.ic_wheat_24)
    )
    object Milk: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_milk),
        icon = UiIcon.ImageResource(id = R.drawable.ic_milk_24)
    )
    object Egg: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_egg),
        icon = UiIcon.ImageResource(id = R.drawable.ic_egg_24)
    )
    object SeaFood: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_seafood),
        icon = UiIcon.ImageResource(id = R.drawable.ic_seafood_24)
    )
    object NightShade: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_nightshade),
        icon = UiIcon.ImageResource(id = R.drawable.ic_nightshade_24)
    )
    object Soy: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_soy),
        icon = UiIcon.ImageResource(id = R.drawable.ic_soy_24)
    )
    object CitrusFruit: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_citrus),
        icon = UiIcon.ImageResource(id = R.drawable.ic_citrus_fruit_24)
    )
    object FastFood: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_fastfood),
        icon = UiIcon.ImageResource(id = R.drawable.ic_fast_food_24)
    )
    object FattyFood: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_fattyfood),
        icon = UiIcon.ImageResource(id = R.drawable.ic_fatty_food_24)
    )
    object Alcohol: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_alcohol),
        icon = UiIcon.ImageResource(id = R.drawable.ic_alcohol_24)
    )

    object Hot: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_hot),
        icon = UiIcon.ImageResource(id = R.drawable.ic_hot_24)
    )
    object Dry: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_dry),
        icon = UiIcon.ImageResource(id = R.drawable.ic_dry_24)
    )
    object Cold: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_cold),
        icon = UiIcon.ImageResource(id = R.drawable.ic_cold_24)
    )
    object Rainy: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_rainy),
        icon = UiIcon.ImageResource(id = R.drawable.ic_rainy_24)
    )
    object Windy: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_windy),
        icon = UiIcon.ImageResource(id = R.drawable.ic_windy_24)
    )
    object Snowy: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_snowy),
        icon = UiIcon.ImageResource(id = R.drawable.ic_snowy_24)

    )

    object Anxiety: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_anxiety),
        icon = UiIcon.ImageResource(id = R.drawable.ic_anxiety_24)
    )
    object Depression: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_depression),
        icon = UiIcon.ImageResource(id = R.drawable.ic_depression_24)
    )
    object Insomnia: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_insomnia),
        icon = UiIcon.ImageResource(id = R.drawable.ic_insomnia_24)
    )

    object Medicine: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_medicine),
        icon = UiIcon.ImageResource(id = R.drawable.ic_medicine_24)
    )
    object Infection: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_infection),
        icon = UiIcon.ImageResource(id = R.drawable.ic_infection_24)
    )
    object Sweat: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_sweat),
        icon = UiIcon.ImageResource(id = R.drawable.ic_sweat_24)
    )
    object Smoking: UiTrigger(
        name = UiText.StringResource(id = R.string.trigger_name_smoking),
        icon = UiIcon.ImageResource(id = R.drawable.ic_smoking_24)
    )

    companion object {

        private val foodTriggers = listOf(
            Wheat,
            Milk,
            Egg,
            SeaFood,
            NightShade,
            Soy,
            CitrusFruit,
            FastFood,
            FattyFood,
            Alcohol
        )

        val foodTriggerChips = foodTriggers.map {
            UiChip(
                label = it.name,
                icon = it.icon
            )
        }

        private val weatherTriggers = listOf(
            Hot,
            Dry,
            Cold,
            Rainy,
            Windy,
            Snowy,
        )

        val weatherTriggerChips = weatherTriggers.map {
            UiChip(
                label = it.name,
                icon = it.icon
            )
        }

        private val mentalTriggers = listOf(
            Anxiety,
            Depression,
            Insomnia
        )

        val mentalTriggerChips = mentalTriggers.map {
            UiChip(
                label = it.name,
                icon = it.icon
            )
        }

        private val otherTriggers = listOf(
            Medicine,
            Infection,
            Smoking,
            Sweat
        )

        val otherTriggerChips = otherTriggers.map {
            UiChip(
                label = it.name,
                icon = it.icon
            )
        }
    }
}

fun ConditionLogModel.getFoodTriggerUiChips(context: Context): List<UiChip> {
    val uiChips = UiTrigger.foodTriggerChips
    val triggers = this.foodTriggers.map {
        UiChip(
            icon = null,
            label = UiText.DynamicString(it.key),
            state = it.value.toUiChipState()
        )
    }
    uiChips.forEachIndexed { index, chip ->
        if (chip.label.asString(context) == triggers[index].label.asString(context)) {
            chip.state = triggers[index].state
        }
    }
    return uiChips
}

fun ConditionLogModel.getWeatherTriggerUiChips(context: Context): List<UiChip> {
    val uiChips = UiTrigger.weatherTriggerChips
    val triggers = this.weatherTriggers.map {
        UiChip(
            icon = null,
            label = UiText.DynamicString(it.key),
            state = it.value.toUiChipState()
        )
    }
    uiChips.forEachIndexed { index, chip ->
        if (chip.label.asString(context) == triggers[index].label.asString(context)) {
            chip.state = triggers[index].state
        }
    }
    return uiChips
}

fun ConditionLogModel.getMentalHealthTriggerUiChips(context: Context): List<UiChip> {
    val uiChips = UiTrigger.mentalTriggerChips
    val triggers = this.mentalHealthTriggers.map {
        UiChip(
            icon = null,
            label = UiText.DynamicString(it.key),
            state = it.value.toUiChipState()
        )
    }
    uiChips.forEachIndexed { index, chip ->
        if (chip.label.asString(context) == triggers[index].label.asString(context)) {
            chip.state = triggers[index].state
        }
    }
    return uiChips
}

fun ConditionLogModel.getOtherTriggerUiChips(context: Context): List<UiChip> {
    val uiChips = UiTrigger.otherTriggerChips
    val triggers = this.otherTriggers.map {
        UiChip(
            icon = null,
            label = UiText.DynamicString(it.key),
            state = it.value.toUiChipState()
        )
    }
    uiChips.forEachIndexed { index, chip ->
        if (chip.label.asString(context) == triggers[index].label.asString(context)) {
            chip.state = triggers[index].state
        }
    }
    return uiChips
}