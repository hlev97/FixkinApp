package hu.bme.aut.survey_ui.model

import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.survey_ui.R

sealed class SurveyQuestion(
    val text: UiText,
    val answers: List<SurveyAnswer>
) {
    companion object {
        val questions = listOf(Qs1, Qs2, Qs3, Qs4, Qs5, Qs6, Qs7a, Qs7b, Qs8, Qs9, Qs10)
    }
}
object Qs1: SurveyQuestion(
    text = UiText.StringResource(id = R.string.survey_question_1),
    answers = listOf(VeryMuch,ALot,ALittle,NotAtAll)
)
object Qs2: SurveyQuestion(
    text = UiText.StringResource(id = R.string.survey_question_2),
    answers = listOf(VeryMuch,ALot,ALittle,NotAtAll)
)
object Qs3: SurveyQuestion(
    text = UiText.StringResource(id = R.string.survey_question_3),
    answers = listOf(VeryMuch,ALot,ALittle,NotAtAll,NotRelevant)
)
object Qs4: SurveyQuestion(
    text = UiText.StringResource(id = R.string.survey_question_4),
    answers = listOf(VeryMuch,ALot,ALittle,NotAtAll)
)
object Qs5: SurveyQuestion(
    text = UiText.StringResource(id = R.string.survey_question_5),
    answers = listOf(VeryMuch,ALot,ALittle,NotAtAll)
)
object Qs6: SurveyQuestion(
    text = UiText.StringResource(id = R.string.survey_question_6),
    answers = listOf(VeryMuch,ALot,ALittle,NotAtAll)
)
object Qs7a: SurveyQuestion(
    text = UiText.StringResource(id = R.string.survey_question_7a),
    answers = listOf(Yes,No)
)
object Qs7b: SurveyQuestion(
    text = UiText.StringResource(id = R.string.survey_question_7b),
    answers = listOf(ALot,ALittle,NotAtAll)
)
object Qs8: SurveyQuestion(
    text = UiText.StringResource(id = R.string.survey_question_8),
    answers = listOf(VeryMuch,ALot,ALittle,NotAtAll)
)
object Qs9: SurveyQuestion(
    text = UiText.StringResource(id = R.string.survey_question_9),
    answers = listOf(VeryMuch,ALot,ALittle,NotAtAll)
)
object Qs10: SurveyQuestion(
    text = UiText.StringResource(id = R.string.survey_question_10),
    answers = listOf(VeryMuch,ALot,ALittle,NotAtAll)
)
