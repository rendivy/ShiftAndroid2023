package com.example.diapplication.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hour(
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("condition") val condition: Condition,
)