package hu.bme.aut.it9p0z.network.dtos

data class ConditionLogStatisticsDto(
    val feelings: HashMap<String,Int>,
    val foodTriggers: HashMap<String,Int>,
    val weatherTriggers: HashMap<String,Int>,
    val mentalHealthTriggers: HashMap<String,Int>,
    val otherTriggers: HashMap<String,Int>
)
