package com.example.diapplication.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Forecast(
    @SerializedName("forecastday") val forecastDayList: List<Forecastday>
)