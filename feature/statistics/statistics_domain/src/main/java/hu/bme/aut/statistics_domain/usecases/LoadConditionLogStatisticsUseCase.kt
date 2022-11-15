package hu.bme.aut.statistics_domain.usecases

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.it9p0z.database.entities.asConditionLogEntity
import hu.bme.aut.it9p0z.database.entities.asConditionLogModel
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.model.statistics.ConditionLogStatisticsModel
import hu.bme.aut.it9p0z.network.dtos.asConditionLogModel
import hu.bme.aut.it9p0z.network.dtos.asConditionLogStatisticsModel
import hu.bme.aut.it9p0z.network.util.NetworkState.isOnline
import hu.bme.aut.statistics_data.repository.StatisticsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoadConditionLogStatisticsUseCase @Inject constructor(
    private val repository: StatisticsRepository,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(): ConditionLogStatisticsModel {
        return if (isOnline(context)) {
            val stats = repository.getStatistics()
            stats.data?.asConditionLogStatisticsModel() ?: throw Exception(stats.message)
        } else throw Exception()
    }
}