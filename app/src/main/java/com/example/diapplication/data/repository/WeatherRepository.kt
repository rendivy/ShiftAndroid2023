package com.example.diapplication.data.repository

import com.example.diapplication.data.remote.WeatherApiService
import com.example.diapplication.domain.entity.Weather
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepository @Inject constructor(private val apiService: WeatherApiService) {
    suspend fun getCurrentWeather(location: String): Weather {
        return apiService.getCurrentWeather(q = location)
    }
}