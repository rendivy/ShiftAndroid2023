package com.example.diapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecastday(
    val date: String,
    @SerialName("day") val day: Day,
    @SerialName("astro") val astro: Astro?,
    @SerialName("hour") val hourList: List<Hour>
)