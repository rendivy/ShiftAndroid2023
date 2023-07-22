package com.example.diapplication.presentation

import com.example.diapplication.domain.entity.Weather

sealed class WeatherState {
    object Loading : WeatherState()
    data class Content(val weather: Weather) : WeatherState()
    data class Error(val error: Boolean) : WeatherState()
}