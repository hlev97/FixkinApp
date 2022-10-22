package hu.bme.aut.statistics_domain.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import hu.bme.aut.statistics_data.repository.StatisticsRepository
import hu.bme.aut.statistics_domain.usecases.LoadConditionLogsUseCase
import hu.bme.aut.statistics_domain.usecases.LoadSurveyLogsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object StatisticsDomainModule {

    @Provides
    @ViewModelScoped
    fun provideLoadConditionLogsUseCase(
        repository: StatisticsRepository,
        @ApplicationContext context: Context
    ): LoadConditionLogsUseCase = LoadConditionLogsUseCase(repository, context)

    @Provides
    @ViewModelScoped
    fun provideLoadSurveyLogsUseCase(
        repository: StatisticsRepository,
        @ApplicationContext context: Context
    ): LoadSurveyLogsUseCase = LoadSurveyLogsUseCase(repository, context)

}