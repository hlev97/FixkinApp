package hu.bme.aut.it9p0z.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.it9p0z.network.NetworkDatasourceImpl
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkDatasourceModule {

    @Binds
    abstract fun bindNetworkDatasource(
        networkDatasource: NetworkDatasourceImpl
    ): NetworkDatasource

}