package hu.bme.aut.it9p0z.network.dtos

import hu.bme.aut.it9p0z.model.statistics.ConditionLogStatisticsModel

data class ConditionLogStatisticsDto(
    val feelings: Map<String,Float>,
    val foodTriggers: Map<String,Float>,
    val weatherTriggers: Map<String,Float>,
    val mentalHealthTriggers: Map<String,Float>,
    val otherTriggers: Map<String,Float>
)

fun ConditionLogStatisticsDto.asConditionLogStatisticsModel(): ConditionLogStatisticsModel = ConditionLogStatisticsModel(
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