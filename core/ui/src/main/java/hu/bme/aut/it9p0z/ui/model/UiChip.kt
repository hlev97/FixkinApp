package hu.bme.aut.it9p0z.ui.model

data class UiChip(
    val icon: UiIcon? = null,
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

    companion object {
        fun Boolean.toUiChipState(): UiChipState = if (this) {
            UiChipState.UNSELECTED
        } else UiChipState.SELECTED
    }
}
