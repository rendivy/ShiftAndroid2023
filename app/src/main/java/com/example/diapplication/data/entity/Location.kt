package com.example.diapplication.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
)