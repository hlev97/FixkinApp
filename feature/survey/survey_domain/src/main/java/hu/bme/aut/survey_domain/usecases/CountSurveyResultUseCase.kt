package hu.bme.aut.survey_domain.usecases

import hu.bme.aut.survey_data.repository.SurveyRepository
import hu.bme.aut.survey_domain.model.*
import javax.inject.Inject

class CountSurveyResultUseCase @Inject constructor(
    private val repository: SurveyRepository
) {
    suspend operator fun invoke(): SurveyResult {
        return when (val result = repository.loadSurveyResult()) {
            in 0..1 -> {
                NoEffect(result)
            }
            in 2..5 -> {
                SmallEffect(result)
            }
            in 6..10 -> {
                ModerateEffect(result)
            }
            in 11 .. 20 -> {
                LargeEffect(result)
            }
            else -> ExtremeEffect(result)
        }
    }
}