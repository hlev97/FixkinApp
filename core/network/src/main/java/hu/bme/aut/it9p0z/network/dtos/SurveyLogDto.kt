package hu.bme.aut.it9p0z.network.dtos

import com.squareup.moshi.JsonClass
import hu.bme.aut.it9p0z.model.surveylog.SurveyLogModel
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class SurveyLogDto(
    val surveyLogId: Int?,
    val userName: String?,
    val creationDate: LocalDate,
    val result: Double,
)

fun SurveyLogModel.asSurveyLogDto(): SurveyLogDto = SurveyLogDto(
    surveyLogId = id,
    userName = null,
    creationDate = creationDate,
    result = result
)

fun SurveyLogDto.asSurveyLogModel(): SurveyLogModel = SurveyLogModel(
    id = surveyLogId,
    creationDate = creationDate,
    result = result
)
