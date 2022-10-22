package hu.bme.aut.survey_ui.model

import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.survey_domain.model.*
import hu.bme.aut.survey_ui.R

sealed class SurveyResultUI(val text: UiText)
data class NoEffectUi(val score: Int) :
    SurveyResultUI(text = UiText.StringResource(id = R.string.survey_result_no_effect))

data class SmallEffectUi(val score: Int) :
    SurveyResultUI(text = UiText.StringResource(id = R.string.survey_result_small_effect))

data class ModerateEffectUi(val score: Int) :
    SurveyResultUI(text = UiText.StringResource(id = R.string.survey_result_moderate_effect))

data class LargeEffectUi(val score: Int) :
    SurveyResultUI(text = UiText.StringResource(id = R.string.survey_result_large_effect))

data class ExtremeEffectUi(val score: Int) :
    SurveyResultUI(text = UiText.StringResource(id = R.string.survey_result_extreme_effect))

fun SurveyResult.asSurveyResultUi(): SurveyResultUI {
    return when(this) {
        is NoEffect -> NoEffectUi(this.score)
        is SmallEffect -> SmallEffectUi(this.score)
        is ModerateEffect -> ModerateEffectUi(this.score)
        is LargeEffect -> LargeEffectUi(this.score)
        is ExtremeEffect -> ExtremeEffectUi(this.score)
    }
}