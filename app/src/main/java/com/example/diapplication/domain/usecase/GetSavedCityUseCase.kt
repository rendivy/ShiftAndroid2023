package com.example.diapplication.domain.usecase

import com.example.diapplication.data.EncryptedLocalStorage
import com.example.diapplication.data.entity.Weather
import com.example.diapplication.data.repository.CityRepositoryImpl
import com.example.diapplication.data.repository.WeatherRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetSavedCityUseCase @Inject constructor(
    private val cityRepositoryImpl: CityRepositoryImpl,
    private val weatherRepository: WeatherRepository,
    private val encryptedLocalStorage: EncryptedLocalStorage
) {

    suspend fun saveCity(cityName: String) {
        cityRepositoryImpl.saveCity(cityName)
    }

    suspend fun getSavedCity(): List<Weather> = coroutineScope {
        val savedCities = cityRepositoryImpl.getAllCity()
        val savedList: MutableList<Weather> = mutableListOf()

        val deferredList = savedCities.map { city ->
            async {
                weatherRepository.getCurrentWeather(city.cityName)
            }
        }

        deferredList.forEach { deferred ->
            val currentWeather = deferred.await()
            savedList.add(currentWeather)
        }
        savedList
    }
}