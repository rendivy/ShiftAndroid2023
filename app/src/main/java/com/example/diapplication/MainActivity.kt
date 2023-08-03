package com.example.diapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.ui.theme.DIapplicationTheme
import com.example.diapplication.view.AddLocationScreen
import com.example.diapplication.view.DetailsScreen
import com.example.diapplication.view.SettingsScreen
import com.example.diapplication.view.WeatherScreen
import com.example.diapplication.view.navigation.ScreenGraph
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var navController: NavHostController
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            val darkTheme = remember { mutableStateOf(false) }
            DIapplicationTheme(darkTheme = darkTheme.value) {
                navController = rememberNavController()
                NavHost(navController = navController, startDestination = ScreenGraph.Home.route) {
                    composable(ScreenGraph.Home.route) {
                        WeatherScreen(viewModel, fusedLocationProviderClient, navController)
                    }
                    composable(ScreenGraph.Search.route) {
                        DetailsScreen(viewModel, navController)
                    }
                    composable("add_location_screen") {
                        AddLocationScreen(
                            weatherViewModel = viewModel,
                            navController = navController
                        )
                    }
                    composable(ScreenGraph.Settings.route) {
                        SettingsScreen(navController = navController,
                            darkTheme = darkTheme,

                            )
                    }
                }

            }
        }
    }
}

