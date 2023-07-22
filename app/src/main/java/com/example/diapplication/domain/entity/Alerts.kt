package com.example.diapplication.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Alerts(
    @SerialName("alert") val alertList: List<Alert>
)