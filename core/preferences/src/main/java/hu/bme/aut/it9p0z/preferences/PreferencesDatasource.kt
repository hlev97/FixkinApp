package hu.bme.aut.it9p0z.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import hu.bme.aut.it9p0z.preferences.domain.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "default_preferences")

class PreferencesDatasource @Inject constructor(
    private val context: Context
) {
    private val dataStore = context.dataStore

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
        val showOnboardingPrefKey = booleanPreferencesKey(name = "show_onboarding")
    }

     suspend fun saveUsername(userName: String) {
        dataStore.edit { preferences ->
            preferences[userNamePrefKey] = userName
        }
    }

     suspend fun saveFullName(fullName: String) {
        dataStore.edit { preferences ->
            preferences[fullNamePrefKey] = fullName
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
            preferences[passwordPrefKey] = password
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
                    userName = preferences[userNamePrefKey] ?: "",
                    fullName = preferences[fullNamePrefKey] ?: "",
                    height = preferences[heightPrefKey] ?: Double.NaN,
                    weight = preferences[weightPrefKey] ?: Double.NaN,
                    diseases = preferences[diseasesPrefKey]?.toList() ?: emptyList(),
                    medicines = preferences[medicinesPrefKey]?.toList() ?: emptyList(),
                    averageLifeQualityIndex = preferences[averageLifeQualityIndexPrefKey] ?: Double.NaN,
                    password = preferences[passwordPrefKey] ?: ""
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
                preferences[showAuthPrefKey] ?: false
            }
    }

     suspend fun saveShowOnboarding(showOnboarding: Boolean) {
        dataStore.edit { preferences ->
            preferences[showOnboardingPrefKey] = showOnboarding
        }
    }

     fun loadShowOnboarding(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[showOnboardingPrefKey] ?: false
            }
    }

}