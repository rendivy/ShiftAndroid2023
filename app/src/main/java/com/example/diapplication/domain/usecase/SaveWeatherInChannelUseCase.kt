package com.example.diapplication.domain.usecase

import com.example.diapplication.data.entity.Weather
import com.example.diapplication.data.repository.CityRepositoryImpl
import javax.inject.Inject

class SaveCurrentCityInChannelUseCase @Inject constructor(
    private val repositoryImpl: CityRepositoryImpl) {

    suspend fun saveWeatherInChannel(weather: Weather) {
        repositoryImpl.weatherChannel.send(weather)
    }
}