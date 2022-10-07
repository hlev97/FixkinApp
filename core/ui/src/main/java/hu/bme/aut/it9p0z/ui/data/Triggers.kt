package hu.bme.aut.it9p0z.ui.data

import hu.bme.aut.it9p0z.ui.R
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiIcon
import hu.bme.aut.it9p0z.ui.model.UiText

sealed class Trigger(val name: UiText, val icon: UiIcon? = null) {

    object Gluten: Trigger(name = UiText.StringResource(id = R.string.trigger_name_gluten))
    object Lactose: Trigger(name = UiText.StringResource(id = R.string.trigger_name_lactose))
    object Milk: Trigger(name = UiText.StringResource(id = R.string.trigger_name_milk))
    object Egg: Trigger(name = UiText.StringResource(id = R.string.trigger_name_egg))
    object Soy: Trigger(name = UiText.StringResource(id = R.string.trigger_name_soy))
    object NightShade: Trigger(name = UiText.StringResource(id = R.string.trigger_name_nightshade))
    object CitrusFruit: Trigger(name = UiText.StringResource(id = R.string.trigger_name_citrus))
    object FastFood: Trigger(name = UiText.StringResource(id = R.string.trigger_name_fastfood))
    object FattyFood: Trigger(name = UiText.StringResource(id = R.string.trigger_name_fattyfood))
    object Alcohol: Trigger(name = UiText.StringResource(id = R.string.trigger_name_alcohol))

    object Hot: Trigger(name = UiText.StringResource(id = R.string.trigger_name_hot))
    object Dry: Trigger(name = UiText.StringResource(id = R.string.trigger_name_dry))
    object Cold: Trigger(name = UiText.StringResource(id = R.string.trigger_name_cold))
    object Rainy: Trigger(name = UiText.StringResource(id = R.string.trigger_name_rainy))
    object Windy: Trigger(name = UiText.StringResource(id = R.string.trigger_name_windy))
    object Snowy: Trigger(name = UiText.StringResource(id = R.string.trigger_name_snowy))
    object ColdFront: Trigger(name = UiText.StringResource(id = R.string.trigger_name_coldfront))
    object WarmFront: Trigger(name = UiText.StringResource(id = R.string.trigger_name_warmfront))

    object Anxiety: Trigger(name = UiText.StringResource(id = R.string.trigger_name_anxiety))
    object Depression: Trigger(name = UiText.StringResource(id = R.string.trigger_name_depression))
    object Insomnia: Trigger(name = UiText.StringResource(id = R.string.trigger_name_insomnia))

    object Medicine: Trigger(name = UiText.StringResource(id = R.string.trigger_name_medicine))
    object Infection: Trigger(name = UiText.StringResource(id = R.string.trigger_name_infection))
    object Sweat: Trigger(name = UiText.StringResource(id = R.string.trigger_name_sweat))
    object Smoking: Trigger(name = UiText.StringResource(id = R.string.trigger_name_smoking))

    companion object {

        private val foodTriggers = listOf(
            Gluten,
            Milk,
            Lactose,
            Egg,
            Soy,
            NightShade,
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
            ColdFront,
            WarmFront
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

        val otherTriggers = listOf(
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