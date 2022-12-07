package hu.bme.aut.statistics_domain.usecases

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.it9p0z.database.converters.Converters.asString
import hu.bme.aut.it9p0z.database.entities.asSurveyLogEntity
import hu.bme.aut.it9p0z.database.entities.asSurveyLogModel
import hu.bme.aut.it9p0z.model.surveylog.SurveyLogModel
import hu.bme.aut.it9p0z.network.dtos.asSurveyLogModel
import hu.bme.aut.it9p0z.network.util.NetworkState.isOnline
import hu.bme.aut.statistics_data.repository.StatisticsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoadSurveyLogsUseCase @Inject constructor(
    private val repository: StatisticsRepository,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(): HashMap<String, Double> {
        return if (isOnline(context)) {
            val size = repository.getNumberOfSurveyLogsInLocalDatabase()
            if (size > 0) {
                repository.deleteAllSurveyLogs()
            }
            val response = repository.getSurveyLogsFromRemoteDatabase()
            if (response.isSuccess) {
                val logs = response.getOrNull()?.map {
                    it.asSurveyLogModel().asSurveyLogEntity()
                } ?: emptyList()

                repository.saveSurveyLogsToLocalDatabase(logs)
                repository.loadSurveyLogsFromLocalDatabase().first().map { it.asSurveyLogModel() }
                    .asSurveyLogsDataMap()
            } else throw response.exceptionOrNull()!!
        } else {
            repository.loadSurveyLogsFromLocalDatabase().first().map { it.asSurveyLogModel() }
                .asSurveyLogsDataMap()
        }
    }
}

private fun List<SurveyLogModel>.asSurveyLogsDataMap(): HashMap<String, Double> {
    val dataSet = hashMapOf<String, Double>()
    this.forEach { log ->
        dataSet[log.creationDate.asString()] = log.result
    }
    return dataSet
}