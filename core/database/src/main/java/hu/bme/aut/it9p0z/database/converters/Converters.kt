package hu.bme.aut.it9p0z.database.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Converters {
    private val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    private const val DELIMITER = ":"
    private const val ENTRY_DELIMITER = ";"

    @TypeConverter
    fun String.toLocalDate(): LocalDate = LocalDate.parse(this, formatter)

    @TypeConverter
    fun LocalDate.asString(): String = this.format(formatter)

    @TypeConverter
    fun HashMap<String,Boolean>.asString(): String {
        var result = ""
        val keys = this.keys
        keys.forEachIndexed { index, key ->
            result += key + DELIMITER + "${this[key]}"
            if (index != keys.size-1) {
                result += ENTRY_DELIMITER
            }
        }
        return result
    }

    @TypeConverter
    fun String.asHashmap(): HashMap<String,Boolean> {
        val entries = this.split(ENTRY_DELIMITER)
        val hashMap = hashMapOf<String,Boolean>()
        entries.forEach {
            val entry = it.split(DELIMITER)
            val key = entry[0]
            val value = entry[1].toBoolean()
            hashMap[key] = value
        }
        return hashMap
    }
}