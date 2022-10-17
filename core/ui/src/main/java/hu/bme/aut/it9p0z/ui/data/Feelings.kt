package hu.bme.aut.it9p0z.ui.data

import hu.bme.aut.it9p0z.model.feeling.Feeling
import hu.bme.aut.it9p0z.ui.R
import hu.bme.aut.it9p0z.ui.model.UiIcon
import hu.bme.aut.it9p0z.ui.model.UiText

sealed class FeelingUi(val name: UiText, val icon: UiIcon?) {
    object Sad: FeelingUi(
        name = UiText.StringResource(id = R.string.feeling_name_sad),
        icon = UiIcon.ImageResource(id = R.drawable.ic_sad_24)
    )
    object Unhappy: FeelingUi(
        name = UiText.StringResource(id = R.string.feeling_name_unhappy),
        icon = UiIcon.ImageResource(id = R.drawable.ic_unhappy_24)
    )
    object Neutral: FeelingUi(
        name = UiText.StringResource(id = R.string.feeling_name_neutral),
        icon = UiIcon.ImageResource(id = R.drawable.ic_neutral_24)
    )
    object Happy: FeelingUi(
        name = UiText.StringResource(id = R.string.feeling_name_happy),
        icon = UiIcon.ImageResource(id = R.drawable.ic_happy_24)
    )
    object Joyful: FeelingUi(
        name = UiText.StringResource(id = R.string.feeling_name_joyful),
        icon = UiIcon.ImageResource(id = R.drawable.ic_joyful_24)
    )

    companion object {
        val feelings = listOf(Sad,Unhappy,Neutral,Happy,Joyful)
    }
}

fun Feeling.asFeelingUi(): FeelingUi {
    return when(this) {
        is Feeling.Sad -> FeelingUi.Sad
        is Feeling.Unhappy -> FeelingUi.Unhappy
        is Feeling.Neutral -> FeelingUi.Neutral
        is Feeling.Happy -> FeelingUi.Happy
        is Feeling.Joyful -> FeelingUi.Joyful
    }
}