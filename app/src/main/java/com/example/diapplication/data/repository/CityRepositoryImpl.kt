package com.example.diapplication.data.repository

import com.example.diapplication.data.database.CityDatabase
import com.example.diapplication.data.database.entity.SavedCities
import com.example.diapplication.data.entity.Weather
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryImpl @Inject constructor(
    private val database: CityDatabase
) {
    val weatherChannel = Channel<Weather>(Channel.BUFFERED)

    suspend fun getAllCity(): List<SavedCities> =
        database.cityDao().getAllCities().toMutableList()



    suspend fun saveCity(cityName: String) {
        database.cityDao().insertCity(cityName)
    }


}