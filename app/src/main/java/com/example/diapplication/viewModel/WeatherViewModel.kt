package com.example.diapplication.viewModel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.diapplication.model.Weather
import com.example.diapplication.model.WeatherRepository
import kotlinx.coroutines.runBlocking

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()
    var weather: Weather? = null

    suspend fun getWeather(location: String) {
        weather = repository.getCurrentWeather(location)
    }

    fun updateWeatherData(
        weatherViewModel: WeatherViewModel, weatherCast: MutableState<String>,
        cityName: MutableState<String>
    ) {
        runBlocking { weatherViewModel.getWeather(cityName.value) }
        weatherCast.value =
            weatherViewModel.weather?.current?.temperatureCelsius.toString() + "°C"

    }

}