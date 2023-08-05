package com.example.diapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.diapplication.presentation.PredictViewModel
import com.example.diapplication.presentation.UserViewModel
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.ui.theme.DIapplicationTheme
import com.example.diapplication.view.navigation.WeatherNavHost
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private val predictViewModel: PredictViewModel by viewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            val darkTheme by userViewModel.darkTheme.collectAsStateWithLifecycle()
            val citiesState by weatherViewModel.citiesWeatherState.collectAsStateWithLifecycle()
            val context: Context = LocalContext.current
            val weatherState by weatherViewModel.weatherState.collectAsStateWithLifecycle()
            val permissionDenied = rememberSaveable { mutableStateOf(false) }
            val locationPermissionState =
                rememberPermissionState(permission = android.Manifest.permission.ACCESS_FINE_LOCATION)

            LaunchedEffect(Unit) {
                userViewModel.getUserTheme()
                weatherViewModel.updateUserGeolocation(
                    context = context,
                    fusedLocationProviderClient = fusedLocationProviderClient,
                    locationPermissionState = locationPermissionState,
                    permissionDenied = permissionDenied
                )
                weatherViewModel.getCities()
            }


            FirebaseApp.initializeApp(this)

            DIapplicationTheme(darkTheme = darkTheme) {
                WeatherNavHost(
                    weatherViewModel = weatherViewModel,
                    userViewModel = userViewModel,
                    predictViewModel = predictViewModel,
                    darkTheme = darkTheme,
                    citiesWeatherState = citiesState,
                    weatherState = weatherState,
                )

            }
        }

    }
}


