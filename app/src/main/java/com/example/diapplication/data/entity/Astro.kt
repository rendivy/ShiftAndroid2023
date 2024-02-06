package com.example.diapplication.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Astro(
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset")val sunset: String,
    @SerializedName("moonrise")val moonrise: String,
    @SerializedName("moonset")val moonset: String,
)
