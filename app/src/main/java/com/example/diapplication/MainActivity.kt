package com.example.diapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.diapplication.ui.theme.DIapplicationTheme
import com.example.diapplication.view.WeatherScreen
import com.example.diapplication.viewModel.WeatherViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DIapplicationTheme {
                WeatherScreen(viewModel)
            }
        }
    }
}

