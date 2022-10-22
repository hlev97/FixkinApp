package hu.bme.aut.statistics_data.repository

import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.database.entities.ConditionLogEntity
import hu.bme.aut.it9p0z.database.entities.SurveyLogEntity
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.network.dtos.ConditionLogDto
import hu.bme.aut.it9p0z.network.dtos.SurveyLogDto
import hu.bme.aut.it9p0z.network.dtos.wrapper.ResponseWrapper
import hu.bme.aut.it9p0z.preferences.PreferencesDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class StatisticsRepository @Inject constructor(
    private val preferencesDatasource: PreferencesDatasource,
    private val networkDatasource: NetworkDatasource,
    private val databaseDatasource: DatabaseDatasource
) {

    suspend fun getSurveyLogsFromRemoteDatabase(): ResponseWrapper<List<SurveyLogDto>> {
        val userInfo = preferencesDatasource.loadUserInfo().first()
        return networkDatasource.getAllSurveyLogs(userInfo.userName,userInfo.password)
    }

    suspend fun saveSurveyLogsToLocalDatabase(logs: List<SurveyLogEntity>) {
        databaseDatasource.insertSurveyLogs(logs)
    }

    fun loadSurveyLogsFromLocalDatabase(): Flow<List<SurveyLogEntity>> {
        return databaseDatasource.getAllSurveyLogs()
    }

    suspend fun getConditionLogsFromRemoteDatabase(): ResponseWrapper<List<ConditionLogDto>> {
        val userInfo = preferencesDatasource.loadUserInfo().first()
        return networkDatasource.getAllConditionLogs(userInfo.userName,userInfo.password)
    }

    suspend fun saveConditionLogsToLocalDatabase(logs: List<ConditionLogEntity>) {
        databaseDatasource.insertConditionLogs(logs)
    }

    fun loadConditionLogsFromLocalDatabase(): Flow<List<ConditionLogEntity>> {
        return databaseDatasource.getAllConditionLogs()
    }
}