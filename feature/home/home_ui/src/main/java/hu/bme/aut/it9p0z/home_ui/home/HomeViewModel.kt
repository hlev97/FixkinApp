package hu.bme.aut.it9p0z.home_ui.home

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.it9p0z.home_domain.usecases.LoadConditionLogStatisticsUseCase
import hu.bme.aut.it9p0z.home_domain.usecases.LoadConditionLogsUseCase
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.foodTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.mentalTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.otherTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.weatherTriggerChips
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadStatistics: LoadConditionLogStatisticsUseCase,
    private val loadLogs: LoadConditionLogsUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val state: StateFlow<HomeState?> = _state

    var foodTriggerUiChips = mutableStateListOf<UiChip>()
    var weatherTriggerUiChips = mutableStateListOf<UiChip>()
    var mentalTriggerUiChips = mutableStateListOf<UiChip>()
    var otherTriggerUiChips = mutableStateListOf<UiChip>()

    init {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            _state.value = try {
                val dates = loadLogs().map { it.creationDate }
                val statistics = loadStatistics()

                foodTriggerUiChips.addAll(getChips(statistics.foodTriggers, foodTriggerChips))
                weatherTriggerUiChips.addAll(getChips(statistics.weatherTriggers, weatherTriggerChips))
                mentalTriggerUiChips.addAll(getChips(statistics.mentalHealthTriggers, mentalTriggerChips))
                otherTriggerUiChips.addAll(getChips(statistics.otherTriggers, otherTriggerChips))

                HomeState.DataReady(
                    creationDates = dates,
                    foodTriggerUiChips = foodTriggerUiChips,
                    weatherTriggerUiChips = weatherTriggerUiChips,
                    mentalTriggerUiChips = mentalTriggerUiChips,
                    otherTriggerUiChips = otherTriggerUiChips
                )

            } catch (e: Exception) {
                HomeState.Error(UiText.DynamicString(e.message ?: e.stackTraceToString()))
            }
        }
    }

    private fun getChips(triggers: HashMap<String,Float>, chips: List<UiChip>): List<UiChip> {
        val triggerChips = chips
            .filter {
                triggers.filter { trigger ->
                    trigger.value != 0f
                }.containsKey(it.label.asString(context))
            }
        triggerChips.onEach { it.state = UiChip.UiChipState.SELECTED }
        return triggerChips
    }
}