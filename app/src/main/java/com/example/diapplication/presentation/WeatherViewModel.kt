package com.example.diapplication.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diapplication.domain.usecase.UpdateWeatherUseCase
import com.example.diapplication.domain.utils.Constants
import com.example.diapplication.domain.utils.UiError
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(ExperimentalPermissionsApi::class)
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val updateWeatherUseCase: UpdateWeatherUseCase,
    private val citiesReference: DatabaseReference) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    private val _citiesWeatherState = MutableStateFlow<MutableList<WeatherState.Content>>(mutableListOf())
    val citiesWeatherState: StateFlow<List<WeatherState.Content>> = _citiesWeatherState

    private val _anotherCityError = MutableStateFlow<String?>(Constants.EMPTY_STRING)
    val anotherCityError: StateFlow<String?> = _anotherCityError

    private fun updateCities() {
        citiesReference.setValue(_citiesWeatherState.value.map { it.weather.location.name })
    }

    private fun getUserCities() {
        citiesReference.get().addOnSuccessListener {
            if (it.value != null){
                val cities = it.value as List<String>
                cities.forEach { cityName ->
                    updateAnotherCityWeather(cityName)
                }
            }
        }.addOnFailureListener {
            _weatherState.value = WeatherState.Error(true)
        }
    }

    private fun updateWeatherData(cityName: String) {
        viewModelScope.launch {
            _weatherState.value = WeatherState.Loading
            try {
                val weather = updateWeatherUseCase(cityName)
                _weatherState.value = WeatherState.Content(weather)
                if (!_citiesWeatherState.value.contains(WeatherState.Content(weather))) {
                    _citiesWeatherState.value.add(0, WeatherState.Content(weather))
                }
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(true)
            }
        }
    }

    fun clearErrorState(){
        _anotherCityError.value = Constants.EMPTY_STRING
    }

    fun getCities() {
        viewModelScope.launch {
            getUserCities()
        }
    }

    fun updateAnotherCityWeather(cityName: String) {
        viewModelScope.launch {
            try {
                val weather = updateWeatherUseCase(cityName)
                val newWeatherState = WeatherState.Content(weather)
                if (!_citiesWeatherState.value.contains(newWeatherState)) {
                    _citiesWeatherState.value.add(WeatherState.Content(weather))
                    updateCities()
                    _anotherCityError.value = Constants.EMPTY_STRING
                } else {
                    _anotherCityError.value = UiError.CITY_ALREADY_EXISTS.message
                }
            } catch (e: Exception) {
                _anotherCityError.value = UiError.CITY_NOT_FOUND.message
            }
        }
    }


    fun updateUserGeolocation(
        fusedLocationProviderClient: FusedLocationProviderClient,
        locationPermissionState: PermissionState,
        context: Context,
        permissionDenied: MutableState<Boolean>
    ) {
        viewModelScope.launch {
            if (!locationPermissionState.status.isGranted) {
                locationPermissionState.launchPermissionRequest()
            } else {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    fusedLocationProviderClient.getCurrentLocation(Constants.PRIORITY, null)
                        .addOnSuccessListener { location: Location? ->
                            location?.let {
                                updateWeatherData("${location.latitude},${location.longitude}")
                                permissionDenied.value = false
                            }
                        }
                } else {
                    permissionDenied.value = true
                }
            }
        }
    }
}