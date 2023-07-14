package com.example.diapplication.data.remote


import com.example.diapplication.data.Weather
import com.example.diapplication.data.utils.Constants.API_TOKEN
import retrofit2.http.GET
import retrofit2.http.Query



interface WeatherApiService {

    @GET("forecast.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String? = API_TOKEN,
        @Query("q") q: String,
        @Query("days") days: Int? = 3,
        @Query("alerts") alerts: String? = "yes",
    ): Weather
}