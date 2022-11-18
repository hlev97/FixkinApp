package hu.bme.aut.it9p0z.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.it9p0z.preferences.domain.UserInfo
import kotlinx.coroutines.flow.*
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Base64
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "default_preferences"
)

class PreferencesDatasource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore
    private val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    companion object {
        val userNamePrefKey = stringPreferencesKey(name = "username")
        val fullNamePrefKey = stringPreferencesKey(name = "full name")
        val heightPrefKey = doublePreferencesKey(name = "height")
        val weightPrefKey = doublePreferencesKey(name = "weight")
        val diseasesPrefKey = stringSetPreferencesKey(name = "diseases")
        val medicinesPrefKey = stringSetPreferencesKey(name = "medicines")
        val averageLifeQualityIndexPrefKey = doublePreferencesKey(name = "average_dlqi")
        val passwordPrefKey = stringPreferencesKey(name = "password")

        val showAuthPrefKey = booleanPreferencesKey(name = "show_auth")

        val lastAnswerPrefKey = intPreferencesKey(name = "survey_last_answer")
        val surveyResultPrefKey = intPreferencesKey(name = "survey_result")

        val lastConditionLogDatePrefKey = stringPreferencesKey(name = "last_condition_log_date")
        val lastSurveyLogDatePrefKey = stringPreferencesKey(name = "last_survey_log_date")
    }

    suspend fun saveUsername(userName: String) {
        dataStore.edit { preferences ->

            preferences[userNamePrefKey] = encode(userName)
        }
    }

    suspend fun saveFullName(fullName: String) {
        dataStore.edit { preferences ->
            preferences[fullNamePrefKey] = encode(fullName)
        }
    }

    suspend fun saveHeight(height: Double) {
        dataStore.edit { preferences ->
            preferences[heightPrefKey] = height
        }
    }

    suspend fun saveWeight(weight: Double) {
        dataStore.edit { preferences ->
            preferences[weightPrefKey] = weight
        }
    }

    suspend fun saveDiseases(diseases: List<String>) {
        dataStore.edit { preferences ->
            preferences[diseasesPrefKey] = diseases.toSet()
        }
    }

    suspend fun saveMedicines(medicines: List<String>) {
        dataStore.edit { preferences ->
            preferences[medicinesPrefKey] = medicines.toSet()
        }
    }

    suspend fun saveAverageLifeQualityIndex(index: Double) {
        dataStore.edit { preferences ->
            preferences[averageLifeQualityIndexPrefKey] = index
        }
    }

    suspend fun savePassword(password: String) {
        dataStore.edit { preferences ->
            preferences[passwordPrefKey] = encode(password)
        }
    }

    fun loadUserInfo(): Flow<UserInfo> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                UserInfo(
                    userName = decode(preferences[userNamePrefKey]?: ""),
                    fullName = decode(preferences[fullNamePrefKey] ?: ""),
                    height = preferences[heightPrefKey] ?: 0.0,
                    weight = preferences[weightPrefKey] ?: 0.0,
                    diseases = preferences[diseasesPrefKey]?.toList() ?: emptyList(),
                    medicines = preferences[medicinesPrefKey]?.toList() ?: emptyList(),
                    averageLifeQualityIndex = preferences[averageLifeQualityIndexPrefKey]
                        ?: 0.0,
                    password = decode(preferences[passwordPrefKey] ?: "")
                )
            }
    }

    suspend fun saveShowAuthentication(showAuthentication: Boolean) {
        dataStore.edit { preferences ->
            preferences[showAuthPrefKey] = showAuthentication
        }
    }

    fun loadShowAuthentication(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[showAuthPrefKey] ?: true
            }
    }


    suspend fun saveLastAnswer(value: Int) {
        dataStore.edit { preferences ->
            preferences[lastAnswerPrefKey] = value
        }
    }

    fun loadLastAnswer(): Flow<Int> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[lastAnswerPrefKey] ?: -1
            }
    }

    suspend fun addPointToSurveyResult(value: Int) {
        dataStore.edit { preferences ->
            preferences[surveyResultPrefKey] = (preferences[surveyResultPrefKey] ?: 0) + value
        }
    }

    fun loadSurveyResult(): Flow<Int> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[surveyResultPrefKey] ?: -1
            }
    }

    suspend fun resetSurvey() {
        dataStore.edit { preferences ->
            preferences[lastAnswerPrefKey] = 0
            preferences[surveyResultPrefKey] = 0
        }
    }

    suspend fun deleteUserInfo() {
        dataStore.edit { preferences ->
            preferences.remove(fullNamePrefKey)
            preferences.remove(weightPrefKey)
            preferences.remove(heightPrefKey)
            preferences.remove(medicinesPrefKey)
            preferences.remove(diseasesPrefKey)
            preferences.remove(averageLifeQualityIndexPrefKey)
        }
    }

    suspend fun saveLastConditionLogDate(date: LocalDate) {
        dataStore.edit { preferences ->
            preferences[lastConditionLogDatePrefKey] = date.format(formatter)
        }
    }

    fun loadLastConditionLogDate(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[lastConditionLogDatePrefKey] ?: ""
        }
    }

    suspend fun saveLastSurveyLogDate(date: LocalDate) {
        dataStore.edit { preferences ->
            preferences[lastSurveyLogDatePrefKey] = date.format(formatter)
        }
    }

    fun loadLastSurveyLogDate(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[lastSurveyLogDatePrefKey] ?: ""
        }
    }

    private fun encode(value: String) = Base64.getEncoder().encodeToString(value.toByteArray())

    private fun decode(value: String): String = String(Base64.getDecoder().decode(value))

}