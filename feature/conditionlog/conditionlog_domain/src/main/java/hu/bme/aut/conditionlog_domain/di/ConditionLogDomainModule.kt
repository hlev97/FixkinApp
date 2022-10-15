package hu.bme.aut.conditionlog_domain.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hu.bme.aut.conditionlog_domain.usecases.LoadConditionLogUseCase
import hu.bme.aut.conditionlog_domain.usecases.SaveConditionLogUseCase
import hu.bme.aut.conditionlog_domain.usecases.UpdateConditionLogUseCase
import hu.bme.aut.it9p0z.conditionlog_data.repository.ConditionLogRepository

@Module
@InstallIn(ViewModelComponent::class)
object ConditionLogDomainModule {

    @Provides
    @ViewModelScoped
    fun provideSaveConditionLogUseCase(
        repository: ConditionLogRepository,
        app: Application
    ): SaveConditionLogUseCase = SaveConditionLogUseCase(repository,app)

    @Provides
    @ViewModelScoped
    fun provideLoadConditionLogUseCase(
        repository: ConditionLogRepository,
    ): LoadConditionLogUseCase = LoadConditionLogUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideUpdateConditionLogUseCase(
        repository: ConditionLogRepository,
        app: Application
    ): UpdateConditionLogUseCase = UpdateConditionLogUseCase(repository,app)
}