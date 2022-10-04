package hu.bme.aut.it9p0z.model.conditionlog

import hu.bme.aut.it9p0z.model.feeling.Feeling
import java.time.LocalDate

data class ConditionLogModel(
    val id: Int,
    val creationDate: LocalDate,
    var feeling: Feeling,
    var triggers: HashMap<String,Boolean>,
)