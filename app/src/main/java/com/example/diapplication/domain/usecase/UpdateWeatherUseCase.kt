package com.example.diapplication.domain.usecase

import com.example.diapplication.data.repository.WeatherRepository
import com.example.diapplication.domain.entity.Weather
import javax.inject.Inject

class UpdateWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
        suspend operator fun invoke(cityName: String): Weather {
            return repository.getCurrentWeather(cityName)
        }
}
