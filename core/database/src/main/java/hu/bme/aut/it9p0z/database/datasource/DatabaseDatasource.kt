package hu.bme.aut.it9p0z.database.datasource

import hu.bme.aut.it9p0z.database.entities.ConditionLogEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseDatasource {

    fun getAllConditionLogs(): Flow<List<ConditionLogEntity>>

    fun getConditionLog(id: Int): Flow<ConditionLogEntity>

    fun getLastConditionLog(): Flow<ConditionLogEntity>

    suspend fun insertConditionLog(log: ConditionLogEntity)

    suspend fun updateConditionLog(log: ConditionLogEntity)

    suspend fun deleteConditionLog(log: ConditionLogEntity)

    suspend fun insertConditionLogs(logs: List<ConditionLogEntity>)

}