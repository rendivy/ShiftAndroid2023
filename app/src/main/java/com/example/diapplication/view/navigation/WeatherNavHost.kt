package com.example.diapplication.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diapplication.presentation.CityPredictViewModel
import com.example.diapplication.presentation.UserViewModel
import com.example.diapplication.presentation.WeatherState
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.view.AddLocationScreen
import com.example.diapplication.view.DetailsScreen
import com.example.diapplication.view.settings.SettingsScreen
import com.example.diapplication.view.WeatherScreen


@Composable
fun WeatherNavHost(
    weatherViewModel: WeatherViewModel,
    userViewModel: UserViewModel,
    cityPredictViewModel: CityPredictViewModel,
    citiesWeatherState: List<WeatherState.Content>,
    darkTheme: Boolean,
    weatherState: WeatherState
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherNavRoutes.Home.route) {

        composable(WeatherNavRoutes.Home.route) {
            WeatherScreen(
                navController = navController,
                weatherState =  weatherState
            )
        }

        composable(WeatherNavRoutes.Search.route) {
            DetailsScreen(
                navController = navController,
                additionCitiesList = citiesWeatherState,
            )
        }

        composable("add_location_screen") {
            AddLocationScreen(
                weatherViewModel = weatherViewModel,
                navController = navController,
                cityPredictViewModel = cityPredictViewModel
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