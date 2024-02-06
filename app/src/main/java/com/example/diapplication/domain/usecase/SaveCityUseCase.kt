package com.example.diapplication.domain.usecase

import com.example.diapplication.data.repository.WeatherRepository
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend fun execute(location: String) {
        repository.saveUserCity(location)
    }
}