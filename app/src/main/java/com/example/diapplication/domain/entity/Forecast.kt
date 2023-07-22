package com.example.diapplication.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Forecast(
    @SerialName("forecastday") val forecastDayList: List<Forecastday>,

    )