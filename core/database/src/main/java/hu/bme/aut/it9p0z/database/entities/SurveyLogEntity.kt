package hu.bme.aut.it9p0z.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.bme.aut.it9p0z.model.surveylog.SurveyLogModel
import java.time.LocalDate

@Entity(tableName = "survey_logs")
data class SurveyLogEntity(
    @PrimaryKey val id: Int? = null,
    val creationDate: LocalDate,
    val result: Double,
)


fun SurveyLogEntity.asSurveyLogModel(): SurveyLogModel = SurveyLogModel(
    id = id,
    creationDate = creationDate,
    result = result
)

fun SurveyLogModel.asSurveyLogEntity(): SurveyLogEntity = SurveyLogEntity(
    id = id,
    creationDate = creationDate,
    result = result
)