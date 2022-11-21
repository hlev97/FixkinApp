package hu.bme.aut.it9p0z.network.datasource

import hu.bme.aut.it9p0z.network.dtos.ConditionLogDto
import hu.bme.aut.it9p0z.network.dtos.ConditionLogStatisticsDto
import hu.bme.aut.it9p0z.network.dtos.SurveyLogDto
import hu.bme.aut.it9p0z.network.dtos.UserDto

interface NetworkDatasource {

    suspend fun createUser(user: UserDto): Result<UserDto?>

    suspend fun getUsernames(): Result<List<String>?>

    suspend fun getUser(userName: String, password: String): Result<UserDto?>

    suspend fun createConditionLog(
        userName: String,
        password: String,
        log: ConditionLogDto
    ): Result<ConditionLogDto?>

    suspend fun updateConditionLog(
        userName: String,
        password: String,
        scLogId: Int,
        log: ConditionLogDto
    ): Result<ConditionLogDto?>

    suspend fun getAllConditionLogs(
        userName: String,
        password: String
    ): Result<List<ConditionLogDto>?>

    suspend fun deleteConditionLog(
        userName: String,
        password: String,
        scLogId: Int,
    ): Result<ConditionLogDto?>

    suspend fun getConditionLogStatistics(
        userName: String,
        password: String,
    ): Result<ConditionLogStatisticsDto?>

    suspend fun createSurveyLog(
        userName: String,
        password: String,
        log: SurveyLogDto
    ): Result<SurveyLogDto?>

    suspend fun getAllSurveyLogs(
        userName: String,
        password: String,
    ): Result<List<SurveyLogDto>?>
}