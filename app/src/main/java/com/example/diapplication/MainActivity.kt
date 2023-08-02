package com.example.diapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.ui.theme.DIapplicationTheme
import com.example.diapplication.view.AddLocationScreen
import com.example.diapplication.view.DetailsScreen
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
            DIapplicationTheme {
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
                }

            }
        }
    }
}

