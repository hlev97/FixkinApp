package hu.bme.aut.it9p0z.network

import hu.bme.aut.it9p0z.network.api.FixkinApiClient
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.network.dtos.ConditionLogDto
import hu.bme.aut.it9p0z.network.dtos.ConditionLogStatisticsDto
import hu.bme.aut.it9p0z.network.dtos.SurveyLogDto
import hu.bme.aut.it9p0z.network.dtos.UserDto
import okhttp3.Credentials
import javax.inject.Inject

class NetworkDatasourceImpl @Inject constructor(
    private val api: FixkinApiClient
) : NetworkDatasource {

    override suspend fun createUser(user: UserDto): Result<UserDto?> {
        return try {
            val response = api.createUser(user)
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.success(body)
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUsernames(): Result<List<String>?> {
        return try {
            val response = api.getAllUsernames()
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.success(body)
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUser(userName: String, password: String): Result<UserDto?> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.getUser(credentials)
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.success(body)
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createConditionLog(
        userName: String,
        password: String,
        log: ConditionLogDto
    ): Result<ConditionLogDto?> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.createConditionLog(credentials,log)
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.success(body)
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateConditionLog(
        userName: String,
        password: String,
        scLogId: Int,
        log: ConditionLogDto
    ): Result<ConditionLogDto?> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.updateConditionLog(credentials, scLogId, log)
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.success(body)
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllConditionLogs(
        userName: String,
        password: String
    ): Result<List<ConditionLogDto>?> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.getAllConditionLogs(credentials)
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.success(body)
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteConditionLog(
        userName: String,
        password: String,
        scLogId: Int
    ): Result<ConditionLogDto?> {
        return try {
            val credentials = Credentials.basic(userName, password)
            api.deleteConditionLog(credentials, scLogId)
            Result.success(null)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getConditionLogStatistics(
        userName: String,
        password: String
    ): Result<ConditionLogStatisticsDto?> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.getStatistics(credentials)
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.success(body)
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createSurveyLog(
        userName: String,
        password: String,
        log: SurveyLogDto
    ): Result<SurveyLogDto?> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.createSurveyLog(credentials,log)
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.success(body)
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllSurveyLogs(
        userName: String,
        password: String
    ): Result<List<SurveyLogDto>?> {
        return try {
            val credentials = Credentials.basic(userName, password)
            val response = api.getAllSurveyLogs(credentials)
            val body = response.body()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.success(body)
                } else throw Exception("Empty body")
            } else throw Exception(response.errorBody()?.string())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}