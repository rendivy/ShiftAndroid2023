package com.example.diapplication.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.diapplication.domain.usecase.UpdateWeatherUseCase
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(ExperimentalPermissionsApi::class)
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val updateWeatherUseCase: UpdateWeatherUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState


    private val _citiesWeatherState =
        MutableStateFlow<MutableList<WeatherState.Content>>(mutableListOf())
    val citiesWeatherState: StateFlow<List<WeatherState.Content>> = _citiesWeatherState


    private val _anotherCityError = MutableStateFlow<String?>("")
    val anotherCityError: StateFlow<String?> = _anotherCityError

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

    fun updateAnotherCityWeather(cityName: String, navController: NavController) {
        viewModelScope.launch {
            try {
                val weather = updateWeatherUseCase(cityName)
                val newWeatherState = WeatherState.Content(weather)
                if (!_citiesWeatherState.value.contains(newWeatherState)) {
                    _citiesWeatherState.value.add(WeatherState.Content(weather))
                    _anotherCityError.value = ""
                    navController.popBackStack()
                } else {
                    _anotherCityError.value = "City already added"
                }
            } catch (e: Exception) {
                _anotherCityError.value = "City not found, please check correctness of city name"
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
            }
            else {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    fusedLocationProviderClient.getCurrentLocation(100, null)
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