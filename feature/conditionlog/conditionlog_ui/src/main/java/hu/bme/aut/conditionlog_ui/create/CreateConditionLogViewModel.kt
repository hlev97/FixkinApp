package hu.bme.aut.conditionlog_ui.create

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.conditionlog_domain.usecases.SaveConditionLogUseCase
import hu.bme.aut.it9p0z.model.conditionlog.ConditionLogModel
import hu.bme.aut.it9p0z.model.feeling.Feeling.Companion.asFeeling
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.foodTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.mentalTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.otherTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.weatherTriggerChips
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.toHashMap
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CreateConditionLogViewModel @Inject constructor(
    private val saveConditionLog: SaveConditionLogUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var sliderValue by mutableStateOf(0f)
        private set

    fun onSliderValueChanged(value: Float) {
        sliderValue = value
    }

    val foodTriggerUiChips = foodTriggerChips
    val weatherTriggerUiChips = weatherTriggerChips
    val mentalTriggerUiChips = mentalTriggerChips
    val otherTriggerUiChips = otherTriggerChips

    init {
        foodTriggerUiChips.forEach { it.state = UiChip.UiChipState.UNSELECTED }
        weatherTriggerUiChips.forEach { it.state = UiChip.UiChipState.UNSELECTED }
        mentalTriggerUiChips.forEach { it.state = UiChip.UiChipState.UNSELECTED }
        otherTriggerUiChips.forEach { it.state = UiChip.UiChipState.UNSELECTED }
    }

    fun saveClick() {
        viewModelScope.launch {
            val log = ConditionLogModel(
                creationDate = LocalDate.now(),
                feeling = sliderValue.asFeeling(),
                foodTriggers = foodTriggerUiChips.toHashMap(context),
                weatherTriggers = weatherTriggerUiChips.toHashMap(context),
                mentalHealthTriggers = mentalTriggerUiChips.toHashMap(context),
                otherTriggers = otherTriggerUiChips.toHashMap(context)
            )
            saveConditionLog(log)
        }
    }

}