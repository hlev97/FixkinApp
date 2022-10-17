package hu.bme.aut.it9p0z.network.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object MoshiAdapters {
    private val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    @ToJson
    fun LocalDate.asString(): String = this.format(formatter)

    @FromJson
    fun String.asLocalDate(): LocalDate = LocalDate.parse(this, formatter)

}