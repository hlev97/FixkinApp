package hu.bme.aut.it9p0z.checkdailylogcalendar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.it9p0z.checkdailylogcalendar.util.DateUtil.currentDateForCalendarPage
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun PagerWeek(
    modifier: Modifier = Modifier,
    currentDate: Calendar,
    creationDates: List<LocalDate>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        DaysOfWeek(
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        WeekDays(
            modifier = Modifier,
            calendarWeek = currentDateForCalendarPage(currentDate),
            creationDates = creationDates
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PagerWeek_Preview() {
    val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    PagerWeek(
        modifier = Modifier,
        currentDate = Calendar.getInstance(),
        creationDates = listOf(
            LocalDate.parse("2022/10/14", formatter),
            LocalDate.parse("2022/10/13", formatter),
            LocalDate.parse("2022/10/12", formatter),
        )
    )
}