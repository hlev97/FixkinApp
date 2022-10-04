package hu.bme.aut.it9p0z.authentication_domain.usecases

import hu.bme.aut.it9p0z.authentication_data.repository.AuthenticationRepository
import javax.inject.Inject

class SaveDiseasesUseCase @Inject constructor(
    private val authRepository: AuthenticationRepository
) {
    suspend operator fun invoke(diseases: List<String>) {
        authRepository.saveDiseases(diseases)
    }
}