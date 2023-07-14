package com.example.diapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Alerts(
    @SerialName("alert") val alertList: List<Alert>
)