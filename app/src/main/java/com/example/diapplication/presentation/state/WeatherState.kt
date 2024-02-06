package com.example.diapplication.presentation.state

import com.example.diapplication.data.entity.Weather

sealed class WeatherState {
    data object Initial : WeatherState()
    data object PermissionDenied : WeatherState()
    data object Loading : WeatherState()
    data object Error : WeatherState()
    data class Content(val weather: Weather) : WeatherState()
}