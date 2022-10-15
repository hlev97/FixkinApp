package hu.bme.aut.it9p0z.network.api

import hu.bme.aut.it9p0z.network.dtos.ConditionLogDto
import hu.bme.aut.it9p0z.network.dtos.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FixkinApi {

    @POST("user/create")
    suspend fun createUser(user: UserDto): Response<UserDto>

    @GET("user/me")
    suspend fun getUser(
        @Header("Authorization") credentials: String
    ): Response<UserDto>

    @GET("user/all/usernames")
    suspend fun getAllUsernames(): Response<List<String>>

    @POST("skin_condition_logs")
    suspend fun createConditionLog(
        @Header("Authorization") credentials: String,
        @Body log: ConditionLogDto
    ): Response<ConditionLogDto>

    @PUT("skin_condition_logs/{scLogId}")
    suspend fun updateConditionLog(
        @Header("Authorization") credentials: String,
        @Path("scLogId") scLogId: Int,
        @Body log: ConditionLogDto
    ): Response<ConditionLogDto>

    @GET("skin_condition_logs/all/me")
    suspend fun getAllConditionLogs(
        @Header("Authorization") credentials: String,
    ): Response<List<ConditionLogDto>>

    @DELETE("skin_condition_logs/{scLogId}")
    suspend fun deleteConditionLog(
        @Header("Authorization") credentials: String,
        @Path("scLogId") scLogId: Int,
    )
}