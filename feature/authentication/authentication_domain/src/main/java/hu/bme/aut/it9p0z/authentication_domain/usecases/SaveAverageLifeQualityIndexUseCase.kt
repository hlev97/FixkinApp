package hu.bme.aut.it9p0z.authentication_domain.usecases

import hu.bme.aut.it9p0z.authentication_data.repository.AuthenticationRepository
import javax.inject.Inject

class SaveAverageLifeQualityIndexUseCase @Inject constructor(
    private val authRepository: AuthenticationRepository
) {
    suspend operator fun invoke(index: Double) {
        authRepository.saveAverageDermatologyLifeQualityIndex(index)
    }
}