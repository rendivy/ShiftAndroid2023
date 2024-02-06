package com.example.diapplication.data.repository

import com.example.diapplication.data.EncryptedLocalStorage
import com.example.diapplication.data.database.CityDatabase
import com.example.diapplication.data.entity.Location
import com.example.diapplication.data.entity.Weather
import com.example.diapplication.data.remote.WeatherApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepository @Inject constructor(
    private val apiService: WeatherApiService,
    private val cityDatabase: CityDatabase,
    private val encryptedLocalStorage: EncryptedLocalStorage
) {


    suspend fun saveUserCity(location: String) {
        cityDatabase.cityDao().insertCity(location)
    }

    suspend fun getCurrentWeather(location: String): Weather {
        encryptedLocalStorage.saveCurrentCoordinates(location)
        return apiService.getCurrentWeather(q = location)
    }

    suspend fun getPredicted(location: String?): List<Location> {
        return apiService.getPredict(q = location)
    }
}