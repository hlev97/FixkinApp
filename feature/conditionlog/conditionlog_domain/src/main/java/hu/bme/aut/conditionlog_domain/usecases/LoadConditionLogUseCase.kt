package hu.bme.aut.conditionlog_domain.usecases

import hu.bme.aut.it9p0z.conditionlog_data.repository.ConditionLogRepository
import hu.bme.aut.it9p0z.database.entities.asConditionLogModel
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoadConditionLogUseCase @Inject constructor(
    private val repository: ConditionLogRepository,
) {

    suspend operator fun invoke(id: Int): ConditionLogModel {
        val log = repository.getLogFromLocalDatabase(id).first()
        return log.asConditionLogModel()
    }
}