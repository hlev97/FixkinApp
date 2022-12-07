package hu.bme.aut.it9p0z.authentication_ui.register.medicines

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.it9p0z.authentication_domain.usecases.LoginUserUseCase
import hu.bme.aut.it9p0z.authentication_domain.usecases.RegisterUserUseCase
import hu.bme.aut.it9p0z.authentication_domain.usecases.SaveMedicinesUseCase
import hu.bme.aut.it9p0z.ui.data.Medicines
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiEvent
import hu.bme.aut.it9p0z.ui.model.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicinesViewModel @Inject constructor(
    private val saveMedicinesUseCase: SaveMedicinesUseCase,
    private val registerUser: RegisterUserUseCase,
    private val app: Application
) : AndroidViewModel(app) {
    val topicalUiChips = Medicines.topicalUiChips
    val oralOrInjectedUiChips = Medicines.oralOrInjectedUiChips
    val otherUiChips = Medicines.otherUiChips

    private val selectedUiChips = mutableStateListOf<UiChip>()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun preformNextClick() {
        viewModelScope.launch {
            topicalUiChips.forEach {
                if (it.state == UiChip.UiChipState.SELECTED) {
                    selectedUiChips.add(it)
                }
            }
            oralOrInjectedUiChips.forEach {
                if (it.state == UiChip.UiChipState.SELECTED) {
                    selectedUiChips.add(it)
                }
            }
            otherUiChips.forEach {
                if (it.state == UiChip.UiChipState.SELECTED) {
                    selectedUiChips.add(it)
                }
            }
            val medicines = selectedUiChips.map { it.label.asString(app.baseContext) }
            saveMedicinesUseCase(medicines)

            try {
                registerUser()
                _uiEvent.send(UiEvent.Success)
            } catch (e: Exception) {
                _uiEvent.send(UiEvent.ShowSnackbar(UiText.DynamicString(e.message ?: "Request error")))
            }
        }
    }
}