package hu.bme.aut.it9p0z.authentication_domain.usecases

import hu.bme.aut.it9p0z.authentication_data.repository.AuthenticationRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authRepository: AuthenticationRepository
) {
    suspend operator fun invoke(userName: String, password: String): Boolean {
        authRepository.saveUsername(userName)
        authRepository.savePassword(password)
        return if(authRepository.getUser(userName,password).isSuccess) {
            authRepository.initLastDatesInPreferences()
            authRepository.hideAuthGraph()
            authRepository.deleteUserInfo()
            true
        } else false
    }
}