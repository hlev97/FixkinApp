package hu.bme.aut.it9p0z.database

import hu.bme.aut.it9p0z.database.daos.ConditionLogDao
import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.database.entities.ConditionLogEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseDatasourceImpl @Inject constructor(
    private val conditionLogDao: ConditionLogDao,
) : DatabaseDatasource {

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

    override fun getConditionLog(id: Int): Flow<ConditionLogEntity> = conditionLogDao.getLog(id)

    override fun getLastConditionLog(): Flow<ConditionLogEntity> = conditionLogDao.getLastLog()

}