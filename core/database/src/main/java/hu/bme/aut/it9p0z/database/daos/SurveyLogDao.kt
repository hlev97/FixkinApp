package hu.bme.aut.it9p0z.database.daos

import androidx.room.*
import hu.bme.aut.it9p0z.database.entities.SurveyLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SurveyLogDao {
    @Query("SELECT * FROM survey_logs")
    fun getLogs(): Flow<List<SurveyLogEntity>>

    @Query("SELECT * FROM survey_logs WHERE id=:id")
    fun getLog(id: Int): Flow<SurveyLogEntity>

    @Query("SELECT * FROM (SELECT * FROM survey_logs ORDER BY id DESC) LIMIT 1")
    fun getLastLog(): Flow<SurveyLogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: SurveyLogEntity)

    @Delete
    suspend fun deleteLog(log: SurveyLogEntity)

    @Query("DELETE FROM survey_logs")
    suspend fun deleteAllLogs()

    @Update
    suspend fun updateLog(log: SurveyLogEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogs(logs: List<SurveyLogEntity>)

    @Query("SELECT COUNT(id) FROM survey_logs")
    fun countLogs(): Int
}