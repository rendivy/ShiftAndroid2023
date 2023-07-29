package com.example.diapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherIconButton(id = R.drawable.back_icon) {
                    navController.popBackStack()
                }
                WeatherIconButton(id = R.drawable.add_location){
                    navController.popBackStack()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = cityWeatherState.value[0].weather.location.name,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        fontSize = 24.sp,
                        color = Color(0xFFFFFFFF),
                    )
                    Text(
                        text = cityWeatherState.value[0].weather.current.temperatureCelsius.toInt().toString()
                                + stringResource(id = R.string.celsius),
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        fontSize = 20.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = cityWeatherState.value[0].weather.current.weatherCondition.text,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
                WeatherConditionImage(
                    weatherCondition = cityWeatherState.value[0].weather.current.weatherCondition.text,
                    modifier = Modifier
                        .padding(top = 32.dp, bottom = 8.dp)
                        .width(85.dp)
                        .height(85.dp)
                        .padding(16.dp).align(Alignment.CenterVertically)
                )
            }
        }
    }
}