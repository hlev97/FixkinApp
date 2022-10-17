package hu.bme.aut.it9p0z.history_domain.usecases

import android.app.Application
import hu.bme.aut.it9p0z.database.entities.asConditionLogModel
import hu.bme.aut.it9p0z.history_data.repository.HistoryRepository
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.network.dtos.asConditionLogModel
import hu.bme.aut.it9p0z.network.util.NetworkState.isOnline
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoadConditionLogsUseCase @Inject constructor(
    private val repository: HistoryRepository,
    private val app: Application
) {
    private val context = app.baseContext

    suspend operator fun invoke(): List<ConditionLogModel> {
        return if (isOnline(context)) {
            val localData = repository.getLogsFromLocalDatabase().first()
            if (localData.isNotEmpty()) {
                repository.deleteAllLogsFromLocalDatabase()
            }
            val logs = repository.getLogsFromRemoteDatabase().data ?: emptyList()
            logs.map { it.asConditionLogModel() }
        } else {
            repository.getLogsFromLocalDatabase().first().map { it.asConditionLogModel() }
        }
    }
}