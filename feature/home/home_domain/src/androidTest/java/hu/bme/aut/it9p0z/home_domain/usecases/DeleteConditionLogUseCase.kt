package hu.bme.aut.it9p0z.home_domain.usecases

import android.app.Application
import hu.bme.aut.it9p0z.database.entities.asConditionLogEntity
import hu.bme.aut.it9p0z.home_data.repository.HomeRepository
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.network.util.NetworkState.isOnline
import javax.inject.Inject

class DeleteConditionLogUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val app: Application
) {
    private val context = app.baseContext
    suspend operator fun invoke(log: ConditionLogModel) {
        repository.deleteLogFromLocalDatabase(log.asConditionLogEntity())
        if (isOnline(context)) {
            repository.deleteConditionLogFromRemoteDatabase(log.id!!)
        }
    }
}