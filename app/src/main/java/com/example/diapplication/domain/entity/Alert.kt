package com.example.diapplication.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Alert(
    @SerialName("headline") val headline: String?,
    @SerialName("event") val event: String?,
    @SerialName("desc") val description: String?,
    @SerialName("instruction") val instruction: String?
)