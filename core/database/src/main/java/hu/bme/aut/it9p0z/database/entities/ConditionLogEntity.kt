package hu.bme.aut.it9p0z.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "condition_logs")
data class ConditionLogEntity(
    @PrimaryKey val id: Int,
    val creationDate: LocalDate,
    var feeling: String,
    var triggers: HashMap<String,Boolean>,
)
