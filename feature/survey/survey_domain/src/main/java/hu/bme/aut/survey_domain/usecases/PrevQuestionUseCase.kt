package hu.bme.aut.survey_domain.usecases

import hu.bme.aut.survey_data.repository.SurveyRepository
import javax.inject.Inject

class PrevQuestionUseCase @Inject constructor(
    private val repository: SurveyRepository
) {

    suspend operator fun invoke() {
        val lastAnswer = repository.loadLastAnswer()
        repository.addPointToAnswerResult(-lastAnswer)
    }

}