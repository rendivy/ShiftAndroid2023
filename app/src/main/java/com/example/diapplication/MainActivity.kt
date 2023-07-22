package com.example.diapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.diapplication.presentation.UserViewModel
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.ui.theme.DIapplicationTheme
import com.example.diapplication.view.WeatherScreen
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            DIapplicationTheme {
                WeatherScreen(viewModel,userViewModel, fusedLocationProviderClient)
            }
        }
    }
}

