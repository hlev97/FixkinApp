package hu.bme.aut.it9p0z.model.feeling

sealed class Feeling(val name: String) {
    object Sad: Feeling(name = "sad")
    object Unhappy: Feeling(name = "unhappy")
    object Neutral: Feeling(name = "neutral")
    object Happy: Feeling(name = "happy")
    object Joyful: Feeling(name = "joyful")

    companion object {
        fun Feeling.asString(): String {
            return when(this) {
                is Sad -> "sad"
                is Unhappy -> "unhappy"
                is Neutral -> "neutral"
                is Happy -> "happy"
                is Joyful -> "joyful"
            }
        }

        fun String.asFeeling(): Feeling {
            return when(this) {
                "sad" -> Sad
                "unhappy" -> Unhappy
                "neutral" -> Neutral
                "happy" -> Happy
                "joyful" -> Joyful
                else -> Sad
            }
        }
    }
}
