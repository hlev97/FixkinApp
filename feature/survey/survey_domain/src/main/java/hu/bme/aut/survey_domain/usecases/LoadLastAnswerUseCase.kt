package hu.bme.aut.survey_domain.usecases

import hu.bme.aut.survey_data.repository.SurveyRepository
import javax.inject.Inject

class LoadLastAnswerUseCase @Inject constructor(
    private val repository: SurveyRepository
) {

    suspend operator fun invoke(): Int {
        return repository.loadLastAnswer()
    }

}