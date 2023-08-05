@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.diapplication.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.diapplication.R
import com.example.diapplication.data.model.DateConverter
import com.example.diapplication.domain.entity.Weather
import com.example.diapplication.presentation.PredictViewModel
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.view.buttons.WeatherIconButton

@Composable
fun ForecastWeatherScreen(weatherState: Weather?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        HourlyForecastScreen(weatherState = weatherState)
        Spacer(modifier = Modifier.height(32.dp))
        DailyForecastScreen(weatherState = weatherState)
    }
}

@Composable
fun HourlyForecastScreen(weatherState: Weather?) {
    val lazyListState = rememberLazyListState()
    Text(
        text = stringResource(id = R.string.hourly_forecast),
        style = TextStyle(
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
            fontWeight = FontWeight(400),
            color = MaterialTheme.colorScheme.tertiary,
        ), modifier = Modifier.padding(start = 32.dp, bottom = 16.dp)
    )
    LazyRow(
        state = lazyListState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp)
            .fillMaxWidth()
    ) {
        for (i in weatherState?.forecast?.forecastDayList?.get(0)?.hourList?.indices!!) {
            item {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = "$i" + stringResource(id = R.string.hour_minute),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(400),
                            color = MaterialTheme.colorScheme.secondary,
                        ),
                    )
                    WeatherConditionImage(
                        weatherCondition = weatherState.forecast.forecastDayList[0].hourList[i].condition.text,
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp)
                            .size(36.dp)

                    )
                    Text(
                        text = weatherState.forecast.forecastDayList[0].hourList[i].tempC.toString() +
                                stringResource(id = R.string.celsius),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(400),
                            color = MaterialTheme.colorScheme.tertiary,
                        ),
                    )
                }
            }

        }
    }
}


@Composable
fun DailyForecastScreen(weatherState: Weather?) {
    Text(
        text = stringResource(id = R.string.daily_forecast),
        style = TextStyle(
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
            fontWeight = FontWeight(400),
            color = MaterialTheme.colorScheme.tertiary,
        ), modifier = Modifier.padding(start = 32.dp, bottom = 16.dp)
    )
    LazyRow(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (j in 0..2) {
            for (i in 0 until weatherState?.forecast?.forecastDayList!!.size) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = DateConverter.invertDate(weatherState.forecast.forecastDayList[i].date),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                fontWeight = FontWeight(400),
                                color = MaterialTheme.colorScheme.secondary,
                            ),
                        )
                        WeatherConditionImage(
                            weatherCondition = weatherState.forecast.forecastDayList[i].day.condition.text,
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp)
                                .size(36.dp)

                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.down_arrow),
                                contentDescription = "down_arrow",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = weatherState.forecast.forecastDayList[i].day.minimumTemperature.toInt()
                                    .toString() + stringResource(id = R.string.celsius),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                    fontWeight = FontWeight(400),
                                    color = MaterialTheme.colorScheme.tertiary,
                                ),
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.up_arrow),
                                contentDescription = "down_arrow",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = weatherState.forecast.forecastDayList[i].day.maximumTemperature.toInt()
                                    .toString() + stringResource(id = R.string.celsius),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                    fontWeight = FontWeight(400),
                                    color = MaterialTheme.colorScheme.tertiary,
                                ),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}


@Composable
fun AddLocationScreen(
    weatherViewModel: WeatherViewModel, navController: NavController,
    predictViewModel: PredictViewModel
) {
    var city by remember { mutableStateOf("") }
    val predictedCities by predictViewModel.predicted.collectAsStateWithLifecycle()
    val isVisible by remember { mutableStateOf(false) }
    val errorState by weatherViewModel.anotherCityError.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)

    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            WeatherIconButton(id = R.drawable.back_icon) {
                navController.popBackStack()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(32.dp),
                text = errorState.toString(),
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(400),
                    color = MaterialTheme.colorScheme.secondary,
                ),
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = city,
                onValueChange = { newValue ->
                    city = newValue
                    predictViewModel.getPredicted(newValue)
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.city_placeholder),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(400),
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(400),
                    color = MaterialTheme.colorScheme.secondary,
                ),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.secondary,
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = MaterialTheme.colorScheme.secondary
                ),
            )
            AnimatedVisibility(
                visible = predictedCities.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    items(predictedCities.size) {
                        Text(
                            text = predictedCities[it].name, modifier = Modifier
                                .fillMaxWidth().padding(8.dp)
                                .clickable {
                                    city = predictedCities[it].name
                                    predictViewModel.clearPredicted()
                                },
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                fontWeight = FontWeight(400),
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        weatherViewModel.updateAnotherCityWeather(
                            cityName = city,
                        )
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.upload_cloud),
                    contentDescription = "add",
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(id = R.string.add_placeholder),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colorScheme.secondary,
                    )
                )
            }
        }

    }

}


@Composable
fun DetailsScreen(weatherState: Weather?) {
    Column(modifier = Modifier.padding(start = 32.dp)) {
        Text(
            text = stringResource(id = R.string.precipitation),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.tertiary,
            ),
        )
        Text(
            text = weatherState?.forecast?.forecastDayList?.get(0)?.totalPrecipitationMm.toString() +
                    " " + stringResource(id = R.string.mm),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
    Column(modifier = Modifier.padding(start = 32.dp, top = 8.dp)) {
        Text(
            text = weatherState?.current?.windDirection.toString() +
                    " " + stringResource(id = R.string.wind),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.tertiary,
            ),
        )
        Text(
            text = weatherState?.current?.windKph.toString() +
                    " " + stringResource(id = R.string.kmh),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
    Column(modifier = Modifier.padding(start = 32.dp, top = 8.dp)) {
        Text(
            text = stringResource(id = R.string.humidity),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.tertiary,
            ),
        )
        Text(
            text = weatherState?.current?.humidity.toString() +
                    " " + stringResource(id = R.string.percent),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
    Column(modifier = Modifier.padding(start = 32.dp, top = 8.dp)) {
        Text(
            text = stringResource(id = R.string.UV),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.tertiary
            ),
        )
        Text(
            text = weatherState?.current?.uv.toString(),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
    Column(modifier = Modifier.padding(start = 32.dp, top = 8.dp)) {
        Text(
            text = "Feels Like",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.tertiary,
            ),
        )
        Text(
            text = weatherState?.current?.feelsLikeCelsius?.toInt().toString()
                    + stringResource(id = R.string.celsius),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
    Column(modifier = Modifier.padding(start = 32.dp, top = 8.dp)) {
        Text(
            text = stringResource(id = R.string.pressure),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.tertiary,
            ),
        )
        Text(
            text = weatherState?.current?.pressureMb.toString() +
                    " " + stringResource(id = R.string.pressure_mb),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
}