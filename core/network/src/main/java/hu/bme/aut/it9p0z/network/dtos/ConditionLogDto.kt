package hu.bme.aut.it9p0z.network.dtos

import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asFeeling
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asString
import java.time.LocalDate

data class ConditionLogDto(
    val scLogId: Int?,
    val userName: String? = null,
    val creationDate: LocalDate,
    val feeling: String,
    val foodTriggers: HashMap<String,Boolean>,
    val weatherTriggers: HashMap<String,Boolean>,
    val mentalHealthTriggers: HashMap<String,Boolean>,
    val otherTriggers: HashMap<String,Boolean>
)

fun ConditionLogDto.asConditionLogModel(): ConditionLogModel = ConditionLogModel(
    id = scLogId,
    creationDate = creationDate,
    feeling = feeling.asFeeling(),
    foodTriggers = foodTriggers,
    weatherTriggers = weatherTriggers,
    mentalHealthTriggers = mentalHealthTriggers,
    otherTriggers = otherTriggers
)

fun ConditionLogModel.asConditionLogDto(): ConditionLogDto = ConditionLogDto(
    scLogId = id,
    creationDate = creationDate,
    feeling = feeling.asString(),
    foodTriggers = foodTriggers,
    weatherTriggers = weatherTriggers,
    mentalHealthTriggers = mentalHealthTriggers,
    otherTriggers = otherTriggers
)