package hu.bme.aut.conditionlog_ui.create

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.conditionlog_ui.R
import hu.bme.aut.conditionlog_ui.components.ConditionLogEditor
import hu.bme.aut.it9p0z.ui.components.appbars.NormalTopAppBar
import hu.bme.aut.it9p0z.ui.model.UiText

@ExperimentalMaterial3Api
@Composable
fun CreateConditionLogScreen(
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit,
    onBack: () -> Unit,
    viewModel: CreateConditionLogViewModel = hiltViewModel()
) {
    BackHandler(onBack = onBack)
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            NormalTopAppBar(
                title = UiText.StringResource(R.string.app_bar_title_create_log),
                onNavigation = onBack,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {
                    viewModel.saveClick()
                    onFabClick()
                },
                modifier = Modifier.testTag("saveFab")
            ) {
                Icon(
                    imageVector = Icons.Outlined.Save,
                    contentDescription = null,
                    modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
                )
            }
        },
        modifier = modifier,
    ) {
        ConditionLogEditor(
            foodTriggerChips = viewModel.foodTriggerUiChips,
            weatherTriggerChips = viewModel.weatherTriggerUiChips,
            mentalTriggerChips = viewModel.mentalTriggerUiChips,
            otherTriggerChips = viewModel.otherTriggerUiChips,
            modifier = Modifier.padding(it),
            sliderValue = viewModel.sliderValue,
            onSliderValueChange = viewModel::onSliderValueChanged,
        )
    }
}

