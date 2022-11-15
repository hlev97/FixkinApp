package hu.bme.aut.it9p0z.history_data.repository

import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.database.entities.ConditionLogEntity
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.network.dtos.ConditionLogDto
import hu.bme.aut.it9p0z.network.dtos.wrapper.ResponseWrapper
import hu.bme.aut.it9p0z.preferences.PreferencesDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val preferencesDatasource: PreferencesDatasource,
    private val networkDatasource: NetworkDatasource,
    private val databaseDatasource: DatabaseDatasource
) {
    fun getLogsFromLocalDatabase(): Flow<List<ConditionLogEntity>> {
        return databaseDatasource.getAllConditionLogs()
    }

    suspend fun saveLogsToLocalDatabase(logs: List<ConditionLogEntity>) {
        databaseDatasource.insertConditionLogs(logs)
    }

    suspend fun deleteLogFromLocalDatabase(log: ConditionLogEntity) {
        databaseDatasource.deleteConditionLog(log)
    }

    suspend fun getLogsFromRemoteDatabase(): ResponseWrapper<List<ConditionLogDto>> {
        val userInfo = preferencesDatasource.loadUserInfo().first()
        return networkDatasource.getAllConditionLogs(userInfo.userName,userInfo.password)
    }

    suspend fun deleteLogFromRemoteDatabase(id: Int) {
        val userInfo = preferencesDatasource.loadUserInfo().first()
        networkDatasource.deleteConditionLog(userInfo.userName,userInfo.password,id)
    }

    suspend fun deleteAllLogsFromLocalDatabase() {
        databaseDatasource.deleteAllConditionLogs()
    }

    fun getLogById(id: Int): Flow<ConditionLogEntity> {
        return databaseDatasource.getConditionLog(id)
    }

    suspend fun deleteLogById(id: Int) {
        databaseDatasource.deleteConditionLogById(id)
    }

    fun getNumberOfConditionLogsInDatabase(): Int = databaseDatasource.getNumberOfConditionLogs()
}