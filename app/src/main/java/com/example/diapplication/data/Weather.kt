package com.example.diapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Weather(
    @SerialName("location") val location: Location,
    @SerialName("current") val current: CurrentWeather
)





