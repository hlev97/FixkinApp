package hu.bme.aut.it9p0z.history_domain.usecases

import android.app.Application
import android.util.Log
import hu.bme.aut.it9p0z.database.entities.asConditionLogEntity
import hu.bme.aut.it9p0z.history_data.repository.HistoryRepository
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.network.util.NetworkState.isOnline
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeleteConditionLogUseCase @Inject constructor(
    private val repository: HistoryRepository,
    private val app: Application
){
    private val context = app.baseContext

    suspend operator fun invoke(id: Int) {
        if (isOnline(context)) {
            repository.deleteLogFromRemoteDatabase(id)
            repository.deleteLogById(id)
        } else {
            repository.deleteLogById(id)
        }
    }

}