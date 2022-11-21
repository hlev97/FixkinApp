package hu.bme.aut.it9p0z.home_domain.usecases

import android.content.Context
import hu.bme.aut.it9p0z.database.entities.asConditionLogEntity
import hu.bme.aut.it9p0z.database.entities.asConditionLogModel
import hu.bme.aut.it9p0z.home_data.repository.HomeRepository
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.network.dtos.asConditionLogModel
import hu.bme.aut.it9p0z.network.util.NetworkState
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoadConditionLogsUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val context: Context
) {
    suspend operator fun invoke(): List<ConditionLogModel> {
        return if (NetworkState.isOnline(context)) {
            val size = repository.getNumberOfConditionLogsInDatabase()
            if (size > 0) {
                repository.deleteAllLogsFromLocalDatabase()
            }
            val logsInResponse = repository.getLogsFromRemoteDatabase().getOrNull() ?: emptyList()
            repository.saveLogsToLocalDatabase(logsInResponse.map {
                it.asConditionLogModel().asConditionLogEntity()
            })
            repository.getLogsFromLocalDatabase().first().map { it.asConditionLogModel() }
        } else {
            repository.getLogsFromLocalDatabase().first().map { it.asConditionLogModel() }
        }
    }
}