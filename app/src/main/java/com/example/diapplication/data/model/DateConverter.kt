package com.example.diapplication.data.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class DateConverter {
    companion object{
        fun invertDate(dateString: String): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
            val date = LocalDate.parse(dateString, formatter)

            val dayOfMonth = date.dayOfMonth
            val month = date.month

            val monthName = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

            return "$dayOfMonth $monthName"
        }

        fun invertDateTime(dateTimeString: String): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH)
            val dateTime = LocalDateTime.parse(dateTimeString, formatter)

            val dayOfMonth = dateTime.dayOfMonth
            val month = dateTime.month
            val hour = dateTime.hour
            val minute = dateTime.minute

            val monthName = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

            return "$dayOfMonth $monthName $hour:$minute"
        }


        fun convertTo24HourFormat(time12Hour: String): String {
            val time12HourPattern = "h:mm a"
            val time24HourPattern = "HH:mm"

            val formatter12Hour = DateTimeFormatter.ofPattern(time12HourPattern)
            val formatter24Hour = DateTimeFormatter.ofPattern(time24HourPattern)

            val localTime = LocalTime.parse(time12Hour, formatter12Hour)
            return localTime.format(formatter24Hour)
        }
    }

}