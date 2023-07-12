package com.example.diapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Forecast(
    @SerialName("forecastday") val forecastDayList: List<Forecastday>
)