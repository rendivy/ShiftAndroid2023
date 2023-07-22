package com.example.diapplication.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    @SerialName("text") val text: String,
    @SerialName("icon") val icon: String
)