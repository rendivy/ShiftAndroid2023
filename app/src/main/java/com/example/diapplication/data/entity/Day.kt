package com.example.diapplication.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Day(
    @SerializedName("maxtemp_c") val maximumTemperature: Double,
    @SerializedName("mintemp_c") val minimumTemperature: Double,
    @SerializedName("avgtemp_c") val averageTemperature: Double,
    @SerializedName("condition") val condition: Condition,
)