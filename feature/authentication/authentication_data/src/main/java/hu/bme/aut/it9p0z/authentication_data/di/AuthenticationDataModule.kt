package hu.bme.aut.it9p0z.authentication_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.it9p0z.authentication_data.repository.AuthenticationRepository
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.preferences.PreferencesDatasource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationDataModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        networkDatasource: NetworkDatasource,
        preferencesDatasource: PreferencesDatasource
    ): AuthenticationRepository = AuthenticationRepository(networkDatasource, preferencesDatasource)
}