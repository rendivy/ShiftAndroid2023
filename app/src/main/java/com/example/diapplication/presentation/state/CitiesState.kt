package com.example.diapplication.presentation.state

import com.example.diapplication.data.entity.Weather

sealed class CitiesState {
    data object Initial : CitiesState()
    data object Loading : CitiesState()
    data object Error : CitiesState()
    data class Content(val citiesList: List<Weather>) : CitiesState()
}