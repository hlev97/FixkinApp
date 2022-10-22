package hu.bme.aut.survey_data.repository

import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.database.entities.SurveyLogEntity
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.network.dtos.SurveyLogDto
import hu.bme.aut.it9p0z.network.dtos.wrapper.ResponseWrapper
import hu.bme.aut.it9p0z.preferences.PreferencesDatasource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SurveyRepository @Inject constructor(
    private val preferencesDatasource: PreferencesDatasource,
    private val databaseDatasource: DatabaseDatasource,
    private val networkDatasource: NetworkDatasource
) {

    suspend fun saveLastAnswer(value: Int) {
        preferencesDatasource.saveLastAnswer(value)
    }

    suspend fun loadLastAnswer(): Int {
        return preferencesDatasource.loadLastAnswer().first()
    }

    suspend fun addPointToAnswerResult(value: Int) {
        preferencesDatasource.addPointToSurveyResult(value)
    }

    suspend fun loadSurveyResult(): Int {
        return preferencesDatasource.loadSurveyResult().first()
    }

    suspend fun saveSurveyResultsToLocalDatabase(logs: List<SurveyLogEntity>) {
        databaseDatasource.insertSurveyLogs(logs)
    }

    suspend fun saveSurveyResultToLocalDatabase(log: SurveyLogEntity) {
        databaseDatasource.insertSurveyLog(log)
    }

    suspend fun deleteSurveyLogsFromLocalDatabase() {
        databaseDatasource.deleteAllSurveyLogs()
    }

    suspend fun saveSurveyResultToRemoteDatabase(log: SurveyLogDto) {
        val userInfo = preferencesDatasource.loadUserInfo()
        networkDatasource.createSurveyLog("hlev97", "password", log)
    }

    suspend fun getAllSurveyLogsFromRemoteDatabase(): ResponseWrapper<List<SurveyLogDto>> {
        val userInfo = preferencesDatasource.loadUserInfo()
        return networkDatasource.getAllSurveyLogs("hlev97", "password")
    }
}