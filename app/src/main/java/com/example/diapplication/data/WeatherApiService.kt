package com.example.diapplication.data

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val AUTH_TOKEN = "ee6dfd6f5e224f0a8ed134208230407"

interface WeatherApiService {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String = AUTH_TOKEN,
        @Query("q") q: String,
    ): Weather
}