package hu.bme.aut.it9p0z.database.entities

import androidx.room.Entity
import java.time.LocalDate

@Entity(tableName = "survey_logs")
data class SurveyLogEntity(
    val id: Int,
    val creationDate: LocalDate,
    val result: Double,
)