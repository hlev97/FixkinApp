package hu.bme.aut.it9p0z.authentication_domain.usecases

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CheckPasswordRequirementsUseCaseTest {

    private lateinit var passwordChecker: CheckPasswordRequirementsUseCase
    private val password = "Mz/x89-py"

    @Before
    fun setUp() {
        passwordChecker = CheckPasswordRequirementsUseCase(
            hasCorrectLength = CheckPasswordHasCorrectLengthUseCase(),
            hasDigit = CheckPasswordHasDigitUseCase(),
            hasLowerCaseLetter = CheckPasswordHasLowerCaseLetterUseCase(),
            hasUpperCaseLetter = CheckPasswordHasUpperCaseLetterUseCase(),
            hasSpecialChar = CheckPasswordHasSpecialCharUseCase(),
            isPasswordAndConfirmPasswordSame = CheckPasswordAndConfirmPasswordIsSameUseCase()
        )
    }

    @Test
    fun `Checks if password has correct length, returns true`() {
        assertTrue(passwordChecker.hasCorrectLength(password))
    }

    @Test
    fun `Checks if password has correct length, returns false`() {
        val wrongPassword = password.substring(0,5)
        assertFalse(passwordChecker.hasCorrectLength(wrongPassword))
    }

    @Test
    fun `Checks if password has digits, returns true`() {
        assertTrue(passwordChecker.hasDigit(password))
    }

    @Test
    fun `Checks if password has digits, returns false`() {
        val noDigitsRegex = Regex("[0-9]")
        val wrongPassword = password.replace(noDigitsRegex,"")
        assertFalse(passwordChecker.hasDigit(wrongPassword))
    }

    @Test
    fun `Checks if password has lowercase letters, returns true`() {
        assertTrue(passwordChecker.hasLowerCaseLetter(password))
    }

    @Test
    fun `Checks if password has lowercase letters, returns false`() {
        val noLowercaseLettersRegex = Regex("[a-z]")
        val wrongPassword = password.replace(noLowercaseLettersRegex,"")
        assertFalse(passwordChecker.hasLowerCaseLetter(wrongPassword))
    }

    @Test
    fun `Checks if password has uppercase letters, returns true`() {
        assertTrue(passwordChecker.hasUpperCaseLetter(password))
    }

    @Test
    fun `Checks if password has uppercase letters, returns false`() {
        val noUppercaseLettersRegex = Regex("[A-Z]")
        val wrongPassword = password.replace(noUppercaseLettersRegex,"")
        assertFalse(passwordChecker.hasUpperCaseLetter(wrongPassword))
    }

    @Test
    fun `Checks if password has special char, returns true`() {
        assertTrue(passwordChecker.hasSpecialChar(password))
    }

    @Test
    fun `Checks if password has special char, returns false`() {
        val noSpecialCharsRegex = Regex("[^A-Za-z0-9]")
        val wrongPassword = password.replace(noSpecialCharsRegex,"")
        assertFalse(passwordChecker.hasSpecialChar(wrongPassword))
    }

    @Test
    fun `Checks if password matches with confirm password, returns true`() {
        val confirmPassword = password
        assertTrue(passwordChecker.isPasswordAndConfirmPasswordSame(password, confirmPassword))
    }

    @Test
    fun `Checks if password matches with confirm password, returns false`() {
        val confirmPassword = "password"
        assertFalse(passwordChecker.isPasswordAndConfirmPasswordSame(password, confirmPassword))
    }

}