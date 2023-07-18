package com.example.diapplication.data.model

import androidx.compose.runtime.Composable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

class LocationTimeConverter {
    companion object{

        fun getCityHour(city: String): Int {
            val calendar: Calendar = Calendar.getInstance()
            val timeZone: TimeZone = TimeZone.getTimeZone(city)
            calendar.timeZone = timeZone

            val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)

            return hour
        }
    }
}