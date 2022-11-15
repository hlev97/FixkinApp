package hu.bme.aut.it9p0z.authentication_data.repository

import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.network.dtos.UserDto
import hu.bme.aut.it9p0z.network.dtos.wrapper.ResponseWrapper
import hu.bme.aut.it9p0z.preferences.PreferencesDatasource
import hu.bme.aut.it9p0z.preferences.domain.UserInfo
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val networkDatasource: NetworkDatasource,
    private val preferencesDatasource: PreferencesDatasource
) {
    suspend fun saveUsername(userName: String) {
        preferencesDatasource.saveUsername(userName)
    }

    suspend fun saveFullName(fullName: String) {
        preferencesDatasource.saveFullName(fullName)
    }

    suspend fun savePassword(password: String) {
        preferencesDatasource.savePassword(password)
    }

    suspend fun saveWeight(weight: Double) {
        preferencesDatasource.saveWeight(weight)
    }

    suspend fun saveHeight(height: Double) {
        preferencesDatasource.saveHeight(height)
    }

    suspend fun saveDiseases(diseases: List<String>) {
        preferencesDatasource.saveDiseases(diseases)
    }

    suspend fun saveMedicines(medicines: List<String>) {
        preferencesDatasource.saveMedicines(medicines)
    }

    suspend fun saveAverageDermatologyLifeQualityIndex(avgIndex: Double) {
        preferencesDatasource.saveAverageLifeQualityIndex(avgIndex)
    }

    suspend fun loadUserInfo(): UserInfo {
        return preferencesDatasource.loadUserInfo().first()
    }

    suspend fun registerUser(user: UserDto): ResponseWrapper<UserDto> {
        return networkDatasource.createUser(user)
    }

    suspend fun getUser(userName: String, password: String): ResponseWrapper<UserDto> {
        return networkDatasource.getUser(userName, password)
    }

    suspend fun getUsernames(): ResponseWrapper<List<String>> {
        return networkDatasource.getUsernames()
    }

    suspend fun hideAuthGraph() {
        preferencesDatasource.saveShowAuthentication(false)
    }

    suspend fun deleteUserInfo() {
        preferencesDatasource.deleteUserInfo()
    }
}