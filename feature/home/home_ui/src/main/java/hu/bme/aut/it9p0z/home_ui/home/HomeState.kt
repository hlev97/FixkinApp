package hu.bme.aut.it9p0z.home_ui.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiText
import java.time.LocalDate

sealed class HomeState {
    object Loading: HomeState()
    data class DataReady(
        val creationDates: List<LocalDate>,
        val foodTriggerUiChips: SnapshotStateList<UiChip>,
        val weatherTriggerUiChips: SnapshotStateList<UiChip>,
        val mentalTriggerUiChips: SnapshotStateList<UiChip>,
        val otherTriggerUiChips: SnapshotStateList<UiChip>,
    ): HomeState()
    data class Error(val message: UiText): HomeState()
}