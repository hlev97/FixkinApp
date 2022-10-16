package hu.bme.aut.it9p0z.network.datasource

import hu.bme.aut.it9p0z.network.dtos.ConditionLogDto
import hu.bme.aut.it9p0z.network.dtos.ConditionLogStatisticsDto
import hu.bme.aut.it9p0z.network.dtos.UserDto
import hu.bme.aut.it9p0z.network.dtos.wrapper.ResponseWrapper

interface NetworkDatasource {

    suspend fun createUser(user: UserDto): ResponseWrapper<UserDto>

    suspend fun getUsernames(): ResponseWrapper<List<String>>

    suspend fun getUser(userName: String, password: String): ResponseWrapper<UserDto>

    suspend fun createConditionLog(
        userName: String,
        password: String,
        log: ConditionLogDto
    ): ResponseWrapper<ConditionLogDto>

    suspend fun updateConditionLog(
        userName: String,
        password: String,
        scLogId: Int,
        log: ConditionLogDto
    ): ResponseWrapper<ConditionLogDto>

    suspend fun getAllConditionLog(
        userName: String,
        password: String
    ): ResponseWrapper<List<ConditionLogDto>>

    suspend fun deleteConditionLog(
        userName: String,
        password: String,
        scLogId: Int,
    ): ResponseWrapper<ConditionLogDto>

    suspend fun getConditionLogStatistics(
        userName: String,
        password: String,
    ): ResponseWrapper<ConditionLogStatisticsDto>
}