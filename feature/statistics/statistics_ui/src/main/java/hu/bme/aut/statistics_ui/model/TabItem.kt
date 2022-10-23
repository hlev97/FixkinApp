package hu.bme.aut.statistics_ui.model

import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.statistics_ui.R

sealed class TabItem(val title: UiText, val route: String) {
    object FoodTrigger: TabItem(
        title = UiText.StringResource(id = R.string.tab_label_food_trigger),
        route = "food_triggers"
    )
    object WeatherTrigger: TabItem(
        title = UiText.StringResource(id = R.string.tab_label_weather_trigger),
        route = "weather_triggers"
    )
    object MentalTrigger: TabItem(
        title = UiText.StringResource(id = R.string.tab_label_mental_trigger),
        route = "mental_triggers"
    )
    object OtherTrigger: TabItem(
        title = UiText.StringResource(id = R.string.tab_label_other_trigger),
        route = "other_triggers"
    )
    object SurveyResults: TabItem(
        title = UiText.StringResource(id = R.string.tab_label_survey_results),
        route = "survey_results"
    )
}
