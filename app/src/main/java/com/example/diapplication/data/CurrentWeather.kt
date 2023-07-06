package com.example.diapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("temp_c") val temperatureCelsius: Double,
    @SerialName("temp_f") val temperatureFahrenheit: Double,
    @SerialName("condition") val weatherCondition: Condition,
)