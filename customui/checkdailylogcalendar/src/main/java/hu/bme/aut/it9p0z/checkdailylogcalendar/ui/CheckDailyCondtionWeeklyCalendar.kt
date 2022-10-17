package hu.bme.aut.it9p0z.checkdailylogcalendar.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import hu.bme.aut.it9p0z.checkdailylogcalendar.model.CalendarState
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun CheckDailyConditionWeeklyCalendar(
    creationDates: List<LocalDate>,
    modifier: Modifier = Modifier
) {
    val calendarState = remember { CalendarState(Calendar.getInstance()) }
    WeeklyCalendar(
        modifier = modifier.fillMaxWidth(),
        calendarState = calendarState,
        creationDates = creationDates
    )
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun WeeklyCalendar(
    modifier: Modifier = Modifier,
    calendarState: CalendarState,
    creationDates: List<LocalDate>
) {
    Column(modifier = modifier) {
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(initialPage = calendarState.startPosition)
        Header(
            modifier = modifier,
            title = calendarState.currentWeekOfYear,
            previousWeek = {
                coroutineScope.launch {
                    pagerState.scrollToPage(
                        pagerState.currentPage - 1
                    )
                }
            },
            nextWeek = {
                coroutineScope.launch {
                    pagerState.scrollToPage(
                        pagerState.currentPage + 1
                    )
                }
            }
        )

        snapshotFlow {
            pagerState.currentPage
        }.collectAsState(initial = calendarState.startPosition).value.let {
            calendarState.pagePosition = it
        }

        CalendarPager(
            pagerState = pagerState,
            startIndex = calendarState.startPosition,
            dates = calendarState.date,
            pageCount = calendarState.pageCount,
            creationDates = creationDates
        )
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
    previousWeek: () -> Unit = {},
    nextWeek: () -> Unit = {}
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { previousWeek() }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "null"
            )
        }
        Text(
            text = title,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        IconButton(
            onClick = { nextWeek() }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "null"
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Header_Preview() {
    Header(
        title = "Week 1",
        previousWeek = {},
        nextWeek = {}
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun CheckDailyConditionWeeklyCalendar_Preview() {
    val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    CheckDailyConditionWeeklyCalendar(
        listOf(
            LocalDate.parse("2022/10/14", formatter),
            LocalDate.parse("2022/10/13", formatter),
            LocalDate.parse("2022/10/12", formatter),
        )
    )
}