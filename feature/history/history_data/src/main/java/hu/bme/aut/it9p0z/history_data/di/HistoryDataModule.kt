package hu.bme.aut.it9p0z.history_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.history_data.repository.HistoryRepository
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.preferences.datasource.PreferencesDatasource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HistoryDataModule {

    @Provides
    @Singleton
    fun provideHistoryRepository(
        preferencesDatasource: PreferencesDatasource,
        databaseDatasource: DatabaseDatasource,
        networkDatasource: NetworkDatasource
    ): HistoryRepository =
        HistoryRepository(preferencesDatasource, networkDatasource, databaseDatasource)

}