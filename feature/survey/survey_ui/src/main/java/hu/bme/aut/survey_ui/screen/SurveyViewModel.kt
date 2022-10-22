package hu.bme.aut.survey_ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.survey_domain.usecases.SurveyUseCases
import hu.bme.aut.survey_ui.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val surveyOperations: SurveyUseCases
) : ViewModel() {

    private val questions = SurveyQuestion.questions
    var currentQuestionId by mutableStateOf(0)
        private set
    var currentQuestion by mutableStateOf(questions[currentQuestionId])
        private set

    private val _state: MutableStateFlow<SurveyState> = MutableStateFlow(Answering)
    val state: StateFlow<SurveyState> = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            surveyOperations.resetSurvey()
        }
    }

    fun onNextClick(selectedAnswer: SurveyAnswer) {
        viewModelScope.launch(Dispatchers.IO) {
            surveyOperations.saveLastAnswer(selectedAnswer.point)
            surveyOperations.addPoint(selectedAnswer.point)
        }
        if (selectedAnswer == Yes) {
            currentQuestionId+=2
            currentQuestion = questions[currentQuestionId]
        } else {
            currentQuestionId++
            currentQuestion = questions[currentQuestionId]
        }

        if (currentQuestionId == questions.lastIndex) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = surveyOperations.countResult().asSurveyResultUi()
                _state.value = Done(
                    result = result
                )
                surveyOperations.saveSurveyLog()
            }
        }
    }

    fun onPreviousClick() {
        viewModelScope.launch(Dispatchers.IO) {
            surveyOperations.previousQuestion()
        }
    }


}

sealed interface SurveyState
object Answering: SurveyState
data class Done(
    val result: SurveyResultUI
): SurveyState