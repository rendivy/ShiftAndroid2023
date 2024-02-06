package com.example.diapplication.common.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


class DateConverter {
    companion object {
        fun invertDate(dateString: String):String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
            val date = LocalDate.parse(dateString, formatter)
            val dayOfMonth = date.dayOfMonth
            val month = date.month
            val monthName = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

            return "$dayOfMonth $monthName"
        }

        fun convertDateTime(inputDateTime: String?): String {
            val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val outputFormat = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH)
            val localDateTime = LocalDateTime.parse(inputDateTime, inputFormat)

            return localDateTime.format(outputFormat)
        }
    }
}