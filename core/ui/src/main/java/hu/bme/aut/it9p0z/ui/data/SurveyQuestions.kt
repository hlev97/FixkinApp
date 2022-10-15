package hu.bme.aut.it9p0z.ui.data

import hu.bme.aut.it9p0z.ui.R
import hu.bme.aut.it9p0z.ui.model.UiText

sealed class SurveyQuestion(val question: UiText, val answers: List<SurveyAnswer>) {
    object Qs1 : SurveyQuestion(
        question = UiText.StringResource(R.string.survey_question_1),
        answers = listOf(
            SurveyAnswer.VeryMuch,
            SurveyAnswer.ALot,
            SurveyAnswer.ALittle,
            SurveyAnswer.NotAtAll
        )
    )

    object Qs2 : SurveyQuestion(
        question = UiText.StringResource(R.string.survey_question_2),
        answers = listOf(
            SurveyAnswer.VeryMuch,
            SurveyAnswer.ALot,
            SurveyAnswer.ALittle,
            SurveyAnswer.NotAtAll
        )
    )

    object Qs3 : SurveyQuestion(
        question = UiText.StringResource(R.string.survey_question_3),
        answers = listOf(
            SurveyAnswer.VeryMuch,
            SurveyAnswer.ALot,
            SurveyAnswer.ALittle,
            SurveyAnswer.NotAtAll,
            SurveyAnswer.NotRelevant
        )
    )

    object Qs4 : SurveyQuestion(
        question = UiText.StringResource(R.string.survey_question_4),
        answers = listOf(
            SurveyAnswer.VeryMuch,
            SurveyAnswer.ALot,
            SurveyAnswer.ALittle,
            SurveyAnswer.NotAtAll,
            SurveyAnswer.NotRelevant
        )
    )

    object Qs5 : SurveyQuestion(
        question = UiText.StringResource(R.string.survey_question_5),
        answers = listOf(
            SurveyAnswer.VeryMuch,
            SurveyAnswer.ALot,
            SurveyAnswer.ALittle,
            SurveyAnswer.NotAtAll,
            SurveyAnswer.NotRelevant
        )
    )

    object Qs6 : SurveyQuestion(
        question = UiText.StringResource(R.string.survey_question_6),
        answers = listOf(
            SurveyAnswer.VeryMuch,
            SurveyAnswer.ALot,
            SurveyAnswer.ALittle,
            SurveyAnswer.NotAtAll,
            SurveyAnswer.NotRelevant
        )
    )

    object Qs7a : SurveyQuestion(
        question = UiText.StringResource(R.string.survey_question_7a),
        answers = listOf(SurveyAnswer.Yes, SurveyAnswer.No)
    )

    object Qs7b : SurveyQuestion(
        question = UiText.StringResource(R.string.survey_question_7b),
        answers = listOf(
            SurveyAnswer.ALot,
            SurveyAnswer.ALittle,
            SurveyAnswer.NotAtAll,
        )
    )

    object Qs8 : SurveyQuestion(
        question = UiText.StringResource(R.string.survey_question_8),
        answers = listOf(
            SurveyAnswer.VeryMuch,
            SurveyAnswer.ALot,
            SurveyAnswer.ALittle,
            SurveyAnswer.NotAtAll,
            SurveyAnswer.NotRelevant
        )
    )

    object Qs9 : SurveyQuestion(
        question = UiText.StringResource(R.string.survey_question_9),
        answers = listOf(
            SurveyAnswer.VeryMuch,
            SurveyAnswer.ALot,
            SurveyAnswer.ALittle,
            SurveyAnswer.NotAtAll,
            SurveyAnswer.NotRelevant
        )
    )

    object Qs10 : SurveyQuestion(
        question = UiText.StringResource(R.string.survey_question_10),
        answers = listOf(
            SurveyAnswer.VeryMuch,
            SurveyAnswer.ALot,
            SurveyAnswer.ALittle,
            SurveyAnswer.NotAtAll,
            SurveyAnswer.NotRelevant
        )
    )
}

sealed class SurveyAnswer(val answer: UiText, val point: Int) {
    object VeryMuch : SurveyAnswer(
        answer = UiText.StringResource(id = R.string.survey_answer_very_much),
        point = 3
    )

    object ALot : SurveyAnswer(
        answer = UiText.StringResource(id = R.string.survey_answer_lot),
        point = 2
    )

    object ALittle : SurveyAnswer(
        answer = UiText.StringResource(id = R.string.survey_answer_little),
        point = 1
    )

    object NotAtAll : SurveyAnswer(
        answer = UiText.StringResource(id = R.string.survey_answer_not),
        point = 0
    )

    object NotRelevant : SurveyAnswer(
        answer = UiText.StringResource(id = R.string.survey_answer_not_relevant),
        point = 0
    )

    object Yes : SurveyAnswer(
        answer = UiText.StringResource(id = R.string.survey_answer_yes),
        point = 3
    )

    object No : SurveyAnswer(
        answer = UiText.StringResource(id = R.string.survey_answer_no),
        point = 0
    )

}
