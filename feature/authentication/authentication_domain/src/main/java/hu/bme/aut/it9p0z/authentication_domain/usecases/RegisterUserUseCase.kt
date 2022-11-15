package hu.bme.aut.it9p0z.authentication_domain.usecases

import hu.bme.aut.it9p0z.authentication_data.repository.AuthenticationRepository
import hu.bme.aut.it9p0z.model.userinfo.UserInfoModel
import hu.bme.aut.it9p0z.network.dtos.asUserDto
import hu.bme.aut.it9p0z.network.dtos.asUserInfoModel
import hu.bme.aut.it9p0z.preferences.domain.asUserInfoModel
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepository: AuthenticationRepository
) {
    suspend operator fun invoke(): UserInfoModel {
        val user = authRepository.loadUserInfo().asUserInfoModel().asUserDto()
        val response = authRepository.registerUser(user)
        val data = response.data

        if (data != null) {
            authRepository.hideAuthGraph()
            authRepository.deleteUserInfo()
            return data.asUserInfoModel()
        } else throw Exception(response.message)
    }
}