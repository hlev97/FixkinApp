package hu.bme.aut.conditionlog_domain.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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
        @ApplicationContext context: Context
    ): SaveConditionLogUseCase = SaveConditionLogUseCase(repository,context)

    @Provides
    @ViewModelScoped
    fun provideLoadConditionLogUseCase(
        repository: ConditionLogRepository,
    ): LoadConditionLogUseCase = LoadConditionLogUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideUpdateConditionLogUseCase(
        repository: ConditionLogRepository,
        @ApplicationContext context: Context
    ): UpdateConditionLogUseCase = UpdateConditionLogUseCase(repository,context)
}