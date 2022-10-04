package hu.bme.aut.it9p0z.authentication_domain.usecases

import hu.bme.aut.it9p0z.authentication_data.repository.AuthenticationRepository
import javax.inject.Inject

class SaveMedicinesUseCase @Inject constructor(
    private val authRepository: AuthenticationRepository
) {
    suspend operator fun invoke(medicines: List<String>) {
        authRepository.saveMedicines(medicines)
    }
}