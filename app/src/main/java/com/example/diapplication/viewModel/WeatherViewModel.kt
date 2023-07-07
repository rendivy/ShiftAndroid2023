package com.example.diapplication.viewModel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diapplication.data.Weather
import com.example.diapplication.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    var weather: Weather? = null

    private suspend fun getWeather(location: String) {
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