package hu.bme.aut.it9p0z.checkdailylogcalendar.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun CalendarPager(
    pagerState: PagerState,
    startIndex: Int,
    dates: Calendar,
    pageCount: Int,
    creationDates: List<LocalDate>
) {
    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(
                align = Alignment.Top,
                unbounded = true
            ),
        state = pagerState,
        count = pageCount
    ) { index ->
        val currentDate = (dates.clone() as Calendar).apply {
            add(Calendar.WEEK_OF_YEAR, index - startIndex)
        }
        PagerWeek(
            modifier = Modifier.fillMaxWidth(),
            currentDate = currentDate,
            creationDates = creationDates
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun Preview_CalendarPager() {
    val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    CalendarPager(
        PagerState(1),
        1,
        Calendar.getInstance(),
        3,
        listOf(
            LocalDate.parse("2022/10/14", formatter),
            LocalDate.parse("2022/10/13", formatter),
            LocalDate.parse("2022/10/12", formatter),
        )
    )
}