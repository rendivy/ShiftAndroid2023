package com.example.diapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    @SerialName("text") val text: String,
    @SerialName("icon") val icon: String
)