package hu.bme.aut.it9p0z.network.datasource

import hu.bme.aut.it9p0z.network.dtos.UserDto
import hu.bme.aut.it9p0z.network.dtos.wrapper.ResponseWrapper

interface NetworkDatasource {

    suspend fun createUser(user: UserDto): ResponseWrapper<UserDto>

    suspend fun getUsernames(): ResponseWrapper<List<String>>

    suspend fun getUser(userName: String, password: String): ResponseWrapper<UserDto>
}