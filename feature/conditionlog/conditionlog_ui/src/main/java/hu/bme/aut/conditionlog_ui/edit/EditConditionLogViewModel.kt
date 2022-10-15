package hu.bme.aut.conditionlog_ui.edit

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.conditionlog_domain.usecases.LoadConditionLogUseCase
import hu.bme.aut.conditionlog_domain.usecases.UpdateConditionLogUseCase
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asFeeling
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asFloat
import hu.bme.aut.it9p0z.ui.data.getFoodTriggerUiChips
import hu.bme.aut.it9p0z.ui.data.getMentalHealthTriggerUiChips
import hu.bme.aut.it9p0z.ui.data.getOtherTriggerUiChips
import hu.bme.aut.it9p0z.ui.data.getWeatherTriggerUiChips
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiEvent
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.model.toHashMap
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditConditionLogViewModel @Inject constructor(
    private val loadConditionLog: LoadConditionLogUseCase,
    private val updateConditionLog: UpdateConditionLogUseCase,
    private val app: Application
) : AndroidViewModel(app) {

    private val _state: MutableStateFlow<EditConditionLogState> = MutableStateFlow(EditConditionLogState.Loading)
    val state: StateFlow<EditConditionLogState?> = _state

    var sliderValue by mutableStateOf(0f)
        private set

    fun onSliderValueChanged(value: Float) {
        sliderValue = value
    }

    var foodTriggerUiChips = mutableStateListOf<UiChip>()
    var weatherTriggerUiChips = mutableStateListOf<UiChip>()
    var mentalTriggerUiChips = mutableStateListOf<UiChip>()
    var otherTriggerUiChips = mutableStateListOf<UiChip>()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            _state.value = EditConditionLogState.Loading
            _state.value = try {
                val log = loadConditionLog(1)

                foodTriggerUiChips.addAll(log.getFoodTriggerUiChips(app.baseContext))
                weatherTriggerUiChips.addAll(log.getWeatherTriggerUiChips(app.baseContext))
                mentalTriggerUiChips.addAll(log.getMentalHealthTriggerUiChips(app.baseContext))
                otherTriggerUiChips.addAll(log.getOtherTriggerUiChips(app.baseContext))

                sliderValue = log.feeling.asFloat()
                delay(2000)
                EditConditionLogState.DataReady(
                    id = log.id!!,
                    creationDate = log.creationDate,
                    foodTriggerUiChips = foodTriggerUiChips,
                    weatherTriggerUiChips = weatherTriggerUiChips,
                    mentalTriggerUiChips = mentalTriggerUiChips,
                    otherTriggerUiChips = otherTriggerUiChips,
                    sliderValue = sliderValue
                )
            } catch (e: Exception) {
                EditConditionLogState.Error(
                    message = UiText.DynamicString(e.localizedMessage ?: "")
                )
            }
        }
    }

    fun updateClick(state: EditConditionLogState?) {
        viewModelScope.launch {
            if (state is EditConditionLogState.DataReady) {
                val log = ConditionLogModel(
                    id = state.id,
                    creationDate = state.creationDate,
                    feeling = sliderValue.asFeeling(),
                    foodTriggers = foodTriggerUiChips.toHashMap(app.baseContext),
                    weatherTriggers = weatherTriggerUiChips.toHashMap(app.baseContext),
                    mentalHealthTriggers = mentalTriggerUiChips.toHashMap(app.baseContext),
                    otherTriggers = otherTriggerUiChips.toHashMap(app.baseContext)
                )
                updateConditionLog(id = state.id, log = log)
            }
        }
    }

}