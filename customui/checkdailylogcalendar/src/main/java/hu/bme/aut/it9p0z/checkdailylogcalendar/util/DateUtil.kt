package hu.bme.aut.it9p0z.checkdailylogcalendar.util

import org.joda.time.DateTime
import java.util.*

object DateUtil {

    fun isSameDate(
        firstDate: Calendar,
        secondDate: Calendar
    ): Boolean {
        return firstDate.get(Calendar.DAY_OF_MONTH) == secondDate.get(Calendar.DAY_OF_MONTH) &&
                firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH) &&
                firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR)
    }

    fun currentDateForCalendarPage(
        startingDate: Calendar
    ): Calendar {
        val datesToDraw = startingDate.clone() as Calendar
        datesToDraw.apply {
            timeInMillis = DateTime(this).withDayOfWeek(1).millis
        }
        val dayOfWeek = datesToDraw.get(Calendar.DAY_OF_WEEK)
        if (dayOfWeek != 1) datesToDraw.add(Calendar.DAY_OF_YEAR, -(dayOfWeek - 1))
        return datesToDraw
    }

}