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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.conditionlog_ui.R
import hu.bme.aut.conditionlog_ui.components.ConditionLogEditor
import hu.bme.aut.it9p0z.ui.components.appbars.NormalTopAppBar
import hu.bme.aut.it9p0z.ui.model.UiText

@OptIn(ExperimentalLifecycleComposeApi::class)
@ExperimentalMaterial3Api
@Composable
fun EditConditionLogScreen(
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit,
    onBack: () -> Unit,
    viewModel: EditConditionLogViewModel = hiltViewModel()
) {
    BackHandler(onBack = onBack)
    val state by viewModel.state.collectAsStateWithLifecycle()

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
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
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
                    sliderValue = viewModel.sliderValue,
                    onSliderValueChange = viewModel::onSliderValueChanged,
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