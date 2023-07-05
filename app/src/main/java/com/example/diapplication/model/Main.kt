package com.example.diapplication.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


suspend fun main() {
    val repository = WeatherRepository()
    println(Json.encodeToString(repository.getCurrentWeather("Angarsk")))
}