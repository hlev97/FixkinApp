package hu.bme.aut.survey_ui.model

import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.survey_ui.R

sealed class SurveyAnswer(val text: UiText, val point: Int)
object VeryMuch: SurveyAnswer(
    text = UiText.StringResource(id = R.string.survey_answer_very_much),
    point = 3
)
object ALot: SurveyAnswer(
    text = UiText.StringResource(id = R.string.survey_answer_a_lot),
    point = 2
)
object ALittle: SurveyAnswer(
    text = UiText.StringResource(id = R.string.survey_answer_a_little),
    point = 1
)
object NotAtAll: SurveyAnswer(
    text = UiText.StringResource(id = R.string.survey_answer_not_at_all),
    point = 0
)
object NotRelevant: SurveyAnswer(
    text = UiText.StringResource(id = R.string.survey_answer_not_relevant),
    point = 0
)
object Yes: SurveyAnswer(
    text = UiText.StringResource(id = R.string.survey_answer_yes),
    point = 3
)
object No: SurveyAnswer(
    text = UiText.StringResource(id = R.string.survey_answer_no),
    point = 0
)
