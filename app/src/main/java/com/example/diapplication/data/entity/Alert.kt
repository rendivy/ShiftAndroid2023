package com.example.diapplication.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Alert(
    @SerializedName("headline") val headline: String?,
    @SerializedName("event") val event: String?,
    @SerializedName("desc") val description: String?,
    @SerializedName("instruction") val instruction: String?
)
