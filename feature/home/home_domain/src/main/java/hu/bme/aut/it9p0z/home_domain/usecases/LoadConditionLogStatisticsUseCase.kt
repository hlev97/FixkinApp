package hu.bme.aut.it9p0z.home_domain.usecases

import android.content.Context
import hu.bme.aut.it9p0z.home_data.repository.HomeRepository
import hu.bme.aut.it9p0z.model.statistics.ConditionLogStatisticsModel
import hu.bme.aut.it9p0z.network.dtos.asConditionLogStatisticsModel
import hu.bme.aut.it9p0z.network.util.NetworkState.isOnline
import javax.inject.Inject

class LoadConditionLogStatisticsUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val context: Context
) {
    suspend operator fun invoke(): ConditionLogStatisticsModel {
        return if (isOnline(context)) {
            repository.getStatistics().data!!.asConditionLogStatisticsModel()
        } else throw Exception(repository.getStatistics().message)
    }
}