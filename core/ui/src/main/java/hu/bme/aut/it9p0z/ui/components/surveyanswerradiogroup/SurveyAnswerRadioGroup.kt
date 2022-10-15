package hu.bme.aut.it9p0z.ui.components.surveyanswerradiogroup

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import hu.bme.aut.it9p0z.ui.data.SurveyAnswer
import hu.bme.aut.it9p0z.ui.theme.dp_l
import hu.bme.aut.it9p0z.ui.theme.dp_s
import hu.bme.aut.it9p0z.ui.theme.surveyAnswerRowHeight

@Composable
fun SurveyAnswerRadioGroup(
    answer: SurveyAnswer,
    selectedAnswer: SurveyAnswer,
    onAnswerSelected: (SurveyAnswer) -> Unit
) {
    val context = LocalContext.current
    Row(
        Modifier
            .fillMaxWidth()
            .height(surveyAnswerRowHeight)
            .selectable(
                selected = (answer == selectedAnswer),
                onClick = { onAnswerSelected(answer) },
                role = Role.RadioButton
            )
            .padding(horizontal = dp_l),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = (answer == selectedAnswer),
            onClick = null
        )
        Text(
            text = answer.answer.asString(context),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = dp_s)
        )
    }
}