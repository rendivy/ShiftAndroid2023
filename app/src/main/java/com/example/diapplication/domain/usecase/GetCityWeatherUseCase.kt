package com.example.diapplication.domain.usecase

import com.example.diapplication.data.entity.Weather
import com.example.diapplication.data.repository.WeatherRepository
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend fun invoke(cityName: String): Weather {
        return repository.getCurrentWeather(cityName)
    }
}
