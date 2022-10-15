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

        fun Feeling.asFloat(): Float {
            return when(this) {
                is Sad -> 0f
                is Unhappy -> 1f
                is Neutral -> 2f
                is Happy -> 3f
                is Joyful -> 4f
            }
        }

        fun Float.asFeeling(): Feeling {
            return when(this) {
                0f -> Sad
                1f -> Unhappy
                2f -> Neutral
                3f -> Happy
                4f -> Joyful
                else -> Sad
            }
        }
    }
}
