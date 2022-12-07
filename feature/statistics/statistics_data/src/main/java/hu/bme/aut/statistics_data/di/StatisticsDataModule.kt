package hu.bme.aut.statistics_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.preferences.datasource.PreferencesDatasource
import hu.bme.aut.statistics_data.repository.StatisticsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StatisticsDataModule {

    @Provides
    @Singleton
    fun provideStatisticsRepository(
        preferencesDatasource: PreferencesDatasource,
        networkDatasource: NetworkDatasource,
        databaseDatasource: DatabaseDatasource
    ): StatisticsRepository =
        StatisticsRepository(preferencesDatasource, networkDatasource, databaseDatasource)

}