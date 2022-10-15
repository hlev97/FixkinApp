package hu.bme.aut.it9p0z.model.conditionlog

import hu.bme.aut.it9p0z.model.feeling.Feeling
import java.time.LocalDate

data class ConditionLogModel(
    val id: Int? = null,
    val creationDate: LocalDate,
    val feeling: Feeling,
    val foodTriggers: HashMap<String,Boolean>,
    val weatherTriggers: HashMap<String,Boolean>,
    val mentalHealthTriggers: HashMap<String,Boolean>,
    val otherTriggers: HashMap<String,Boolean>
)