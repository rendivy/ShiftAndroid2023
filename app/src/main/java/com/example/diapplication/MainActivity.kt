@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.diapplication

import android.Manifest.permission.ACCESS_FINE_LOCATION
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
import com.example.diapplication.presentation.CityPredictViewModel
import com.example.diapplication.presentation.UserViewModel
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.presentation.ui.navigation.WeatherNavHost
import com.example.diapplication.ui.theme.DIapplicationTheme
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
    private val cityPredictViewModel: CityPredictViewModel by viewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            val darkTheme by userViewModel.darkTheme.collectAsStateWithLifecycle()
            val context: Context = LocalContext.current

            val permissionDenied = rememberSaveable { mutableStateOf(false) }
            val locationPermissionState = rememberPermissionState(permission = ACCESS_FINE_LOCATION)

            FirebaseApp.initializeApp(this)
            userViewModel.getUserTheme()
            weatherViewModel.getCities()
            LaunchedEffect(Unit) {
                weatherViewModel.updateUserGeolocation(
                    context = context,
                    fusedLocationProviderClient = fusedLocationProviderClient,
                    locationPermissionState = locationPermissionState,
                    permissionDenied = permissionDenied,
                )

            }

            DIapplicationTheme(darkTheme = darkTheme) {
                WeatherNavHost(
                    weatherViewModel = weatherViewModel,
                    userViewModel = userViewModel,
                    cityPredictViewModel = cityPredictViewModel,
                    darkTheme = darkTheme,
                )

            }
        }

    }
}


