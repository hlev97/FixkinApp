package hu.bme.aut.it9p0z.home_ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import hu.bme.aut.it9p0z.checkdailylogcalendar.ui.CheckDailyConditionWeeklyCalendar
import hu.bme.aut.it9p0z.home_ui.components.MostCommonTriggers
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.theme.dp_l
import java.time.LocalDate

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    when(state) {
        is HomeState.Loading -> {
            Column(modifier = modifier) {
                Text(text = "loading")
            }
        }
        is HomeState.DataReady -> {
            val data = (state as HomeState.DataReady)
            DataReadyContent(
                dates = data.creationDates,
                foodTriggers = data.foodTriggerUiChips,
                weatherTriggers = data.weatherTriggerUiChips,
                mentalTriggers = data.mentalTriggerUiChips,
                otherTriggers = data.otherTriggerUiChips,
                modifier = modifier
            )
        }
        is HomeState.Error -> {
            Column(modifier = modifier) {
                Text(text = "error")
            }
        }
        else -> { }
    }
}

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun DataReadyContent(
    dates: List<LocalDate>,
    foodTriggers: List<UiChip>,
    weatherTriggers: List<UiChip>,
    mentalTriggers: List<UiChip>,
    otherTriggers: List<UiChip>,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CheckDailyConditionWeeklyCalendar(
            creationDates = dates
        )

        Spacer(modifier = Modifier.height(dp_l))

        MostCommonTriggers(
            title = UiText.DynamicString("Most common food triggers: "),
            triggers = foodTriggers,
        )

        MostCommonTriggers(
            title = UiText.DynamicString("Most common weather triggers: "),
            triggers = weatherTriggers,
        )

        MostCommonTriggers(
            title = UiText.DynamicString("Most common mental health triggers: "),
            triggers = mentalTriggers,
        )

        MostCommonTriggers(
            title = UiText.DynamicString("Most common other triggers: "),
            triggers = otherTriggers,
        )
    }
}