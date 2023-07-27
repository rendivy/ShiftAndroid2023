package com.example.diapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.diapplication.R
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.view.buttons.WeatherIconButton


@Composable
fun DetailsWeatherScreen(weatherViewModel: WeatherViewModel, navController: NavController) {
    val cityWeatherState = weatherViewModel.citiesWeatherState.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column {
                    WeatherIconButton(id = R.drawable.settings_icon) { navController.popBackStack() }
                    Text(
                        text = cityWeatherState.value[0].weather.location.name,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        fontSize = 24.sp,
                        color = Color(0xFFFFFFFF),
                    )
                    Text(
                        text = "22C",
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        fontSize = 24.sp,
                        color = Color(0xFFFFFFFF)
                    )
                    Text(
                        text = "Cloudy",
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        fontSize = 24.sp,
                        color = Color(0xFFFFFFFF)
                    )
                }
            }
        }
    }
}