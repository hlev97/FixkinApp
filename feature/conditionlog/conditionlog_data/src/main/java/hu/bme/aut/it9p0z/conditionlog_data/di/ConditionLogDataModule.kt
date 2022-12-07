package hu.bme.aut.it9p0z.conditionlog_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.it9p0z.conditionlog_data.repository.ConditionLogRepository
import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.preferences.datasource.PreferencesDatasource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConditionLogDataModule {

    @Provides
    @Singleton
    fun provideConditionLogRepository(
        preferencesDatasource: PreferencesDatasource,
        networkDatasource: NetworkDatasource,
        databaseDatasource: DatabaseDatasource
    ): ConditionLogRepository = ConditionLogRepository(
        preferencesDatasource,networkDatasource,databaseDatasource
    )

}