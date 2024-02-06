package com.example.diapplication.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diapplication.common.Constants
import com.example.diapplication.di.IoDispatcher
import com.example.diapplication.domain.usecase.GetSavedCityUseCase
import com.example.diapplication.domain.usecase.PredictCityUseCase
import com.example.diapplication.presentation.state.CitiesState
import com.example.diapplication.presentation.state.PredictedCityState
import com.example.diapplication.presentation.ui.state.AddLocationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSavedCityUseCase: GetSavedCityUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val predictCityUseCase: PredictCityUseCase,
) : ViewModel() {

    private val _citiesState = MutableStateFlow<CitiesState>(CitiesState.Initial)
    val citiesState: StateFlow<CitiesState> = _citiesState

    private val _predictedCitiesState =
        MutableStateFlow<PredictedCityState>(PredictedCityState.Initial)
    val predictedCitiesState: StateFlow<PredictedCityState> = _predictedCitiesState

    private val _cityNameState = mutableStateOf<AddLocationState>(
        AddLocationState()
    )
    val cityNameState = _cityNameState


    fun getSavedCities() {
        viewModelScope.launch(dispatcher) {
            val cities = getSavedCityUseCase.getSavedCity()
            _citiesState.value = CitiesState.Content(cities)
        }
    }

    fun addNewCity(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSavedCityUseCase.saveCity(cityName)
            _citiesState.value = CitiesState.Content(getSavedCityUseCase.getSavedCity())
            clearState()
        }
    }

    private fun clearState() {
        _cityNameState.value = AddLocationState()
        _citiesState.value = CitiesState.Initial
        _predictedCitiesState.value = PredictedCityState.Initial
    }

    fun getPredicted(location: String?) {
        viewModelScope.launch(dispatcher) {
            try {
                _predictedCitiesState.value = PredictedCityState.Loading
                if (location?.isNotEmpty() == true) {
                    delay(Constants.PREDICT_DELAY)
                    _predictedCitiesState.value =
                        PredictedCityState.Content(predictCityUseCase.invoke(location))
                }
            } catch (e: Exception) {
                _predictedCitiesState.value = PredictedCityState.Error
            }

        }
    }

    fun setCityName(cityName: String) {
        _cityNameState.value = AddLocationState(cityName)
    }


}