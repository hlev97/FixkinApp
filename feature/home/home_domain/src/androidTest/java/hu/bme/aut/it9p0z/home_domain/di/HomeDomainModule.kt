package hu.bme.aut.it9p0z.home_domain.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hu.bme.aut.it9p0z.home_data.repository.HomeRepository
import hu.bme.aut.it9p0z.home_domain.usecases.DeleteConditionLogUseCase
import hu.bme.aut.it9p0z.home_domain.usecases.LoadConditionLogsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object HomeDomainModule {

    @Provides
    @ViewModelScoped
    fun provideLoadConditionLogsUseCase(
        repository: HomeRepository,
        app: Application
    ): LoadConditionLogsUseCase = LoadConditionLogsUseCase(repository,app)

    @Provides
    @ViewModelScoped
    fun provideDeleteConditionLogUseCase(
        repository: HomeRepository,
        app: Application
    ): DeleteConditionLogUseCase = DeleteConditionLogUseCase(repository,app)
}