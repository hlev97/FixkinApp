package hu.bme.aut.it9p0z.preferences.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.it9p0z.preferences.PreferencesDatasourceImpl
import hu.bme.aut.it9p0z.preferences.datasource.PreferencesDatasource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    @Singleton
    abstract fun bindPreferencesDatasource(
        preferencesDatasource: PreferencesDatasourceImpl
    ): PreferencesDatasource

}