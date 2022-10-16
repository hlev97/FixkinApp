package hu.bme.aut.it9p0z.home_domain.usecases

import android.app.Application
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
    private val app: Application
) {
    private val context = app.baseContext
    suspend operator fun invoke(): List<ConditionLogModel> {
        return if (NetworkState.isOnline(context)) {
            val logsInLocalDatabase = repository.getLogsFromLocalDatabase().first()
            if (logsInLocalDatabase.isNotEmpty()) {
                logsInLocalDatabase.forEach {
                    repository.deleteLogFromLocalDatabase(it)
                }
            }
            val response = repository.getLogsFromRemoteDatabase()
            val logs = response.data!!.map { it.asConditionLogModel() }
            repository.saveLogsToLocalDatabase(logs.map { it.asConditionLogEntity() })
            repository.getLogsFromLocalDatabase().first().map { it.asConditionLogModel() }
        } else {
            repository.getLogsFromLocalDatabase().first().map { it.asConditionLogModel() }
        }
    }
}