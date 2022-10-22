package hu.bme.aut.it9p0z.database

import hu.bme.aut.it9p0z.database.daos.ConditionLogDao
import hu.bme.aut.it9p0z.database.daos.SurveyLogDao
import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.database.entities.ConditionLogEntity
import hu.bme.aut.it9p0z.database.entities.SurveyLogEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseDatasourceImpl @Inject constructor(
    private val conditionLogDao: ConditionLogDao,
    private val surveyLogDao: SurveyLogDao
) : DatabaseDatasource {

    override fun getAllSurveyLogs(): Flow<List<SurveyLogEntity>> = surveyLogDao.getLogs()

    override suspend fun insertSurveyLog(log: SurveyLogEntity) {
        surveyLogDao.insertLog(log)
    }

    override suspend fun updateSurveyLog(log: SurveyLogEntity) {
        surveyLogDao.updateLog(log)
    }

    override suspend fun deleteSurveyLog(log: SurveyLogEntity) {
        surveyLogDao.deleteLog(log)
    }

    override suspend fun insertSurveyLogs(logs: List<SurveyLogEntity>) {
        surveyLogDao.insertLogs(logs)
    }

    override suspend fun deleteAllConditionLogs() {
        surveyLogDao.deleteAllLogs()
    }

    override fun getNumberOfSurveyLogs(): Int = surveyLogDao.countLogs()

    override fun getSurveyLog(id: Int): Flow<SurveyLogEntity> = surveyLogDao.getLog(id)

    override fun getLastSurveyLog(): Flow<SurveyLogEntity> = surveyLogDao.getLastLog()

    override fun getAllConditionLogs(): Flow<List<ConditionLogEntity>> = conditionLogDao.getLogs()

    override suspend fun insertConditionLog(log: ConditionLogEntity) {
        conditionLogDao.insertLog(log)
    }

    override suspend fun updateConditionLog(log: ConditionLogEntity) {
        conditionLogDao.updateLog(log)
    }

    override suspend fun deleteConditionLog(log: ConditionLogEntity) {
        conditionLogDao.deleteLog(log)
    }

    override suspend fun insertConditionLogs(logs: List<ConditionLogEntity>) {
        conditionLogDao.insertLogs(logs)
    }

    override suspend fun deleteAllSurveyLogs() {
        conditionLogDao.deleteAllLogs()
    }

    override fun getNumberOfConditionLogs(): Int = conditionLogDao.countLogs()

    override fun getConditionLog(id: Int): Flow<ConditionLogEntity> = conditionLogDao.getLog(id)

    override fun getLastConditionLog(): Flow<ConditionLogEntity> = conditionLogDao.getLastLog()

}