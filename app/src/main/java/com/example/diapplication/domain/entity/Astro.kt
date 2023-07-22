package com.example.diapplication.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Astro(
    @SerialName("sunrise") val sunrise: String,
    @SerialName("sunset")val sunset: String,
    @SerialName("moonrise")val moonrise: String,
    @SerialName("moonset")val moonset: String,
)
