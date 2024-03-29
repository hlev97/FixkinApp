package hu.bme.aut.conditionlog_domain.usecases

import android.content.Context
import hu.bme.aut.it9p0z.conditionlog_data.repository.ConditionLogRepository
import hu.bme.aut.it9p0z.database.entities.asConditionLogEntity
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.network.dtos.asConditionLogDto
import hu.bme.aut.it9p0z.network.util.NetworkState
import javax.inject.Inject

class UpdateConditionLogUseCase @Inject constructor(
    private val repository: ConditionLogRepository,
    private val context: Context
) {
    suspend operator fun invoke(log: ConditionLogModel, id: Int) {
        if (NetworkState.isOnline(context)) {
            repository.updateLogInRemoteDatabase(log.asConditionLogDto(),id)
        }
        repository.updateLogInLocalDatabase(log.asConditionLogEntity())
    }
}