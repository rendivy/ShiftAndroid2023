package com.example.diapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diapplication.data.UserGeocoding
import com.example.diapplication.data.Weather
import com.example.diapplication.data.repository.ClientRepository
import com.example.diapplication.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val clientRepository: ClientRepository
) :
    ViewModel() {


    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> = _weather

    private val _userGeocoding = MutableStateFlow<UserGeocoding?>(null)
    val userGeocoding: StateFlow<UserGeocoding?> = _userGeocoding

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<Boolean?>(null)
    val error: StateFlow<Boolean?> = _error

    private suspend fun getClientLocation(latitude: Double, longitude: Double) {
        _userGeocoding.value = clientRepository.getClientService(latitude, longitude)
    }

    private suspend fun getWeather(location: String) {
        _weather.value = repository.getCurrentWeather(location)
    }

    fun updateUserInfo(
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            getClientLocation(latitude, longitude)
        }

    }

    fun updateWeatherData(
        cityName: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                getWeather(cityName)
                _error.value = false
            } catch (e: Exception) {
                _error.value = true
            }
            _isLoading.value = false
        }

    }
}