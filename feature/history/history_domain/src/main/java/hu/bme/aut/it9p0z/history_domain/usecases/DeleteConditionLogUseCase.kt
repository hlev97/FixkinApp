package hu.bme.aut.it9p0z.history_domain.usecases

import android.app.Application
import hu.bme.aut.it9p0z.database.entities.asConditionLogEntity
import hu.bme.aut.it9p0z.history_data.repository.HistoryRepository
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.network.util.NetworkState.isOnline
import javax.inject.Inject

class DeleteConditionLogUseCase @Inject constructor(
    private val repository: HistoryRepository,
    private val app: Application
){
    private val context = app.baseContext

    suspend operator fun invoke(log: ConditionLogModel) {
        repository.deleteLogFromLocalDatabase(log.asConditionLogEntity())
        if (isOnline(context)) {
            repository.deleteLogFromRemoteDatabase(log.id!!)
        } else {
            // TODO: work manager
        }
    }

}