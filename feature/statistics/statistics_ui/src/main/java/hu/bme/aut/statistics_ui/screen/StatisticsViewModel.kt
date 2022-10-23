package hu.bme.aut.statistics_ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.it9p0z.model.statistics.ConditionLogStatisticsModel
import hu.bme.aut.statistics_domain.usecases.LoadConditionLogStatisticsUseCase
import hu.bme.aut.statistics_domain.usecases.LoadSurveyLogsUseCase
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
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val graphType = checkNotNull<String>(savedStateHandle["graphType"])

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
                DataReady(
                    feelings = conditionLogStats.feelings,
                    foodTriggers = conditionLogStats.foodTriggers,
                    weatherTriggers = conditionLogStats.weatherTriggers,
                    mentalHealthTriggers = conditionLogStats.mentalHealthTriggers,
                    otherTriggers = conditionLogStats.otherTriggers,
                    surveyLogs = loadSurveyLogs()
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
