package hu.bme.aut.it9p0z.history_domain.usecases

import android.content.Context
import hu.bme.aut.it9p0z.history_data.repository.HistoryRepository
import hu.bme.aut.it9p0z.network.util.NetworkState.isOnline
import javax.inject.Inject

class DeleteConditionLogUseCase @Inject constructor(
    private val repository: HistoryRepository,
    private val context: Context
){

    suspend operator fun invoke(id: Int) {
        if (isOnline(context)) {
            repository.deleteLogFromRemoteDatabase(id)
            repository.deleteLogById(id)
        } else {
            repository.deleteLogById(id)
        }
    }

}