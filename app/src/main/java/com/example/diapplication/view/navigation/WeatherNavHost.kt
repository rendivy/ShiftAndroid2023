package com.example.diapplication.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diapplication.presentation.UserViewModel
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.view.AddLocationScreen
import com.example.diapplication.view.DetailsScreen
import com.example.diapplication.view.SettingsScreen
import com.example.diapplication.view.WeatherScreen
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


@Composable
fun WeatherNavHost(
    weatherViewModel: WeatherViewModel,
    userViewModel: UserViewModel,
    fusedLocationProviderClient: FusedLocationProviderClient,
    database: FirebaseDatabase,
    myRef: DatabaseReference,
    darkTheme: Boolean
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherNavRoutes.Home.route) {

        composable(WeatherNavRoutes.Home.route) {
            WeatherScreen(weatherViewModel, fusedLocationProviderClient, navController)
        }

        composable(WeatherNavRoutes.Search.route) {
            DetailsScreen(
                weatherViewModel = weatherViewModel,
                navController = navController,
                database = database
            )
        }

        composable("add_location_screen") {
            AddLocationScreen(
                weatherViewModel = weatherViewModel,
                navController = navController,
                dataBaseReference = myRef
            )
        }

        composable(WeatherNavRoutes.Settings.route) {
            SettingsScreen(
                navController = navController,
                darkTheme = darkTheme,
                userDataViewModel = userViewModel
            )
        }
    }


}