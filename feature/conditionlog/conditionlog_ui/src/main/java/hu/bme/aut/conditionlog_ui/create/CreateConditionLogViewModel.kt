package hu.bme.aut.conditionlog_ui.create

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.conditionlog_domain.usecases.SaveConditionLogUseCase
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asFeeling
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.foodTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.mentalTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.otherTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.weatherTriggerChips
import hu.bme.aut.it9p0z.ui.model.UiEvent
import hu.bme.aut.it9p0z.ui.model.toHashMap
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CreateConditionLogViewModel @Inject constructor(
    private val app: Application,
    private val saveConditionLog: SaveConditionLogUseCase
) : AndroidViewModel(app) {

    var sliderValue by mutableStateOf(0f)
        private set

    fun onSliderValueChanged(value: Float) {
        sliderValue = value
    }

    val foodTriggerUiChips = foodTriggerChips
    val weatherTriggerUiChips = weatherTriggerChips
    val mentalTriggerUiChips = mentalTriggerChips
    val otherTriggerUiChips = otherTriggerChips

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun saveClick() {
        viewModelScope.launch {
            val log = ConditionLogModel(
                creationDate = LocalDate.now(),
                feeling = sliderValue.asFeeling(),
                foodTriggers = foodTriggerUiChips.toHashMap(app.baseContext),
                weatherTriggers = weatherTriggerUiChips.toHashMap(app.baseContext),
                mentalHealthTriggers = mentalTriggerUiChips.toHashMap(app.baseContext),
                otherTriggers = otherTriggerUiChips.toHashMap(app.baseContext)
            )
            saveConditionLog(log)
        }
    }

}