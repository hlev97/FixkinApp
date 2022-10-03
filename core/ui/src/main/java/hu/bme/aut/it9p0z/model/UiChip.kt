package hu.bme.aut.it9p0z.model

data class UiChip(
    val icon: UiIcon?,
    val label: UiText,
    var state: UiChipState = UiChipState.UNSELECTED,
    var enabled: Boolean = true,
) {
    enum class UiChipState {
        SELECTED, UNSELECTED;
    }

    fun onChangeState() {
        when (this.state) {
            UiChipState.SELECTED -> this.state = UiChipState.UNSELECTED
            else -> this.state = UiChipState.SELECTED
        }
    }
}
