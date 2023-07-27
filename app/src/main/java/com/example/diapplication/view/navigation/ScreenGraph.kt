package com.example.diapplication.view.navigation

sealed class ScreenGraph(val route: String) {
    object Home : ScreenGraph(route = "weatherScreen")
    object Settings: ScreenGraph(route = "settings")
    object Search: ScreenGraph(route = "search") {
    }
}