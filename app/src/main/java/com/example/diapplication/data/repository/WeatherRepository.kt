package com.example.diapplication.data.repository

import com.example.diapplication.data.Weather
import com.example.diapplication.data.remote.WeatherApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val apiService: WeatherApiService) {
    suspend fun getCurrentWeather(location: String): Weather {
        return apiService.getCurrentWeather(q = location)
    }


}