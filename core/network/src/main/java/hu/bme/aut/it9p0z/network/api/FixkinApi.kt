package hu.bme.aut.it9p0z.network.api

import hu.bme.aut.it9p0z.network.dtos.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface FixkinApi {

    @POST("user/create")
    suspend fun createUser(user: UserDto): Response<UserDto>

    @GET("user/me")
    suspend fun getUser(@Header("Authentication") credentials: String): Response<UserDto>

    @GET("user/all/usernames")
    suspend fun getAllUsernames(): Response<List<String>>
}