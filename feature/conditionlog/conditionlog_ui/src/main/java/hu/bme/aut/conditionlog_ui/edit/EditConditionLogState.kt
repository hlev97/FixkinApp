package hu.bme.aut.conditionlog_ui.edit

import androidx.compose.runtime.snapshots.SnapshotStateList
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiText
import java.time.LocalDate

sealed class EditConditionLogState {
    object Loading: EditConditionLogState()
    data class DataReady(
        val id: Int,
        val creationDate: LocalDate,
        val foodTriggerUiChips: SnapshotStateList<UiChip>,
        val weatherTriggerUiChips: SnapshotStateList<UiChip>,
        val mentalTriggerUiChips: SnapshotStateList<UiChip>,
        val otherTriggerUiChips: SnapshotStateList<UiChip>,
        val sliderValue: Float
    ): EditConditionLogState()
    data class Error(val message: UiText): EditConditionLogState()
}