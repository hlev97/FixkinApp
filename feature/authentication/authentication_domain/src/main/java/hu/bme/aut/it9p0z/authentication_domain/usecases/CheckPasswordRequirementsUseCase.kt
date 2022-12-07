package hu.bme.aut.it9p0z.authentication_domain.usecases

import javax.inject.Inject

class CheckPasswordRequirementsUseCase @Inject constructor(
    val hasLowerCaseLetter: CheckPasswordHasLowerCaseLetterUseCase,
    val hasUpperCaseLetter: CheckPasswordHasUpperCaseLetterUseCase,
    val hasDigit: CheckPasswordHasDigitUseCase,
    val hasSpecialChar: CheckPasswordHasSpecialCharUseCase,
    val hasCorrectLength: CheckPasswordHasCorrectLengthUseCase,
    val isPasswordAndConfirmPasswordSame: CheckPasswordAndConfirmPasswordIsSameUseCase
)

class CheckPasswordHasLowerCaseLetterUseCase @Inject constructor() {
    operator fun invoke(password: String): Boolean {
        return password.any { it.isLowerCase() }
    }
}

class CheckPasswordHasUpperCaseLetterUseCase @Inject constructor() {
    operator fun invoke(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }
}

class CheckPasswordHasDigitUseCase @Inject constructor() {
    operator fun invoke(password: String): Boolean {
        return password.any { it.isDigit() }
    }
}

class CheckPasswordHasSpecialCharUseCase @Inject constructor() {
    operator fun invoke(password: String): Boolean {
        return password.any { !it.isLetterOrDigit() }
    }
}

class CheckPasswordHasCorrectLengthUseCase @Inject constructor() {
    operator fun invoke(password: String): Boolean {
        return password.length in 8..15
    }
}

class CheckPasswordAndConfirmPasswordIsSameUseCase @Inject constructor() {
    operator fun invoke(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}
