package com.example.diapplication.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diapplication.common.Constants
import com.example.diapplication.di.IoDispatcher
import com.example.diapplication.domain.usecase.GetCityWeatherUseCase
import com.example.diapplication.domain.usecase.SaveCurrentCityInChannelUseCase
import com.example.diapplication.presentation.state.WeatherState
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val saveCurrentCityInChannelUseCase: SaveCurrentCityInChannelUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Initial)
    val weatherState: StateFlow<WeatherState> = _weatherState



    private fun getCityWeather(cityName: String) {
        viewModelScope.launch(ioDispatcher) {
            val weather = getCityWeatherUseCase.invoke(cityName)
            _weatherState.value = WeatherState.Content(weather)
            saveCurrentCityInChannelUseCase.saveWeatherInChannel(weather)
        }
    }


    fun updateUserGeolocation(
        context: Context,
        fusedLocationProviderClient: FusedLocationProviderClient,
    ) {
        _weatherState.value = WeatherState.Loading
        viewModelScope.launch(ioDispatcher) {
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
                            getCityWeather("${it.latitude},${it.longitude}")
                        }
                    }
            } else {
                _weatherState.value = WeatherState.PermissionDenied
            }
        }
    }
}
