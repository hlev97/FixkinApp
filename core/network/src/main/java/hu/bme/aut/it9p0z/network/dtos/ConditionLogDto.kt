package hu.bme.aut.it9p0z.network.dtos

import com.squareup.moshi.JsonClass
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asFeeling
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asString
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class ConditionLogDto(
    val id: Int?,
    val scLogId: Int?,
    val userName: String? = null,
    val creationDate: LocalDate,
    val feeling: String,
    val foodTriggers: Map<String,Boolean>,
    val weatherTriggers: Map<String,Boolean>,
    val mentalHealthTriggers: Map<String,Boolean>,
    val otherTriggers: Map<String,Boolean>
)

fun ConditionLogDto.asConditionLogModel(): ConditionLogModel = ConditionLogModel(
    id = scLogId,
    creationDate = creationDate,
    feeling = feeling.asFeeling(),
    foodTriggers = HashMap(foodTriggers),
    weatherTriggers = HashMap(weatherTriggers),
    mentalHealthTriggers = HashMap(mentalHealthTriggers),
    otherTriggers = HashMap(otherTriggers)
)

fun ConditionLogModel.asConditionLogDto(): ConditionLogDto = ConditionLogDto(
    id = null,
    scLogId = id,
    creationDate = creationDate,
    feeling = feeling.asString(),
    foodTriggers = foodTriggers,
    weatherTriggers = weatherTriggers,
    mentalHealthTriggers = mentalHealthTriggers,
    otherTriggers = otherTriggers
)