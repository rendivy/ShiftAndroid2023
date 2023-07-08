package com.example.diapplication.data.remote


import com.example.diapplication.data.Weather
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_TOKEN = "ee6dfd6f5e224f0a8ed134208230407"

interface WeatherApiService {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String = API_TOKEN,
        @Query("q") q: String,
    ): Weather
}