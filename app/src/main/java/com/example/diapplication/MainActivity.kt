@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.diapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diapplication.presentation.ui.navigation.WeatherNavHost
import com.example.diapplication.presentation.ui.theme.DIapplicationTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        super.onCreate(savedInstanceState)
        setContent {
            DIapplicationTheme(darkTheme = false) {
                WeatherNavHost(
                    weatherViewModel = hiltViewModel(),
                    cityPredictViewModel = hiltViewModel(),
                    darkTheme = true,
                    fusedLocationProviderClient = fusedLocationProviderClient
                )


            }
        }

    }
}


