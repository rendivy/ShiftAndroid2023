package com.example.diapplication.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.diapplication.R
import com.example.diapplication.ui.theme.lightWeatherColor
import com.example.diapplication.ui.theme.weatherButtonText
import com.example.diapplication.viewModel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel) {
    val weatherCast = remember { mutableStateOf("--") }
    val cityName = remember { mutableStateOf("") }
    val weatherState by weatherViewModel.weather.collectAsStateWithLifecycle()
    val isLoading by weatherViewModel.isLoading.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightWeatherColor)
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Text(
                text = weatherCast.value,
                fontSize = 100.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 32.dp)
            )
        }

        TextField(
            modifier = Modifier.align(TopCenter),
            value = cityName.value,
            onValueChange = { cityName.value = it },
            label = { Text("Enter city name") }
        )

        Button(
            onClick = {
                weatherViewModel.updateWeatherData(weatherCast, cityName)
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = stringResource(id = R.string.weatherButtonText))
        }

        LaunchedEffect(weatherState) {
            weatherState?.let {
                weatherCast.value = it.current.temperatureCelsius.toString() + "°C"
            }
        }
    }
}
