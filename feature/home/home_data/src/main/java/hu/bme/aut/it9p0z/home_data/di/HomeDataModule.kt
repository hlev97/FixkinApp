package hu.bme.aut.it9p0z.home_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.home_data.repository.HomeRepository
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.preferences.datasource.PreferencesDatasource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDataModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        preferencesDatasource: PreferencesDatasource,
        networkDatasource: NetworkDatasource,
        databaseDatasource: DatabaseDatasource
    ): HomeRepository = HomeRepository(preferencesDatasource, networkDatasource, databaseDatasource)

}