package hu.bme.aut.survey_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.preferences.datasource.PreferencesDatasource
import hu.bme.aut.survey_data.repository.SurveyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SurveyDataModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        preferencesDatasource: PreferencesDatasource,
        networkDatasource: NetworkDatasource,
        databaseDatasource: DatabaseDatasource
    ): SurveyRepository = SurveyRepository(preferencesDatasource, databaseDatasource, networkDatasource)

}