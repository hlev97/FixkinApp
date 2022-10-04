package hu.bme.aut.it9p0z.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asFeeling
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asString
import java.time.LocalDate

@Entity(tableName = "condition_logs")
data class ConditionLogEntity(
    @PrimaryKey val id: Int,
    val creationDate: LocalDate,
    var feeling: String,
    var triggers: HashMap<String,Boolean>,
)

fun ConditionLogEntity.asConditionLogModel(): ConditionLogModel = ConditionLogModel(
    id = id,
    creationDate = creationDate,
    feeling = feeling.asFeeling(),
    triggers = triggers
)

fun ConditionLogModel.asConditionLogEntity(): ConditionLogEntity = ConditionLogEntity(
    id = id,
    creationDate = creationDate,
    feeling = feeling.asString(),
    triggers = triggers
)