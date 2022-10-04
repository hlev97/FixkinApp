package hu.bme.aut.it9p0z.network.dtos

import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import java.time.LocalDate

data class ConditionLogDto(
    val scLogId: Int,
    val userName: String? = null,
    val creationDate: LocalDate,
    var feeling: String,
    var triggers: HashMap<String,Boolean>,
)

fun ConditionLogDto.asConditionLogModel(): ConditionLogModel = ConditionLogModel(
    id = scLogId,
    creationDate = creationDate,
    feeling = feeling,
    triggers = triggers
)