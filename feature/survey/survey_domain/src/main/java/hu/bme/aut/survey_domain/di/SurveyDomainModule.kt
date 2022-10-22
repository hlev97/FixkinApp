package hu.bme.aut.survey_domain.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.survey_data.repository.SurveyRepository
import hu.bme.aut.survey_domain.usecases.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SurveyDomainModule {

    @Provides
    @Singleton
    fun provideAddPointToAnswerResultUseCase(
        repository: SurveyRepository
    ): AddPointToAnswerResultUseCase = AddPointToAnswerResultUseCase(repository)

    @Provides
    @Singleton
    fun provideCountSurveyResultUseCase(
        repository: SurveyRepository
    ): CountSurveyResultUseCase = CountSurveyResultUseCase(repository)

    @Provides
    @Singleton
    fun provideLoadLastAnswerUseCase(
        repository: SurveyRepository
    ): LoadLastAnswerUseCase = LoadLastAnswerUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveLastAnswerUseCase(
        repository: SurveyRepository
    ): SaveLastAnswerUseCase = SaveLastAnswerUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveSurveyLogUseCase(
        repository: SurveyRepository,
        @ApplicationContext context: Context
    ): SaveSurveyLogUseCase = SaveSurveyLogUseCase(repository, context)

    @Provides
    @Singleton
    fun provideSurveyUseCases(
        addPoint: AddPointToAnswerResultUseCase,
        countResult: CountSurveyResultUseCase,
        loadLastAnswer: LoadLastAnswerUseCase,
        saveLastAnswer: SaveLastAnswerUseCase,
        saveSurveyLog: SaveSurveyLogUseCase
    ): SurveyUseCases =
        SurveyUseCases(addPoint, countResult, loadLastAnswer, saveLastAnswer, saveSurveyLog)
}