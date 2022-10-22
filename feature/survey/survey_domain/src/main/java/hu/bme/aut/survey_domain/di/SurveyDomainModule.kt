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
    fun provideResetSurveyUseCase(
        repository: SurveyRepository
    ): ResetSurveyUseCase = ResetSurveyUseCase(repository)

    @Provides
    @Singleton
    fun providePrevQuestionUseCase(
        repository: SurveyRepository
    ): PrevQuestionUseCase = PrevQuestionUseCase(repository)

    @Provides
    @Singleton
    fun provideSurveyUseCases(
        addPoint: AddPointToAnswerResultUseCase,
        countResult: CountSurveyResultUseCase,
        loadLastAnswer: LoadLastAnswerUseCase,
        saveLastAnswer: SaveLastAnswerUseCase,
        saveSurveyLog: SaveSurveyLogUseCase,
        resetSurvey: ResetSurveyUseCase,
        prevQuestion: PrevQuestionUseCase
    ): SurveyUseCases =
        SurveyUseCases(
            addPoint,
            countResult,
            loadLastAnswer,
            saveLastAnswer,
            saveSurveyLog,
            resetSurvey,
            prevQuestion
        )
}