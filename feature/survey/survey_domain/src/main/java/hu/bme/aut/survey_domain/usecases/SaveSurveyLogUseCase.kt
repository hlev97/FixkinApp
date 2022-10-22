package hu.bme.aut.survey_domain.usecases

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.it9p0z.network.util.NetworkState.isOnline
import hu.bme.aut.survey_data.repository.SurveyRepository
import javax.inject.Inject

class SaveSurveyLogUseCase @Inject constructor(
    private val repository: SurveyRepository,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke() {
        if (isOnline(context)) {
            repository.saveSurveyResultToRemoteDatabase()
        } else {
            repository.saveSurveyResultToLocalDatabase()
        }
    }
}