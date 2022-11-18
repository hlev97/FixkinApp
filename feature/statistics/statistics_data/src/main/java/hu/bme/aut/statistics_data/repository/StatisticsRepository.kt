package hu.bme.aut.statistics_data.repository

import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.database.entities.SurveyLogEntity
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.network.dtos.ConditionLogStatisticsDto
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

    fun getNumberOfSurveyLogsInLocalDatabase(): Int {
        return databaseDatasource.getNumberOfSurveyLogs()
    }

    suspend fun deleteAllSurveyLogs() {
        databaseDatasource.deleteAllSurveyLogs()
    }

    suspend fun getStatistics(): ResponseWrapper<ConditionLogStatisticsDto> {
        val userInfo = preferencesDatasource.loadUserInfo().first()
        return networkDatasource.getConditionLogStatistics(userInfo.userName,userInfo.password)
    }
}