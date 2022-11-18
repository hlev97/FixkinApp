package hu.bme.aut.it9p0z.authentication_ui.register.medicines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.it9p0z.authentication_ui.R
import hu.bme.aut.it9p0z.ui.components.buttons.TextButton
import hu.bme.aut.it9p0z.ui.components.chipgroup.ChipGroup
import hu.bme.aut.it9p0z.ui.model.UiEvent
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.theme.corner_radius_s

@ExperimentalMaterial3Api
@Composable
fun MedicinesScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit,
    viewModel: MedicinesViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChipGroup(
            label = UiText.StringResource(id = R.string.chip_group_label_topical),
            chips = viewModel.topicalUiChips
        )
        ChipGroup(
            label = UiText.StringResource(id = R.string.chip_group_label_oral_or_injected),
            chips = viewModel.oralOrInjectedUiChips
        )
        ChipGroup(
            label = UiText.StringResource(id = R.string.chip_group_label_other_treatments),
            chips = viewModel.otherUiChips
        )
        TextButton(
            uiText = UiText.StringResource(id = R.string.button_register),
            shape = RoundedCornerShape(corner_radius_s),
            onClick = { viewModel.preformNextClick() },
            modifier = Modifier.width(TextFieldDefaults.MinWidth)
        )
    }
}