package com.example.diapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diapplication.data.repository.WeatherRepository
import com.example.diapplication.domain.entity.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PredictViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel(){

    private val predictedState = MutableStateFlow(listOf<Location>())
    val predicted = predictedState

    fun getPredicted(location: String?){
        viewModelScope.launch {
            if (location?.isNotEmpty() == true){
                delay(300)
                predictedState.value = weatherRepository.getPredicted(location)
            }

        }
    }

    fun clearPredicted(){
        predictedState.value = listOf()
    }
}