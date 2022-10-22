package hu.bme.aut.survey_domain.model

sealed interface SurveyResult
data class NoEffect(val score: Int): SurveyResult
data class SmallEffect(val score: Int): SurveyResult
data class ModerateEffect(val score: Int): SurveyResult
data class LargeEffect(val score: Int): SurveyResult
data class ExtremeEffect(val score: Int): SurveyResult
