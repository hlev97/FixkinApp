package hu.bme.aut.statistics_ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.statistics_domain.usecases.LoadConditionLogStatisticsUseCase
import hu.bme.aut.statistics_domain.usecases.LoadSurveyLogsUseCase
import hu.bme.aut.statistics_ui.model.TabItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val loadSurveyLogs: LoadSurveyLogsUseCase,
    private val loadConditionLogs: LoadConditionLogStatisticsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val graphType = checkNotNull(savedStateHandle.get<String>("graphType"))

    val tabs = listOf(
        TabItem.FoodTrigger,
        TabItem.WeatherTrigger,
        TabItem.MentalTrigger,
        TabItem.OtherTrigger,
        TabItem.SurveyResults
    )

    var selectedItem by mutableStateOf(tabs.filter { it.route == graphType }[0])
        private set

    fun onSelect(item: TabItem) {
        selectedItem = item
    }

    private val _state: MutableStateFlow<StatisticsState> = MutableStateFlow(Loading)
    val state: StateFlow<StatisticsState> = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = Loading
            _state.value = try {
                val conditionLogStats = loadConditionLogs()
                if (conditionLogStats.foodTriggers.values.any { it == 0f }) throw Exception("You haven't added enough logs for statistics.")

                val surveyLogs = loadSurveyLogs()
                if (surveyLogs.size < 4) throw Exception("You haven't added enough logs for statistics.")
                DataReady(
                    feelings = conditionLogStats.feelings,
                    foodTriggers = conditionLogStats.foodTriggers,
                    weatherTriggers = conditionLogStats.weatherTriggers,
                    mentalHealthTriggers = conditionLogStats.mentalHealthTriggers,
                    otherTriggers = conditionLogStats.otherTriggers,
                    surveyLogs = surveyLogs
                )
            } catch (e: Exception) {
                Error(e.message!!)
            }
        }
    }
}

sealed interface StatisticsState
object Loading: StatisticsState
data class DataReady(
    val feelings: HashMap<String,Float>,
    val foodTriggers: HashMap<String,Float>,
    val weatherTriggers: HashMap<String,Float>,
    val mentalHealthTriggers: HashMap<String,Float>,
    val otherTriggers: HashMap<String,Float>,
    val surveyLogs: HashMap<String,Double>
): StatisticsState
data class Error(val message: String): StatisticsState
