package com.example.diapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.diapplication.presentation.UserViewModel
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.ui.theme.DIapplicationTheme
import com.example.diapplication.view.navigation.WeatherNavHost
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            val darkTheme by userViewModel.darkTheme.collectAsStateWithLifecycle()
            val database = Firebase.database
            val myRef = database.getReference("cities")

            LaunchedEffect(Unit) {
                userViewModel.getUserTheme()
            }
            FirebaseApp.initializeApp(this)

            DIapplicationTheme(darkTheme = darkTheme) {
                WeatherNavHost(
                    weatherViewModel = weatherViewModel,
                    userViewModel = userViewModel,
                    fusedLocationProviderClient = fusedLocationProviderClient,
                    database = database,
                    myRef = myRef,
                    darkTheme = darkTheme
                )

            }
        }

    }
}


