package hu.bme.aut.statistics_domain.usecases

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.it9p0z.database.entities.asConditionLogEntity
import hu.bme.aut.it9p0z.database.entities.asConditionLogModel
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.network.dtos.asConditionLogModel
import hu.bme.aut.it9p0z.network.util.NetworkState.isOnline
import hu.bme.aut.statistics_data.repository.StatisticsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoadConditionLogsUseCase @Inject constructor(
    private val repository: StatisticsRepository,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(): List<ConditionLogModel> {
        return if (isOnline(context)) {
            val size = repository.getNumberOfConditionLogsInLocalDatabase()
            if (size > 0) {
                repository.deleteAllConditionLogs()
            }
            val logs = repository.getConditionLogsFromRemoteDatabase().data?.map {
                it.asConditionLogModel().asConditionLogEntity()
            } ?: emptyList()

            repository.saveConditionLogsToLocalDatabase(logs)
            repository.loadConditionLogsFromLocalDatabase().first().map { it.asConditionLogModel() }
        } else repository.loadConditionLogsFromLocalDatabase().first().map { it.asConditionLogModel() }

    }
}