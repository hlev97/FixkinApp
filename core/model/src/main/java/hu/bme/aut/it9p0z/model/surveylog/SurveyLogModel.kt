package hu.bme.aut.it9p0z.model.surveylog

import java.time.LocalDate

data class SurveyLogModel(
    val id: Int,
    val creationDate: LocalDate,
    val result: Double,
)
