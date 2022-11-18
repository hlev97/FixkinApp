package hu.bme.aut.it9p0z.authentication_ui.register.weightandheight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.it9p0z.authentication_domain.usecases.SaveHeightUseCase
import hu.bme.aut.it9p0z.authentication_domain.usecases.SaveWeightUseCase
import hu.bme.aut.it9p0z.authentication_ui.R
import hu.bme.aut.it9p0z.ui.model.UiEvent
import hu.bme.aut.it9p0z.ui.model.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightAndHeightViewModel @Inject constructor(
    private val saveWeight: SaveWeightUseCase,
    private val saveHeight: SaveHeightUseCase
) : ViewModel() {

    var weight by mutableStateOf("")
        private set

    fun onWeightChanged(value: String) {
        if (weightError) weightError = !weightError
        weight = value.trim()
    }

    var weightError by mutableStateOf(false)
        private set

    fun onWeightErrorStateChanged() {
        weightError = !weightError
    }

    var height by mutableStateOf("")
        private set

    fun onHeightChanged(value: String) {
        if (heightError) heightError = !heightError
        height = value.trim()
    }

    var heightError by mutableStateOf(false)
        private set

    fun onHeightErrorStateChanged() {
        heightError = !heightError
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun preformNextClick() {
        viewModelScope.launch {
            if (weight.isNotEmpty() && height.isNotEmpty()) {
                saveWeight(weight.toDouble())
                saveHeight(height.toDouble())
                _uiEvent.send(UiEvent.Success)
            } else _uiEvent.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_empty_fields)))
        }
    }
}