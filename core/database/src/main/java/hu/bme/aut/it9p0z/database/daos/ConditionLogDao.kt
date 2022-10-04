package hu.bme.aut.it9p0z.database.daos

import androidx.room.*
import hu.bme.aut.it9p0z.database.entities.ConditionLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConditionLogDao {
    @Query("SELECT * FROM condition_logs")
    fun getLogs(): Flow<List<ConditionLogEntity>>

    @Query("SELECT * FROM condition_logs WHERE id=:id")
    fun getLog(id: Int): Flow<ConditionLogEntity>

    @Query("SELECT * FROM (SELECT * FROM condition_logs ORDER BY id DESC) LIMIT 1")
    fun getLastLog(): Flow<ConditionLogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: ConditionLogEntity)

    @Delete
    suspend fun deleteLog(log: ConditionLogEntity)

    @Update
    suspend fun updateLog(log: ConditionLogEntity)
}