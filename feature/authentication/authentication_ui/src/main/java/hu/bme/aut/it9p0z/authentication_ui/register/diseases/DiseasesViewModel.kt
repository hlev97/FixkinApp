package hu.bme.aut.it9p0z.authentication_ui.register.diseases

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.it9p0z.authentication_domain.usecases.SaveDiseasesUseCase
import hu.bme.aut.it9p0z.ui.data.Disease
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiseasesViewModel @Inject constructor(
    private val saveDiseasesUseCase: SaveDiseasesUseCase,
    private val app: Application
) : AndroidViewModel(app) {
    val psoriasisUiChips = Disease.psoriasisDiseaseUiChips
    val eczemaUiChips = Disease.eczemaDiseaseUiChips

    val selectedUiChips = mutableStateListOf<UiChip>()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun preformNextClick() {
        viewModelScope.launch {
            psoriasisUiChips.forEach {
                if (it.state == UiChip.UiChipState.SELECTED) {
                    selectedUiChips.add(it)
                }
            }
            eczemaUiChips.forEach {
                if (it.state == UiChip.UiChipState.SELECTED) {
                    selectedUiChips.add(it)
                }
            }
            val diseases = selectedUiChips.map { it.label.asString(app.baseContext) }
            saveDiseasesUseCase(diseases)

            _uiEvent.send(UiEvent.Success)

        }
    }
}