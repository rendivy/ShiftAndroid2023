package com.example.diapplication.view


import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.TopCenter
import com.example.diapplication.ui.theme.lightWeatherColor
import com.example.diapplication.ui.theme.weatherButtonText
import com.example.diapplication.viewModel.WeatherViewModel
import kotlinx.coroutines.runBlocking


@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    val weatherViewModel: WeatherViewModel = WeatherViewModel()
    WeatherScreen(weatherViewModel = weatherViewModel)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeatherScreen(weatherViewModel: WeatherViewModel) {
    var weatherCast by remember { mutableStateOf("--") }
    val cityName = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightWeatherColor)
    ) {
        Text(
            text = weatherCast, fontSize = 100.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 32.dp),
        )

        TextField(
            modifier = Modifier.align(TopCenter),
            value = cityName.value,
            onValueChange = { cityName.value = it },
            label = { Text("Enter city name") }
        )

        /*Не очень понимаю, куда стоит вынести данный функционал кнопки,
        т.к. он не относится к UI, а кнопка в целом должна быть глупенькая и просто вызывать какую-то функцию
        */
        Button(
                onClick = {

                    runBlocking { weatherViewModel.getWeather(cityName.value) }
                    weatherCast =
                        weatherViewModel.weather?.current?.temperatureCelsius.toString() + "°C"
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(text = weatherButtonText)
            }
        }
    }