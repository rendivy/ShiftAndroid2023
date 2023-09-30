package com.example.diapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diapplication.domain.entity.Location
import com.example.diapplication.domain.usecase.PredictCityUseCase
import com.example.diapplication.domain.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityPredictViewModel @Inject constructor(
    private val cityUseCase: PredictCityUseCase,
) : ViewModel(){

    private val _predictedCitiesState = MutableStateFlow(listOf<Location>())
    val predictedCitiesState = _predictedCitiesState

    fun getPredicted(location: String?){
        viewModelScope.launch {
            if (location?.isNotEmpty() == true){
                delay(Constants.PREDICT_DELAY)
                _predictedCitiesState.value = cityUseCase.invoke(location)
            }

        }
    }

    fun clearPredicted(){
        _predictedCitiesState.value = listOf()
    }
}