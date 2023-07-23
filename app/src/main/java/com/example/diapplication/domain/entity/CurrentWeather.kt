package com.example.diapplication.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("temp_c") val temperatureCelsius: Double,
    @SerialName("condition") val weatherCondition: Condition,
    @SerialName("wind_kph") val windKph: Double,
    @SerialName("wind_dir") val windDirection: String,
    @SerialName("humidity") val humidity: Int,
    @SerialName("uv") val uv: Double,
    @SerialName("feelslike_c") val feelsLikeCelsius: Double,
    @SerialName("pressure_mb") val pressureMb: Double,
)