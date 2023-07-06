package com.example.diapplication.viewModel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diapplication.data.Weather
import com.example.diapplication.data.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()
    var weather: Weather? = null

    suspend fun getWeather(location: String) {
        weather = repository.getCurrentWeather(location)
    }

    fun updateWeatherData(
        weatherCast: MutableState<String>,
        cityName: MutableState<String>
    ) {
        viewModelScope.launch { getWeather(cityName.value) }
        weatherCast.value = weather?.current?.temperatureCelsius.toString() + "°C"

    }

}