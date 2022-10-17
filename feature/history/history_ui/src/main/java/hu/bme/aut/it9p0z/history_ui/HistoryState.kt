package hu.bme.aut.it9p0z.history_ui

import hu.bme.aut.it9p0z.ui.model.UiText

sealed class HistoryState
object Loading: HistoryState()
object DataReady: HistoryState()
data class Error(val message: UiText): HistoryState()
