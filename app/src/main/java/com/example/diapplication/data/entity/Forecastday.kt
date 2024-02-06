package com.example.diapplication.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecastday(
    val date: String,
    @SerializedName("day") val day: Day,
    @SerializedName("astro") val astro: Astro?,
    @SerializedName("hour") val hourList: List<Hour>,
    @SerializedName("totalprecip_mm") val totalPrecipitationMm: Double = 0.0,
)