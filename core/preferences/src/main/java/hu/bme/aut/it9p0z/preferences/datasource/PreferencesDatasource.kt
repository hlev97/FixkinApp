package hu.bme.aut.it9p0z.preferences.datasource

import hu.bme.aut.it9p0z.preferences.domain.UserInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface PreferencesDatasource {
    suspend fun saveUsername(userName: String)

    suspend fun saveFullName(fullName: String)

    suspend fun saveHeight(height: Double)

    suspend fun saveWeight(weight: Double)

    suspend fun saveDiseases(diseases: List<String>)

    suspend fun saveMedicines(medicines: List<String>)

    suspend fun saveAverageLifeQualityIndex(index: Double)

    suspend fun savePassword(password: String)

    fun loadUserInfo(): Flow<UserInfo>

    suspend fun saveShowAuthentication(showAuthentication: Boolean)

    fun loadShowAuthentication(): Flow<Boolean>


    suspend fun saveLastAnswer(value: Int)

    fun loadLastAnswer(): Flow<Int>

    suspend fun addPointToSurveyResult(value: Int)

    fun loadSurveyResult(): Flow<Int>

    suspend fun resetSurvey()

    suspend fun deleteUserInfo()

    suspend fun saveLastConditionLogDate(date: LocalDate)

    fun loadLastConditionLogDate(): Flow<String>

    suspend fun saveLastSurveyLogDate(date: LocalDate)

    fun loadLastSurveyLogDate(): Flow<String>

    suspend fun resetDates()
}