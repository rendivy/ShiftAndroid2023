package com.example.diapplication.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Current(
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val temperatureCelsius: Double,
    @SerializedName("condition") val weatherCondition: Condition,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("uv") val uv: Double,
    @SerializedName("feelslike_c") val feelsLikeCelsius: Double,
    @SerializedName("pressure_mb") val pressureMb: Double,
)