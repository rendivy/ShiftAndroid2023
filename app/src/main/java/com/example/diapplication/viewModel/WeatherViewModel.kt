package com.example.diapplication.viewModel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diapplication.data.Weather
import com.example.diapplication.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> = _weather

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<Boolean?>(null)
    val error: StateFlow<Boolean?> = _error


    private suspend fun getWeather(location: String) {
        _weather.value = repository.getCurrentWeather(location)
    }

    fun updateWeatherData(
        cityName: MutableState<String>
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                getWeather(cityName.value)
                _error.value = true
            } catch (e: Exception) {
                _error.value = false
            }
            _isLoading.value = false
        }

    }
}