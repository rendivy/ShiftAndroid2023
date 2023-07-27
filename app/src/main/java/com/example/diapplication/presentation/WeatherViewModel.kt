package com.example.diapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diapplication.domain.usecase.UpdateWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val updateWeatherUseCase: UpdateWeatherUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    private val _citiesWeatherState = MutableStateFlow<MutableList<WeatherState.Content>>(mutableListOf())
    val citiesWeatherState: StateFlow<List<WeatherState.Content>> = _citiesWeatherState

    fun updateWeatherData(cityName: String) {
        viewModelScope.launch {
            _weatherState.value = WeatherState.Loading
            try {
                val weather = updateWeatherUseCase(cityName)
                _weatherState.value = WeatherState.Content(weather)
                _citiesWeatherState.value.add(0, WeatherState.Content(weather))
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(true)
            }
        }
    }



}