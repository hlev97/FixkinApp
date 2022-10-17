package hu.bme.aut.it9p0z.network.dtos

import hu.bme.aut.it9p0z.model.statistics.ConditionLogStatisticsModel

data class ConditionLogStatisticsDto(
    val feelings: Map<String,Int>,
    val foodTriggers: Map<String,Int>,
    val weatherTriggers: Map<String,Int>,
    val mentalHealthTriggers: Map<String,Int>,
    val otherTriggers: Map<String,Int>
)

fun ConditionLogStatisticsDto.asConditionLogStatisticsModel(): ConditionLogStatisticsModel  = ConditionLogStatisticsModel(
    feelings = HashMap(feelings),
    foodTriggers = HashMap(foodTriggers),
    weatherTriggers = HashMap(weatherTriggers),
    mentalHealthTriggers = HashMap(mentalHealthTriggers),
    otherTriggers = HashMap(otherTriggers)
)

fun ConditionLogStatisticsModel.asConditionLogStatisticsDto(): ConditionLogStatisticsDto = ConditionLogStatisticsDto(
    feelings = feelings,
    foodTriggers = foodTriggers,
    weatherTriggers = weatherTriggers,
    mentalHealthTriggers = mentalHealthTriggers,
    otherTriggers = otherTriggers
)