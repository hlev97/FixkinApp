package hu.bme.aut.survey_domain.usecases

import hu.bme.aut.survey_data.repository.SurveyRepository
import javax.inject.Inject

class ResetSurveyUseCase @Inject constructor(
    private val repository: SurveyRepository
) {
    suspend operator fun invoke() {
        repository.resetSurvey()
    }
}