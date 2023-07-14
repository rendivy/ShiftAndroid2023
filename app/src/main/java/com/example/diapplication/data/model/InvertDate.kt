package com.example.diapplication.data.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class InvertDate {
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
    }

}