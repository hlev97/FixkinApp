package hu.bme.aut.it9p0z.database.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.it9p0z.database.DatabaseDatasourceImpl
import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseDatasourceModule {

    @Binds
    abstract fun bindDatabaseDatasource(
        databaseDatasource: DatabaseDatasourceImpl
    ): DatabaseDatasource

}