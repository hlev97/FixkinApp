package hu.bme.aut.it9p0z.checkdailylogcalendar.model

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.*

class CalendarState(val date: Calendar) {
    val pageCount = Int.MAX_VALUE
    val startPosition = Int.MAX_VALUE / 2

    var pagePosition by mutableStateOf(startPosition)

    val currentWeekOfYear: String by derivedStateOf {
        val currentWeek = (date.clone() as Calendar).apply {
            add(Calendar.WEEK_OF_YEAR, pagePosition - startPosition)
        }.get(Calendar.WEEK_OF_YEAR)

        val currentMonth = (date.clone() as Calendar).apply {
            add(Calendar.WEEK_OF_YEAR, pagePosition - startPosition)
        }.getDisplayName(
            Calendar.MONTH,
            Calendar.LONG_FORMAT, Locale.getDefault()
        )

        "WEEK $currentWeek \n$currentMonth"
    }
}