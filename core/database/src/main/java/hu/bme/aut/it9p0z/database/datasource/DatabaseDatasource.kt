package hu.bme.aut.it9p0z.database.datasource

import hu.bme.aut.it9p0z.database.entities.ConditionLogEntity
import hu.bme.aut.it9p0z.database.entities.SurveyLogEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseDatasource {

    fun getAllConditionLogs(): Flow<List<ConditionLogEntity>>

    fun getConditionLog(id: Int): Flow<ConditionLogEntity>

    fun getLastConditionLog(): Flow<ConditionLogEntity>

    suspend fun insertConditionLog(log: ConditionLogEntity)

    suspend fun updateConditionLog(log: ConditionLogEntity)

    suspend fun deleteConditionLog(log: ConditionLogEntity)

    suspend fun insertConditionLogs(logs: List<ConditionLogEntity>)

    suspend fun deleteAllConditionLogs()

    fun getNumberOfConditionLogs(): Int

    fun getAllSurveyLogs(): Flow<List<SurveyLogEntity>>

    fun getSurveyLog(id: Int): Flow<SurveyLogEntity>

    fun getLastSurveyLog(): Flow<SurveyLogEntity>

    suspend fun insertSurveyLog(log: SurveyLogEntity)

    suspend fun updateSurveyLog(log: SurveyLogEntity)

    suspend fun deleteSurveyLog(log: SurveyLogEntity)

    suspend fun insertSurveyLogs(logs: List<SurveyLogEntity>)

    suspend fun deleteAllSurveyLogs()

    fun getNumberOfSurveyLogs(): Int
}