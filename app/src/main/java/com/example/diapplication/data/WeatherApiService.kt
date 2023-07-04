package com.example.diapplication.data

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val AUTH_TOKEN = "ee6dfd6f5e224f0a8ed134208230407"

interface WeatherApiService {
    @Headers("Authorization: Bearer $AUTH_TOKEN")
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") location: String
    ): Weather
}