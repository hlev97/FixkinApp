package hu.bme.aut.statistics_ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.futured.donut.compose.DonutProgress
import com.jaikeerthick.composable_graphs.color.LinearGraphColors
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import com.jaikeerthick.composable_graphs.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.style.LinearGraphVisibility
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.statistics_ui.model.asDonutModel
import hu.bme.aut.statistics_ui.model.getColors
import hu.bme.aut.statistics_ui.R
import hu.bme.aut.statistics_ui.common.DataOverview

@ExperimentalMaterial3Api
@Composable
fun StatisticsScreen(
    modifier: Modifier = Modifier,
    viewModel: StatisticsViewModel = hiltViewModel(),
    onTabItemClick: (String) -> Unit
) {
    val graphType = viewModel.graphType
    val state by viewModel.state.collectAsState()

    val context = LocalContext.current

    Scaffold(
        topBar = {
            val selectedItem = viewModel.selectedItem
            TabRow(
                selectedTabIndex = viewModel.tabs.indexOf(selectedItem),
            ) {
                viewModel.tabs.forEachIndexed { index, item ->
                    Tab(
                        selected = item == selectedItem,
                        onClick = {
                            viewModel.onSelect(item)
                            onTabItemClick(item.route)
                        },
                        text = {
                            Text(
                                text = item.title.asString(context),
                            )
                        }
                    )
                }
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
                is DataReady -> {
                    val data = (state as DataReady)
                    DataReadyScreen(
                        graphType = graphType,
                        data = data
                    )
                }
                is Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = (state as Error).message)
                    }
                }
            }
        }
    }


}

@Composable
fun DataReadyScreen(
    graphType: String,
    data: DataReady
) {
    when (graphType) {
        "food_triggers" -> {
            DonutGraph(
                dataMap = data.foodTriggers,
                title = UiText.StringResource(R.string.statistics_title_foodtriggers)
            )
        }
        "weather_triggers" -> {
            DonutGraph(
                dataMap = data.weatherTriggers,
                title = UiText.StringResource(R.string.statistics_title_weathertrigger)
            )
        }
        "mental_triggers" -> {
            DonutGraph(
                dataMap = data.mentalHealthTriggers,
                title = UiText.StringResource(R.string.statistics_title_mentaltriggers)
            )
        }
        "other_triggers" -> {
            DonutGraph(
                dataMap = data.otherTriggers,
                title = UiText.StringResource(R.string.statistics_title_othertriggers)
            )
        }
        "survey_results" -> {
            SurveyGraph(
                dates = data.surveyLogs.keys.toList(),
                values = data.surveyLogs.values.toList()
            )
        }
    }
}

@Composable
fun DonutGraph(
    dataMap: HashMap<String, Float>,
    title: UiText,
) {

    val model = dataMap.asDonutModel()
    val colors = model.getColors()
    val numOfSections = colors.size

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.size(width = 240.dp, height = 240.dp)
        ) {
            DonutProgress(
                model = model,
                modifier = Modifier.fillMaxSize(),
            )
            DataOverview(
                dataMap = dataMap,
                colors = colors,
                title = title
            )
        }
    }
}

@Composable
fun SurveyGraph(
    dates: List<String>,
    values: List<Double>
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        contentAlignment = Alignment.Center
    ) {
        var text by remember {
            mutableStateOf(
                UiText.StringResource(id = R.string.statistics_survey_results).asString(context)
            )
        }
        LineGraph(
            xAxisData = dates.map { GraphData.String(it) },
            yAxisData = values,
            header = {
                Text(
                    text = text,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            style = LineGraphStyle(
                visibility = LinearGraphVisibility(
                    isHeaderVisible = true,
                    isYAxisLabelVisible = false,
                    isCrossHairVisible = true
                ),
                colors = LinearGraphColors(
                    lineColor = MaterialTheme.colorScheme.primaryContainer,
                    pointColor = MaterialTheme.colorScheme.primary,
                    clickHighlightColor = MaterialTheme.colorScheme.secondary,
                    fillGradient = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                )
            ),
            onPointClicked = {
                text = UiText.StringResource(
                    R.string.statistics_survey_result,
                    listOf(it.second.toString())
                ).asString(context)
            }
        )
    }
}


