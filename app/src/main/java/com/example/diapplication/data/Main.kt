package com.example.diapplication.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Retrofit




suspend fun main() {
    val repository = WeatherRepository()
    println(Json.encodeToString(repository.getCurrentWeather("London")))
}