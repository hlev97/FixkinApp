package hu.bme.aut.it9p0z.fixkin

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtil {
    fun aDayPassed(date: LocalDate): Boolean
            = date != LocalDate.now()

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

}