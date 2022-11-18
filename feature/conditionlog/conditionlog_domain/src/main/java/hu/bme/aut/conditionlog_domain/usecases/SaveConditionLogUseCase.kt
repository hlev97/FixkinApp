package hu.bme.aut.conditionlog_domain.usecases

import android.content.Context
import hu.bme.aut.it9p0z.conditionlog_data.repository.ConditionLogRepository
import hu.bme.aut.it9p0z.database.entities.asConditionLogEntity
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.network.dtos.asConditionLogDto
import hu.bme.aut.it9p0z.network.util.NetworkState.isOnline
import java.time.LocalDate
import javax.inject.Inject

class SaveConditionLogUseCase @Inject constructor(
   private val repository: ConditionLogRepository,
   private val context: Context
) {
    suspend operator fun invoke(log: ConditionLogModel) {
        repository.saveLastConditionLogDate(LocalDate.now())
        if (isOnline(context)) {
            repository.saveLogToRemoteDatabase(log.asConditionLogDto())
        } else {
            repository.saveLogToLocalDatabase(log.asConditionLogEntity())
        }
    }
}