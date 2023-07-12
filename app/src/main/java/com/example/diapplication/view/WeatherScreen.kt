package com.example.diapplication.view


import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.diapplication.R
import com.example.diapplication.ui.theme.SfProDisplay
import com.example.diapplication.ui.theme.lightWeatherColor
import com.example.diapplication.viewModel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel) {
    val cityName = remember { mutableStateOf("Angarsk") }
    val weatherState by weatherViewModel.weather.collectAsStateWithLifecycle()
    val isLoading by weatherViewModel.isLoading.collectAsStateWithLifecycle()
    val error by weatherViewModel.error.collectAsStateWithLifecycle(null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightWeatherColor),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (error == false) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.error_message),
                    fontFamily = SfProDisplay,
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        } else if (isLoading) {
            CircularProgressIndicator(modifier = Modifier)
        } else {
            weatherState?.location?.let {
                Text(
                    text = weatherState?.current?.temperatureCelsius.toString() + "°C",
                    fontSize = 100.sp,
                    fontFamily = SfProDisplay,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 32.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = weatherState?.location?.country.toString(),
                        fontFamily = SfProDisplay,
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = weatherState?.location?.region.toString() + " region",
                        fontFamily = SfProDisplay,
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = weatherState?.location?.name.toString(),
                        fontFamily = SfProDisplay,
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                Column(verticalArrangement = Arrangement.Center){
                    Text(
                        text = weatherState?.current?.weatherCondition?.text.toString(),
                        fontFamily = SfProDisplay,
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Log.i(TAG, weatherState?.current?.weatherCondition?.icon.toString())
                    LoadImageFromUrl(imageUrl = "https:"+ weatherState?.current?.weatherCondition?.icon.toString())

                    Text(
                        text = "Updated "+ weatherState?.current?.lastUpdated.toString(),
                        fontFamily = SfProDisplay,
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

            }
        }
        TextField(
            value = cityName.value,
            onValueChange = { cityName.value = it },
            label = { Text("Enter city name") }
        )

        Button(
            onClick = {
                weatherViewModel.updateWeatherData(cityName)
            },
        ) {
            Text(text = stringResource(id = R.string.weatherButtonText))
        }
    }
}
