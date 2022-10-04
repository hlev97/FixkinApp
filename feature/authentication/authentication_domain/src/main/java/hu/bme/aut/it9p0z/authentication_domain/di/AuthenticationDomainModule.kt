package hu.bme.aut.it9p0z.authentication_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.it9p0z.authentication_data.repository.AuthenticationRepository
import hu.bme.aut.it9p0z.authentication_domain.usecases.*

@Module
@InstallIn(SingletonComponent::class)
class AuthenticationDomainModule {

    @Provides
    @ViewModelScoped
    fun provideCheckUserNameIsFreeUseCase(
        authRepository: AuthenticationRepository
    ): CheckUsernameIsFreeUseCase = CheckUsernameIsFreeUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideRegisterUserUseCase(
        authRepository: AuthenticationRepository
    ): RegisterUserUseCase = RegisterUserUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideSaveUsernameCase(
        authRepository: AuthenticationRepository
    ): SaveUsernameUseCase = SaveUsernameUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideSaveFullNameCase(
        authRepository: AuthenticationRepository
    ): SaveFullNameUseCase = SaveFullNameUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideSaveWeightUseCase(
        authRepository: AuthenticationRepository
    ): SaveWeightUseCase = SaveWeightUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideSaveHeightUseCase(
        authRepository: AuthenticationRepository
    ): SaveHeightUseCase = SaveHeightUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideSaveDiseasesUseCase(
        authRepository: AuthenticationRepository
    ): SaveDiseasesUseCase = SaveDiseasesUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideSaveMedicinesUseCase(
        authRepository: AuthenticationRepository
    ): SaveMedicinesUseCase = SaveMedicinesUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideSaveAverageLifeQualityIndexCase(
        authRepository: AuthenticationRepository
    ): SaveAverageLifeQualityIndexUseCase = SaveAverageLifeQualityIndexUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideCheckPasswordHasLowerCaseLetterUseCase(
        authRepository: AuthenticationRepository
    ): CheckPasswordHasLowerCaseLetterUseCase = CheckPasswordHasLowerCaseLetterUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideCheckPasswordHasUpperCaseLetterUseCase(
        authRepository: AuthenticationRepository
    ): CheckPasswordHasUpperCaseLetterUseCase = CheckPasswordHasUpperCaseLetterUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideCheckPasswordHasDigitUseCase(
        authRepository: AuthenticationRepository
    ): CheckPasswordHasDigitUseCase = CheckPasswordHasDigitUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideCheckPasswordHasSpecialCharUseCase(
        authRepository: AuthenticationRepository
    ): CheckPasswordHasSpecialCharUseCase = CheckPasswordHasSpecialCharUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideCheckPasswordHasCorrectLengthUseCase(
        authRepository: AuthenticationRepository
    ): CheckPasswordHasCorrectLengthUseCase = CheckPasswordHasCorrectLengthUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideCheckPasswordRequirementsUseCase(
        hasLowerCaseLetter: CheckPasswordHasLowerCaseLetterUseCase,
        hasUpperCaseLetter: CheckPasswordHasUpperCaseLetterUseCase,
        hasDigit: CheckPasswordHasDigitUseCase,
        hasSpecialChar: CheckPasswordHasSpecialCharUseCase,
        hasCorrectLength: CheckPasswordHasCorrectLengthUseCase
    ): CheckPasswordRequirementsUseCase = CheckPasswordRequirementsUseCase(
        hasLowerCaseLetter, hasUpperCaseLetter, hasDigit, hasSpecialChar, hasCorrectLength
    )
}