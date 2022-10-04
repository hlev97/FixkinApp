package hu.bme.aut.it9p0z.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.it9p0z.database.FixkinDatabase
import hu.bme.aut.it9p0z.database.daos.ConditionLogDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): FixkinDatabase = Room.databaseBuilder(
        context,
        FixkinDatabase::class.java,
        "fixkin_db"
    ).build()

    @Provides
    @Singleton
    fun provideArticleDao(
        db: FixkinDatabase
    ): ConditionLogDao = db.conditionLogDao()
}