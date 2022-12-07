package hu.bme.aut.conditionlog_ui.edit

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.model.toHashMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import hu.bme.aut.conditionlog_ui.R

@HiltViewModel
class EditConditionLogViewModel @Inject constructor(
    private val loadConditionLog: LoadConditionLogUseCase,
    private val updateConditionLog: UpdateConditionLogUseCase,
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state: MutableStateFlow<EditConditionLogState> =
        MutableStateFlow(EditConditionLogState.Loading)
    val state: StateFlow<EditConditionLogState?> = _state.asStateFlow()

    private val id = checkNotNull(savedStateHandle.get<Int>("id"))

    var sliderValue by mutableStateOf(0f)
        private set

    fun onSliderValueChanged(value: Float) {
        sliderValue = value
    }

    private var foodTriggerUiChips = mutableStateListOf<UiChip>()
    private var weatherTriggerUiChips = mutableStateListOf<UiChip>()
    private var mentalTriggerUiChips = mutableStateListOf<UiChip>()
    private var otherTriggerUiChips = mutableStateListOf<UiChip>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.emit(EditConditionLogState.Loading)
            _state.emit(try {
                val log = loadConditionLog(id)

                foodTriggerUiChips.addAll(log.getFoodTriggerUiChips(context))
                weatherTriggerUiChips.addAll(log.getWeatherTriggerUiChips(context))
                mentalTriggerUiChips.addAll(log.getMentalHealthTriggerUiChips(context))
                otherTriggerUiChips.addAll(log.getOtherTriggerUiChips(context))

                sliderValue = log.feeling.asFloat()
                delay(1000)
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
                    message = e.message?.let { UiText.DynamicString(it) }
                        ?: UiText.StringResource(R.string.some_error)
                )
            })
        }
    }

    fun updateClick(state: EditConditionLogState?) {
        viewModelScope.launch {
            if (state is EditConditionLogState.DataReady) {
                val log = ConditionLogModel(
                    id = state.id,
                    creationDate = state.creationDate,
                    feeling = sliderValue.asFeeling(),
                    foodTriggers = foodTriggerUiChips.toHashMap(context),
                    weatherTriggers = weatherTriggerUiChips.toHashMap(context),
                    mentalHealthTriggers = mentalTriggerUiChips.toHashMap(context),
                    otherTriggers = otherTriggerUiChips.toHashMap(context)
                )
                updateConditionLog(id = state.id, log = log)
            }
        }
    }

}