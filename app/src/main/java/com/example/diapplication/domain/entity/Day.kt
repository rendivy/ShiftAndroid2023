package com.example.diapplication.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Day(
    @SerialName("maxtemp_c") val maximumTemperature: Double,
    @SerialName("mintemp_c") val minimumTemperature: Double,
    @SerialName("avgtemp_c") val averageTemperature: Double,
    @SerialName("condition") val condition: Condition,
    )