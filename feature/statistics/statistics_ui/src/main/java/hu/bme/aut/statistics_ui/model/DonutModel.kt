package hu.bme.aut.statistics_ui.model

import androidx.compose.ui.graphics.Color
import app.futured.donut.compose.data.DonutConfig
import app.futured.donut.compose.data.DonutModel
import app.futured.donut.compose.data.DonutSection
import kotlin.random.Random

fun HashMap<String,Float>.asDonutModel(
    cap: Float = this.values.sum(),
    masterProgress: Float = 1f,
    gapWidthDegrees: Float = 120f,
    gapAngleDegrees: Float = 90f,
    strokeWidth: Float = 65f,
    backgroundLineColor: Color = Color.LightGray
): DonutModel {
    return DonutModel(
        cap = cap,
        masterProgress = masterProgress,
        gapWidthDegrees = gapWidthDegrees,
        gapAngleDegrees = gapAngleDegrees,
        strokeWidth = strokeWidth,
        backgroundLineColor = backgroundLineColor,
        sections = this.asDonutSection()
    )
}

fun DonutModel.getColors(): List<Color> {
    return this.sections.map { it.color }
}

private fun HashMap<String,Float>.asDonutSection(): List<DonutSection> {
    val colors = mutableListOf(
        Color(0xFFD1A623),
        Color(0xFF6F54A0),
        Color(0xFF3F51B5),
        Color(0xFF9C4A30),
        Color(0xFF009688),
        Color(0xFF4CAF50),
        Color(0xFF555255),
        Color(0xFF3B7B83),
        Color(0xFF911D14),
        Color(0xFFC47A93),
        Color(0xFFCA5F3D)
    )
    return this.map { (_,value) ->
        DonutSection(amount = value, color = colors.getRandomElement())
    }
}

fun <E>MutableList<E>.getRandomElement(): E {
    val index = Random.nextInt(0,this.size-1)
    val picked = this[index]
    this.remove(picked)
    return picked
}

