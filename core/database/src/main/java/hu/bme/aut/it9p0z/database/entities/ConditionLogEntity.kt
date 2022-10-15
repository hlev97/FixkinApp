package hu.bme.aut.it9p0z.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asFeeling
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asString
import java.time.LocalDate

@Entity(tableName = "condition_logs")
data class ConditionLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val creationDate: LocalDate,
    val feeling: String,
    val foodTriggers: HashMap<String,Boolean>,
    val weatherTriggers: HashMap<String,Boolean>,
    val mentalHealthTriggers: HashMap<String,Boolean>,
    val otherTriggers: HashMap<String,Boolean>
)

fun ConditionLogEntity.asConditionLogModel(): ConditionLogModel = ConditionLogModel(
    id = id,
    creationDate = creationDate,
    feeling = feeling.asFeeling(),
    foodTriggers = foodTriggers,
    weatherTriggers = weatherTriggers,
    mentalHealthTriggers = mentalHealthTriggers,
    otherTriggers = otherTriggers
)

fun ConditionLogModel.asConditionLogEntity(): ConditionLogEntity = ConditionLogEntity(
    id = id,
    creationDate = creationDate,
    feeling = feeling.asString(),
    foodTriggers = foodTriggers,
    weatherTriggers = weatherTriggers,
    mentalHealthTriggers = mentalHealthTriggers,
    otherTriggers = otherTriggers
)