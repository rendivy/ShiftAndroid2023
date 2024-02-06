package com.example.diapplication.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.diapplication.common.Constants.EMPTY_STRING
import com.example.diapplication.presentation.CityPredictViewModel
import com.example.diapplication.presentation.DetailsViewModel
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.presentation.ui.WeatherScreen
import com.example.diapplication.presentation.ui.forecast.AddLocationSection
import com.example.diapplication.presentation.ui.forecast.DetailsScreen
import com.google.android.gms.location.FusedLocationProviderClient


@Composable
fun WeatherNavHost(
    weatherViewModel: WeatherViewModel,
    cityPredictViewModel: CityPredictViewModel,
    darkTheme: Boolean,
    fusedLocationProviderClient: FusedLocationProviderClient
) {
    val navController = rememberNavController()
    val detailsViewModel = hiltViewModel<DetailsViewModel>()

    NavHost(navController = navController, startDestination = WeatherNavRoutes.Home.route) {

        composable(WeatherNavRoutes.Home.route) {
            WeatherScreen(
                navController = navController,
                weatherViewModel = weatherViewModel,
                fusedLocationProviderClient = fusedLocationProviderClient
            )
        }

        composable(
            route = WeatherNavRoutes.Search.route,
            arguments = listOf(
                navArgument("temperatureCelsius") { type = NavType.StringType },
                navArgument("condition") { type = NavType.StringType },
                navArgument("location") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
                detailsViewModel = detailsViewModel,
                temperatureCelsius = backStackEntry.arguments?.getString("temperatureCelsius")
                    ?: EMPTY_STRING,
                condition = backStackEntry.arguments?.getString("condition") ?: EMPTY_STRING,
                location = backStackEntry.arguments?.getString("location") ?: EMPTY_STRING
            )
        }

        composable(WeatherNavRoutes.AddLocation.route) {
            AddLocationSection(
                navController = navController,
                detailsViewModel = detailsViewModel
            )
        }
    }
}