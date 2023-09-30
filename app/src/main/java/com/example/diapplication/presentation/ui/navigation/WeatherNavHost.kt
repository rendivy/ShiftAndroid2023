package com.example.diapplication.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diapplication.presentation.CityPredictViewModel
import com.example.diapplication.presentation.UserViewModel
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.presentation.ui.WeatherScreen
import com.example.diapplication.presentation.ui.forecast.AddLocationScreen
import com.example.diapplication.presentation.ui.forecast.DetailsScreen
import com.example.diapplication.presentation.ui.settings.SettingsScreen


@Composable
fun WeatherNavHost(
    weatherViewModel: WeatherViewModel,
    userViewModel: UserViewModel,
    cityPredictViewModel: CityPredictViewModel,
    darkTheme: Boolean,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherNavRoutes.Home.route) {

        composable(WeatherNavRoutes.Home.route) {
            WeatherScreen(
                navController = navController,
                weatherViewModel = weatherViewModel,
                )
        }

        composable(WeatherNavRoutes.Search.route) {
            DetailsScreen(
                navController = navController,
                weatherViewModel = weatherViewModel,
            )
        }

        composable("add_location_screen") {
            AddLocationScreen(
                weatherViewModel = weatherViewModel,
                navController = navController,
                cityPredictViewModel = cityPredictViewModel,
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