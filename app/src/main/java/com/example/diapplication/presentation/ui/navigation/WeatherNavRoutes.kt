package com.example.diapplication.presentation.ui.navigation

sealed class WeatherNavRoutes(val route: String) {
    object Home : WeatherNavRoutes(route = "weatherScreen")
    object Settings: WeatherNavRoutes(route = "settings")
    object Search: WeatherNavRoutes(route = "search") {
    }
}