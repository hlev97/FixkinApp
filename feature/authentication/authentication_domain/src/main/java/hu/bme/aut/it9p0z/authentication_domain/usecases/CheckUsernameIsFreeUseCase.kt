package hu.bme.aut.it9p0z.authentication_domain.usecases

import hu.bme.aut.it9p0z.authentication_data.repository.AuthenticationRepository
import javax.inject.Inject

class CheckUsernameIsFreeUseCase @Inject constructor(
    private val authRepository: AuthenticationRepository
) {
    suspend operator fun invoke(userName: String): Boolean {
        val response = authRepository.getUsernames()
        val userNames = response.getOrNull()

        if (userNames != null) {
            return !userNames.contains(userName)
        } else throw response.exceptionOrNull()!!
    }
}