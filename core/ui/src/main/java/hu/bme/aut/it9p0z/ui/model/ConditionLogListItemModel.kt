package hu.bme.aut.it9p0z.ui.model

import hu.bme.aut.it9p0z.ui.data.FeelingUi

data class ConditionLogListItemModel(
    val id: Int,
    val date: UiText,
    val feeling: FeelingUi,
    val triggers: List<UiChip>
)
