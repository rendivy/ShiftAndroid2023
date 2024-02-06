package com.example.diapplication.presentation.ui.navigation

sealed class WeatherNavRoutes(val route: String) {
    data object Home : WeatherNavRoutes(route = "weatherScreen")
    data object Settings: WeatherNavRoutes(route = "settings")
    data object AddLocation: WeatherNavRoutes(route = "add_location_screen")
    data object Search: WeatherNavRoutes(route = "search/{temperatureCelsius}/{condition}/{location}")
}