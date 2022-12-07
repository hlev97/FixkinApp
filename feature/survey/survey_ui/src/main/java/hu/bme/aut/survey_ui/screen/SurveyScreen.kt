package hu.bme.aut.survey_ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.it9p0z.ui.components.buttons.TextButton
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.theme.corner_radius_s
import hu.bme.aut.it9p0z.ui.theme.dp_l
import hu.bme.aut.it9p0z.ui.theme.dp_m
import hu.bme.aut.survey_ui.components.surveyanswerradiogroup.SurveyAnswerRadioGroup
import hu.bme.aut.survey_ui.R
import hu.bme.aut.survey_ui.model.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@ExperimentalMaterial3Api
@Composable
fun SurveyScreen(
    onOkButtonClick: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SurveyViewModel = hiltViewModel()
) {
    BackHandler(onBack = onBack)

    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val (selectedAnswer, onAnswerSelected) = remember { mutableStateOf(viewModel.currentQuestion.answers[0]) }

    when (state) {
        Answering -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = viewModel.currentQuestion.text.asString(context),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = dp_l)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectableGroup()
                        .padding(bottom = dp_l)
                ) {
                    viewModel.currentQuestion.answers.forEach { answer ->
                        SurveyAnswerRadioGroup(
                            answer = answer,
                            selectedAnswer = selectedAnswer,
                            onAnswerSelected = onAnswerSelected
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    if (!viewModel.isFirstQuestion()) {
                        TextButton(
                            uiText = UiText.StringResource(R.string.button_prev),
                            shape = RoundedCornerShape(corner_radius_s),
                            onClick = viewModel::onPreviousClick,
                            modifier = Modifier.weight(1f).padding(horizontal = 5.dp)
                        )
                    }
                    TextButton(
                        uiText = UiText.StringResource(R.string.button_next),
                        shape = RoundedCornerShape(corner_radius_s),
                        onClick = { viewModel.onNextClick(selectedAnswer) },
                        modifier = Modifier.weight(1f).padding(horizontal = 5.dp)
                    )
                }
            }
        }
        is Done -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val _state = (state as Done)
                Text(
                    text = _state.result.text.asString(context),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                )

                val score = when (_state.result) {
                    is NoEffectUi -> _state.result.score
                    is SmallEffectUi -> _state.result.score
                    is ModerateEffectUi -> _state.result.score
                    is LargeEffectUi -> _state.result.score
                    is ExtremeEffectUi -> _state.result.score
                }

                Spacer(modifier = Modifier.height(dp_m))

                Text(
                    text = UiText.DynamicString(
                        stringResource(
                            id = R.string.survey_result_score,
                            score
                        )
                    ).asString(context),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(dp_l))

                TextButton(
                    uiText = UiText.StringResource(R.string.button_ok),
                    shape = RoundedCornerShape(corner_radius_s),
                    onClick = onOkButtonClick
                )
            }
        }
    }
}