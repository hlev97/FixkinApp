package hu.bme.aut.it9p0z.network

import android.util.Log
import hu.bme.aut.it9p0z.network.api.FixkinApi
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.network.dtos.ConditionLogDto
import hu.bme.aut.it9p0z.network.dtos.ConditionLogStatisticsDto
import hu.bme.aut.it9p0z.network.dtos.SurveyLogDto
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
            val credentials = Credentials.basic(userName, password)
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

    override suspend fun createConditionLog(
        userName: String,
        password: String,
        log: ConditionLogDto
    ): ResponseWrapper<ConditionLogDto> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.createConditionLog(credentials,log)
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

    override suspend fun updateConditionLog(
        userName: String,
        password: String,
        scLogId: Int,
        log: ConditionLogDto
    ): ResponseWrapper<ConditionLogDto> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.updateConditionLog(credentials, scLogId, log)
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

    override suspend fun getAllConditionLogs(
        userName: String,
        password: String
    ): ResponseWrapper<List<ConditionLogDto>> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.getAllConditionLogs(credentials)
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
            Log.i("request", e.message!!)
            ResponseWrapper(
                data = null,
                message = "Unsuccessful request"
            )
        }
    }

    override suspend fun deleteConditionLog(
        userName: String,
        password: String,
        scLogId: Int
    ): ResponseWrapper<ConditionLogDto> {
        return try {
            val credentials = Credentials.basic(userName, password)
            api.deleteConditionLog(credentials, scLogId)
            ResponseWrapper(
                data = null,
                message = "Successful request"
            )
        } catch (e: Exception) {
            ResponseWrapper(
                data = null,
                message = "Unsuccessful request"
            )
        }
    }

    override suspend fun getConditionLogStatistics(
        userName: String,
        password: String
    ): ResponseWrapper<ConditionLogStatisticsDto> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.getStatistics(credentials)
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
            Log.i("request", e.message!!)
            ResponseWrapper(
                data = null,
                message = "Unsuccessful request"
            )
        }
    }

    override suspend fun createSurveyLog(
        userName: String,
        password: String,
        log: SurveyLogDto
    ): ResponseWrapper<SurveyLogDto> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.createSurveyLog(credentials,log)
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

    override suspend fun getAllSurveyLogs(
        userName: String,
        password: String
    ): ResponseWrapper<List<SurveyLogDto>> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.getAllSurveyLogs(credentials)
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
            Log.i("request", e.message!!)
            ResponseWrapper(
                data = null,
                message = "Unsuccessful request"
            )
        }
    }
}