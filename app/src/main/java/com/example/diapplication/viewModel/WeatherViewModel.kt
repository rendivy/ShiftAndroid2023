package com.example.diapplication.viewModel

import androidx.lifecycle.ViewModel
import com.example.diapplication.model.Weather
import com.example.diapplication.model.WeatherRepository

class WeatherViewModel: ViewModel() {
    private val repository = WeatherRepository()
    var weather: Weather? = null

    suspend fun getWeather(location: String){
        weather = repository.getCurrentWeather(location)
    }
}