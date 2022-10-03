package hu.bme.aut.it9p0z.network

import hu.bme.aut.it9p0z.network.api.FixkinApi
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.network.dtos.UserDto
import hu.bme.aut.it9p0z.network.dtos.wrapper.ResponseWrapper
import okhttp3.Credentials
import javax.inject.Inject

class NetworkDatasourceImpl @Inject constructor(
    private val api: FixkinApi
) : NetworkDatasource {

    override suspend fun createUser(user: UserDto): ResponseWrapper<UserDto> {
        return try {
            val response = api.createUser(user)
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    ResponseWrapper(
                        data = body,
                        message = "Successful request"
                    )
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            ResponseWrapper(
                data = null,
                message = "Unsuccessful request"
            )
        }
    }

    override suspend fun getUsernames(): ResponseWrapper<List<String>> {
        return try {
            val response = api.getAllUsernames()
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    ResponseWrapper(
                        data = body,
                        message = "Successful request"
                    )
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            ResponseWrapper(
                data = null,
                message = "Unsuccessful request"
            )
        }
    }

    override suspend fun getUser(userName: String, password: String): ResponseWrapper<UserDto> {
        return try {
            val credentials = Credentials.basic(userName,password)
            val response = api.getUser(credentials)
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    ResponseWrapper(
                        data = body,
                        message = "Successful request"
                    )
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            ResponseWrapper(
                data = null,
                message = "Unsuccessful request"
            )
        }
    }
}