package hu.bme.aut.it9p0z.history_ui

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.it9p0z.history_domain.usecases.DeleteConditionLogUseCase
import hu.bme.aut.it9p0z.history_domain.usecases.LoadConditionLogsUseCase
import hu.bme.aut.it9p0z.ui.data.*
import hu.bme.aut.it9p0z.ui.model.ConditionLogListItemModel
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiText
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val loadConditionLogs: LoadConditionLogsUseCase,
    private val deleteConditionLog: DeleteConditionLogUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state: MutableStateFlow<HistoryState> = MutableStateFlow(Loading)
    val state: StateFlow<HistoryState> = _state.asStateFlow()

    val result = MutableLiveData<List<ConditionLogListItemModel>>()

    var isRefreshing by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            load()
        }
    }

    fun refresh() {
        isRefreshing = true
        viewModelScope.launch(Dispatchers.IO) {
            load(delayed = true)
        }
        isRefreshing = false
    }

    private suspend fun load(delayed: Boolean = false) {
        _state.emit(Loading)
        _state.emit(try {
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
            val logs = loadConditionLogs().map { log ->
                val foodTriggers = log.getFoodTriggerUiChips(context)
                    .filter { it.state == UiChip.UiChipState.SELECTED }
                val weatherTriggers = log.getWeatherTriggerUiChips(context)
                    .filter { it.state == UiChip.UiChipState.SELECTED }

                val mentalTriggers = log.getMentalHealthTriggerUiChips(context)
                    .filter { it.state == UiChip.UiChipState.SELECTED }

                val otherTriggers = log.getOtherTriggerUiChips(context)
                    .filter { it.state == UiChip.UiChipState.SELECTED }

                val triggers = mutableListOf<UiChip>()
                triggers.addAll(foodTriggers)
                triggers.addAll(weatherTriggers)
                triggers.addAll(mentalTriggers)
                triggers.addAll(otherTriggers)

                ConditionLogListItemModel(
                    id = log.id!!,
                    date = UiText.DynamicString(log.creationDate.format(formatter)),
                    feeling = log.feeling.asFeelingUi(),
                    triggers = triggers
                )
            }
            if (delayed) {
                delay(2000)
            }
            result.postValue(logs)
            DataReady
        } catch (e: Exception) {
            Error(message = e.message?.let {  UiText.DynamicString(it) } ?: UiText.StringResource(R.string.some_error))
        })
    }

    fun deleteLog(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteConditionLog(id)
            load()
        }
    }
}