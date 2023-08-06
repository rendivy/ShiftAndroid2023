package com.example.diapplication.data.model

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

        fun convertDateTime(inputDateTime: String): String {
            // Формат строки входной даты и времени
            val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

            // Формат строки, в которую будет произведено преобразование
            val outputFormat = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH)

            // Парсим входную строку в объект LocalDateTime
            val localDateTime = LocalDateTime.parse(inputDateTime, inputFormat)

            // Преобразуем объект LocalDateTime в строку заданного формата
            return localDateTime.format(outputFormat)
        }
    }
}