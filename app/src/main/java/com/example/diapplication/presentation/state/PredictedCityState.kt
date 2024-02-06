package com.example.diapplication.presentation.state

import com.example.diapplication.data.entity.Location

sealed class PredictedCityState {
    data object Initial : PredictedCityState()
    data class Content(val citiesList: List<Location>) : PredictedCityState()
    data object Loading : PredictedCityState()
    data object Error : PredictedCityState()
}