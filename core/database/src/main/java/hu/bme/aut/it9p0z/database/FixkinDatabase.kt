package hu.bme.aut.it9p0z.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.it9p0z.database.converters.Converters
import hu.bme.aut.it9p0z.database.daos.ConditionLogDao
import hu.bme.aut.it9p0z.database.daos.SurveyLogDao
import hu.bme.aut.it9p0z.database.entities.ConditionLogEntity
import hu.bme.aut.it9p0z.database.entities.SurveyLogEntity

@TypeConverters(Converters::class)
@Database(entities = [ConditionLogEntity::class, SurveyLogEntity::class], version = 1, exportSchema = false)
abstract class FixkinDatabase : RoomDatabase() {
    abstract fun conditionLogDao(): ConditionLogDao
    abstract fun surveyLogDao(): SurveyLogDao
}