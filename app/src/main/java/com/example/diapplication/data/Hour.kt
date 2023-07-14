package com.example.diapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hour(
    @SerialName("temp_c") val tempC: Double,
    @SerialName("condition") val condition: Condition,
)