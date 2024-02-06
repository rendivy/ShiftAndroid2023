package com.example.diapplication.data.remote


import com.example.diapplication.common.NetworkConstants.API_TOKEN
import com.example.diapplication.data.entity.Location
import com.example.diapplication.data.entity.Weather
import retrofit2.http.GET
import retrofit2.http.Query



interface WeatherApiService {

    @GET("forecast.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String? = API_TOKEN,
        @Query("q") q: String,
        @Query("days") days: Int? = 3,
        @Query("latitude") latitude: Double? = null,
        @Query("longitude") longitude: Double? = null,
        @Query("alerts") alerts: String? = "yes",
    ): Weather

    @GET("search.json")
    suspend fun getPredict(
        @Query("key") key: String? = API_TOKEN,
        @Query("q") q: String?,
    ): List<Location>

}