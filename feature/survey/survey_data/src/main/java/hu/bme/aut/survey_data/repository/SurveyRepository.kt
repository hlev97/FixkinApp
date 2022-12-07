package hu.bme.aut.survey_data.repository

import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.database.entities.SurveyLogEntity
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.network.dtos.SurveyLogDto
import hu.bme.aut.it9p0z.preferences.datasource.PreferencesDatasource
import kotlinx.coroutines.flow.first
import java.time.LocalDate
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

    suspend fun saveSurveyResultToLocalDatabase() {
        val log = SurveyLogEntity(
            id = null,
            creationDate = LocalDate.now(),
            result = preferencesDatasource.loadSurveyResult().first().toDouble()
        )
        databaseDatasource.insertSurveyLog(log)
    }

    suspend fun saveSurveyResultToRemoteDatabase(): Result<SurveyLogDto?> {
        val userInfo = preferencesDatasource.loadUserInfo().first()
        val log = SurveyLogDto(
            id = null,
            surveyLogId = null,
            userName = null,
            creationDate = LocalDate.now(),
            result = preferencesDatasource.loadSurveyResult().first().toDouble()
        )
        return networkDatasource.createSurveyLog(userInfo.userName,userInfo.password, log)
    }

    suspend fun resetSurvey() {
        preferencesDatasource.resetSurvey()
    }

    suspend fun saveLastSurveyLogDate(date: LocalDate) {
        preferencesDatasource.saveLastSurveyLogDate(date)
    }
}