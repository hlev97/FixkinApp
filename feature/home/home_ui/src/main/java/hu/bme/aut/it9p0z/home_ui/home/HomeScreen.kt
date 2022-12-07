package hu.bme.aut.it9p0z.home_ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import hu.bme.aut.it9p0z.checkdailylogcalendar.ui.CheckDailyConditionWeeklyCalendar
import hu.bme.aut.it9p0z.home_ui.components.MostCommonTriggers
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.theme.dp_l
import java.time.LocalDate
import hu.bme.aut.it9p0z.home_ui.R

@OptIn(ExperimentalLifecycleComposeApi::class)
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    when(state) {
        is HomeState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
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
            val message = (state as HomeState.Error)
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = message.message.asString(context),
                    textAlign = TextAlign.Center
                )
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
        CheckDailyConditionWeeklyCalendar(creationDates = dates)

        Spacer(modifier = Modifier.height(dp_l))

        val triggerSizes = listOf(foodTriggers.size, weatherTriggers.size, mentalTriggers.size, otherTriggers.size)
        if (triggerSizes.all { it >= 2 }) {
            MostCommonTriggers(
                title = UiText.StringResource(R.string.most_common_food_triggers),
                triggers = foodTriggers,
            )

            MostCommonTriggers(
                title = UiText.StringResource(R.string.most_common_weather_triggers),
                triggers = weatherTriggers,
            )

            MostCommonTriggers(
                title = UiText.StringResource(R.string.most_common_mental_triggers),
                triggers = mentalTriggers,
            )

            MostCommonTriggers(
                title = UiText.StringResource(R.string.most_common_other_triggers),
                triggers = otherTriggers,
            )
        } else {
            Text(
                text = stringResource(id = R.string.too_few_data),
                modifier = Modifier.weight(4f),
                textAlign = TextAlign.Center
            )
        }

    }
}