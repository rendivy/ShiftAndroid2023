package com.example.diapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserGeocoding(
    @SerialName("city") val city: String,
)