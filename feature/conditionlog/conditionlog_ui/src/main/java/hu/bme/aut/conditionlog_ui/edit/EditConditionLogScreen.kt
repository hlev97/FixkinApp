package hu.bme.aut.conditionlog_ui.edit

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.conditionlog_ui.R
import hu.bme.aut.conditionlog_ui.components.ConditionLogEditor
import hu.bme.aut.it9p0z.ui.components.appbars.NormalTopAppBar
import hu.bme.aut.it9p0z.ui.model.UiText

@ExperimentalMaterial3Api
@Composable
fun EditConditionLogScreen(
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit,
    onBack: () -> Unit,
    viewModel: EditConditionLogViewModel = hiltViewModel()
) {
    BackHandler(onBack = onBack)
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.state.collectAsState()

    val context = LocalContext.current
    Scaffold(
        topBar = {
            Column {
                NormalTopAppBar(
                    title = UiText.StringResource(R.string.app_bar_title_create_log),
                    onNavigation = onBack,
                )
                if (state is EditConditionLogState.Loading) {
                    LinearProgressIndicator(modifier = modifier.fillMaxWidth())
                }
            }

        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            if (state !is EditConditionLogState.Loading) {
                LargeFloatingActionButton(onClick = {
                    viewModel.updateClick(state)
                    onFabClick()
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Save,
                        contentDescription = null,
                        modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
                    )
                }
            }
        },
        modifier = modifier,
    ) {
        when(state) {
            is EditConditionLogState.Loading -> {
                Box(
                    modifier = modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = hu.bme.aut.it9p0z.ui.R.drawable.ic_empty_24),
                        contentDescription = null
                    )
                }
            }
            is EditConditionLogState.DataReady -> {
                val data = (state as EditConditionLogState.DataReady)
                ConditionLogEditor(
                    foodTriggerChips = data.foodTriggerUiChips,
                    weatherTriggerChips = data.weatherTriggerUiChips,
                    mentalTriggerChips = data.mentalTriggerUiChips,
                    otherTriggerChips = data.otherTriggerUiChips,
                    modifier = Modifier.padding(it),
                    sliderValue = data.sliderValue,
                    onSliderValueChange = { newValue ->
                        viewModel.onSliderValueChanged(newValue)
                    },
                )
            }
            is EditConditionLogState.Error -> {
                val message = (state as EditConditionLogState.Error).message
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = message.asString(context))
                }
            }
            else -> { }
        }
    }
}