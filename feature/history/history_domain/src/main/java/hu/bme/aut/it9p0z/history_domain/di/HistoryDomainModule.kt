package hu.bme.aut.it9p0z.history_domain.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import hu.bme.aut.it9p0z.history_data.repository.HistoryRepository
import hu.bme.aut.it9p0z.history_domain.usecases.DeleteConditionLogUseCase
import hu.bme.aut.it9p0z.history_domain.usecases.LoadConditionLogsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object HistoryDomainModule {

    @Provides
    @ViewModelScoped
    fun provideLoadConditionLogUseCase(
        repository: HistoryRepository,
        @ApplicationContext context: Context
    ): LoadConditionLogsUseCase = LoadConditionLogsUseCase(repository, context)

    @Provides
    @ViewModelScoped
    fun provideDeleteConditionLogUseCase(
        repository: HistoryRepository,
        @ApplicationContext context: Context
    ): DeleteConditionLogUseCase = DeleteConditionLogUseCase(repository, context)

}