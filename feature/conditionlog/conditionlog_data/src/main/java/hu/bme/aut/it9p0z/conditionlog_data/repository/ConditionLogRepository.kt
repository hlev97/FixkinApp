package hu.bme.aut.it9p0z.conditionlog_data.repository

import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.database.entities.ConditionLogEntity
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.network.dtos.ConditionLogDto
import hu.bme.aut.it9p0z.preferences.datasource.PreferencesDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import javax.inject.Inject

class ConditionLogRepository @Inject constructor(
    private val preferencesDatasource: PreferencesDatasource,
    private val networkDatasource: NetworkDatasource,
    private val databaseDatasource: DatabaseDatasource
) {

    fun getLogFromLocalDatabase(id: Int): Flow<ConditionLogEntity> {
        return databaseDatasource.getConditionLog(id)
    }

    suspend fun saveLogToLocalDatabase(log: ConditionLogEntity) {
        databaseDatasource.insertConditionLog(log)
    }

    suspend fun updateLogInLocalDatabase(log: ConditionLogEntity) {
        databaseDatasource.updateConditionLog(log)
    }

    suspend fun saveLogToRemoteDatabase(log: ConditionLogDto): Result<ConditionLogDto?> {
        val userInfo = preferencesDatasource.loadUserInfo().first()
        return networkDatasource.createConditionLog(
            userName = userInfo.userName,
            password = userInfo.password,
            log = log
        )
    }

    suspend fun updateLogInRemoteDatabase(log: ConditionLogDto, id: Int): Result<ConditionLogDto?> {
        val userInfo = preferencesDatasource.loadUserInfo().first()
        return networkDatasource.updateConditionLog(userInfo.userName,userInfo.password,id,log)
    }

    suspend fun saveLastConditionLogDate(date: LocalDate) {
        preferencesDatasource.saveLastConditionLogDate(date)
    }

}