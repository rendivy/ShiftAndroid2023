package com.example.diapplication.domain.usecase

import com.example.diapplication.data.entity.Location
import com.example.diapplication.data.repository.WeatherRepository
import javax.inject.Inject

class PredictCityUseCase @Inject constructor(val repository: WeatherRepository) {
    suspend operator fun invoke(location: String): List<Location> {
        return repository.getPredicted(location)
    }
}