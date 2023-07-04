package com.example.diapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Weather(
    @SerialName("location") val location: Location,
    @SerialName("current") val current: CurrentWeather
)


@Serializable
data class Location(
    @SerialName("name") val name: String,
    @SerialName("region") val region: String,
    @SerialName("country") val country: String,
)


@Serializable
data class CurrentWeather(
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("temp_c") val temperatureCelsius: Double,
    @SerialName("temp_f") val temperatureFahrenheit: Double,
)