package hu.bme.aut.survey_domain.usecases

data class SurveyUseCases(
    val addPoint: AddPointToAnswerResultUseCase,
    val countResult: CountSurveyResultUseCase,
    val loadLastAnswer: LoadLastAnswerUseCase,
    val saveLastAnswer: SaveLastAnswerUseCase,
    val saveSurveyLog: SaveSurveyLogUseCase,
    val resetSurvey: ResetSurveyUseCase,
    val previousQuestion: PrevQuestionUseCase
)