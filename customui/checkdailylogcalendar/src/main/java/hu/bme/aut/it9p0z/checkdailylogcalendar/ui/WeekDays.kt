package hu.bme.aut.it9p0z.checkdailylogcalendar.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.bme.aut.it9p0z.checkdailylogcalendar.util.DateUtil.isSameDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeekDays(
    modifier: Modifier,
    calendarWeek: Calendar,
    creationDates: List<LocalDate>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (day in 0 until 7) {
            val dateForWeekCell = calendarWeek.clone() as Calendar
            dateForWeekCell.add(
                Calendar.DAY_OF_WEEK,
                day
            )
            val localDate = LocalDateTime.ofInstant(dateForWeekCell.toInstant(),dateForWeekCell.timeZone.toZoneId()).toLocalDate()
            WeekDay(
                date = dateForWeekCell,
                isConditionLogged = creationDates.contains(localDate)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeekDays_Preview() {
    val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    WeekDays(
        modifier = Modifier,
        calendarWeek = Calendar.getInstance(),
        creationDates = listOf(
            LocalDate.parse("2022/10/14", formatter),
            LocalDate.parse("2022/10/13", formatter),
            LocalDate.parse("2022/10/12", formatter),
        )
    )
}

@Composable
fun RowScope.WeekDay(
    date: Calendar,
    isConditionLogged: Boolean = false
) {
    val isToday = isSameDate(Calendar.getInstance(), date)
    Box(modifier = Modifier
        .weight(1f)
        .height(40.dp)
        .drawBehind {
            drawLine(
                color = Color.LightGray,
                start = Offset(size.width, 0f),
                end = Offset(size.width, size.height),
                strokeWidth = 1 * density
            )
        },
        contentAlignment = Alignment.Center
    ) {
        if (isConditionLogged) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center),
                tint = MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                modifier = Modifier.dateBackground(isToday),
                text = date.get(Calendar.DAY_OF_MONTH).toString(),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = if (isToday) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onBackground
            )
        }
    }
    
}
fun Modifier.dateBackground(
    isToday: Boolean
): Modifier = composed {
    if (isToday) {
        this
            .padding(4.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(4.dp)
    } else {
        this.padding(8.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun WeekDay_Preview() {
    Row {
        WeekDay(date = Calendar.getInstance())
    }
}

@Preview(showBackground = true)
@Composable
fun WeekDayLogged_Preview() {
    Row {
        WeekDay(date = Calendar.getInstance(), isConditionLogged = true)
    }
}