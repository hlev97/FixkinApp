package hu.bme.aut.it9p0z.model

sealed class UiEvent {
    object Success: UiEvent()
    data class ShowSnackbar(val message: UiText): UiEvent()
}