package com.example.diapplication.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.diapplication.R
import com.example.diapplication.data.Hour
import com.example.diapplication.ui.theme.SfProDisplay
import com.example.diapplication.viewModel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel) {
    val cityName = remember { mutableStateOf("Los-Angeles") }
    val weatherState by weatherViewModel.weather.collectAsStateWithLifecycle()
    val isLoading by weatherViewModel.isLoading.collectAsStateWithLifecycle()
    val error by weatherViewModel.error.collectAsStateWithLifecycle(null)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize().background(color = Color.Black),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
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
                    MainWeatherData(weatherState = weatherState)
                    Spacer(modifier = Modifier.padding(8.dp))
                    WeatherForeCastScreen(weatherState = weatherState)
                    Spacer(modifier = Modifier.padding(16.dp))
                    HourScreen(weatherState = weatherState)
                    Spacer(modifier = Modifier.padding(16.dp))
                    AstroScreen(weatherState = weatherState)
                    Spacer(modifier = Modifier.padding(16.dp))
                    if (weatherState?.alerts?.alertList?.isNotEmpty() == true) {
                        GovernmentAlertButton(weatherState = weatherState)
                    }
                    OtherDaysScreen(weatherState = weatherState)
                }
            }
            TextField(
                modifier = Modifier
                    .padding(2.dp)
                    .background(color = Color.Transparent, shape = RoundedCornerShape(size = 18.dp))
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
                value = cityName.value,
                onValueChange = { cityName.value = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.city_name),
                        fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                        fontWeight = FontWeight(400),
                        fontSize = 20.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center)
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )
            Button(
                onClick = {
                    weatherViewModel.updateWeatherData(cityName)
                },
                modifier = Modifier
                    .padding(2.dp)
                    .background(color = Color.Transparent, shape = RoundedCornerShape(size = 18.dp))
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB200)),
            ) {
                Text(
                    text = stringResource(id = R.string.update_weather),
                    fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                    fontWeight = FontWeight(400),
                    fontSize = 20.sp,
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            }

        }
    }
}
