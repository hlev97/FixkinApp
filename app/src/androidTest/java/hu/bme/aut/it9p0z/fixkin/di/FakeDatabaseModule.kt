package hu.bme.aut.it9p0z.fixkin.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import hu.bme.aut.it9p0z.database.FixkinDatabase
import hu.bme.aut.it9p0z.database.daos.ConditionLogDao
import hu.bme.aut.it9p0z.database.daos.SurveyLogDao
import hu.bme.aut.it9p0z.database.di.DatabaseModule
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class FakeDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): FixkinDatabase = Room.inMemoryDatabaseBuilder(
        context,
        FixkinDatabase::class.java,
    ).allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideConditionLogDao(
        database: FixkinDatabase
    ): ConditionLogDao = database.conditionLogDao()

    @Provides
    @Singleton
    fun provideSurveyLogDao(
        database: FixkinDatabase
    ): SurveyLogDao = database.surveyLogDao()
}