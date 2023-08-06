@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.diapplication.view.forecast

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.diapplication.R
import com.example.diapplication.data.model.DateConverter
import com.example.diapplication.domain.entity.Weather
import com.example.diapplication.domain.utils.Constants
import com.example.diapplication.presentation.CityPredictViewModel
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.ui.theme.MediumFont
import com.example.diapplication.ui.theme.PartSmallFont
import com.example.diapplication.ui.theme.PlusMediumIconSize
import com.example.diapplication.ui.theme.RegularFont
import com.example.diapplication.ui.theme.SmallFont
import com.example.diapplication.ui.theme.SpacingMedium
import com.example.diapplication.ui.theme.SpacingPartTiny
import com.example.diapplication.ui.theme.SpacingRegular
import com.example.diapplication.ui.theme.SpacingSmall
import com.example.diapplication.ui.theme.ThickFont
import com.example.diapplication.ui.theme.UbuntuBold
import com.example.diapplication.ui.theme.tinyIconSize
import com.example.diapplication.view.utils.WeatherConditionImage
import com.example.diapplication.view.utils.WeatherIconButton

@Composable
fun ForecastWeatherScreen(weatherState: Weather?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        HourlyForecastScreen(weatherState = weatherState)
        Spacer(modifier = Modifier.height(SpacingMedium))
        DailyForecastScreen(weatherState = weatherState)
    }
}

@Composable
fun HourlyForecastScreen(weatherState: Weather?) {
    val lazyListState = rememberLazyListState()
    Text(
        text = stringResource(id = R.string.hourly_forecast),
        style = TextStyle(
            fontSize = PartSmallFont,
            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
            fontWeight = FontWeight(UbuntuBold),
            color = MaterialTheme.colorScheme.tertiary,
        ), modifier = Modifier.padding(start = SpacingMedium, bottom = SpacingSmall)
    )
    LazyRow(
        state = lazyListState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(start = SpacingMedium, end = SpacingMedium)
            .fillMaxWidth()
    ) {
        for (i in weatherState?.forecast?.forecastDayList?.get(0)?.hourList?.indices!!) {
            item {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = SpacingPartTiny, end = SpacingPartTiny)
                ) {
                    Text(
                        text = "$i" + stringResource(id = R.string.hour_minute),
                        style = TextStyle(
                            fontSize = SmallFont,
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(UbuntuBold),
                            color = MaterialTheme.colorScheme.secondary,
                        ),
                    )
                    WeatherConditionImage(
                        weatherCondition = weatherState.forecast.forecastDayList[0].hourList[i].condition.text,
                        modifier = Modifier
                            .padding(top = SpacingPartTiny, bottom = SpacingPartTiny)
                            .size(PlusMediumIconSize)

                    )
                    Text(
                        text = weatherState.forecast.forecastDayList[0].hourList[i].tempC.toString() +
                                stringResource(id = R.string.celsius),
                        style = TextStyle(
                            fontSize = SmallFont,
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(UbuntuBold),
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
            fontSize = PartSmallFont,
            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
            fontWeight = FontWeight(UbuntuBold),
            color = MaterialTheme.colorScheme.tertiary,
        ), modifier = Modifier.padding(start = SpacingMedium, bottom = SpacingSmall)
    )
    LazyRow(
        modifier = Modifier
            .padding(start = SpacingMedium, end = SpacingMedium)
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
                                fontSize = SmallFont,
                                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                fontWeight = FontWeight(UbuntuBold),
                                color = MaterialTheme.colorScheme.secondary,
                            ),
                        )
                        WeatherConditionImage(
                            weatherCondition = weatherState.forecast.forecastDayList[i].day.condition.text,
                            modifier = Modifier
                                .padding(top = SpacingPartTiny, bottom = SpacingPartTiny)
                                .size(PlusMediumIconSize)

                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.down_arrow),
                                contentDescription = "down_arrow",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(tinyIconSize)
                            )
                            Text(
                                text = weatherState.forecast.forecastDayList[i].day.minimumTemperature.toInt()
                                    .toString() + stringResource(id = R.string.celsius),
                                style = TextStyle(
                                    fontSize = ThickFont,
                                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                    fontWeight = FontWeight(UbuntuBold),
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
                                modifier = Modifier.size(tinyIconSize)
                            )
                            Text(
                                text = weatherState.forecast.forecastDayList[i].day.maximumTemperature.toInt()
                                    .toString() + stringResource(id = R.string.celsius),
                                style = TextStyle(
                                    fontSize = ThickFont,
                                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                    fontWeight = FontWeight(UbuntuBold),
                                    color = MaterialTheme.colorScheme.tertiary,
                                ),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(SpacingSmall))
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLocationScreen(
    weatherViewModel: WeatherViewModel, navController: NavController,
    cityPredictViewModel: CityPredictViewModel
) {
    var city by remember { mutableStateOf(Constants.EMPTY_STRING) }
    val predictedCities by cityPredictViewModel.predictedCitiesState.collectAsStateWithLifecycle()
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
                .padding(SpacingMedium)
        ) {
            WeatherIconButton(id = R.drawable.back_icon) {
                navController.popBackStack()
                cityPredictViewModel.clearPredicted()
                weatherViewModel.clearErrorState()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier.padding(SpacingMedium),
                text = errorState.toString(),
                style = TextStyle(
                    fontSize = MediumFont,
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
                    color = MaterialTheme.colorScheme.secondary,
                ),
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = city,
                onValueChange = { newValue ->
                    city = newValue
                    cityPredictViewModel.getPredicted(newValue)
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.city_placeholder),
                        style = TextStyle(
                            fontSize = SmallFont,
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(UbuntuBold),
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(
                    fontSize = SmallFont,
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(SpacingRegular),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    items(predictedCities.size) {
                        Text(
                            text = predictedCities[it].name, modifier = Modifier
                                .fillMaxWidth()
                                .padding(SpacingPartTiny)
                                .clickable {
                                    city = predictedCities[it].name
                                    cityPredictViewModel.clearPredicted()
                                },
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = SmallFont,
                                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                fontWeight = FontWeight(UbuntuBold),
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(SpacingMedium ))
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
                Spacer(modifier = Modifier.width(SpacingSmall))
                Text(
                    text = stringResource(id = R.string.add_placeholder),
                    style = TextStyle(
                        fontSize = SmallFont,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(UbuntuBold),
                        color = MaterialTheme.colorScheme.secondary,
                    )
                )
            }
        }

    }

}


@Composable
fun AdditionalDetailsScreen(weatherState: Weather?) {
    Column(modifier = Modifier.padding(start = SpacingMedium)) {
        Text(
            text = stringResource(id = R.string.precipitation),
            style = TextStyle(
                fontSize = SmallFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.tertiary,
            ),
        )
        Text(
            text = weatherState?.forecast?.forecastDayList?.get(0)?.totalPrecipitationMm.toString() +
                    Constants.SHORT_SPACE + stringResource(id = R.string.mm),
            style = TextStyle(
                fontSize = RegularFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
    Column(modifier = Modifier.padding(start = SpacingMedium, top = SpacingPartTiny)) {
        Text(
            text = weatherState?.current?.windDirection.toString() +
                    Constants.SHORT_SPACE + stringResource(id = R.string.wind),
            style = TextStyle(
                fontSize = SmallFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.tertiary,
            ),
        )
        Text(
            text = weatherState?.current?.windKph.toString() +
                    Constants.SHORT_SPACE + stringResource(id = R.string.kmh),
            style = TextStyle(
                fontSize = RegularFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
    Column(modifier = Modifier.padding(start = SpacingMedium, top = SpacingPartTiny)) {
        Text(
            text = stringResource(id = R.string.humidity),
            style = TextStyle(
                fontSize = SmallFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.tertiary,
            ),
        )
        Text(
            text = weatherState?.current?.humidity.toString() +
                    Constants.SHORT_SPACE + stringResource(id = R.string.percent),
            style = TextStyle(
                fontSize = RegularFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
    Column(modifier = Modifier.padding(start = SpacingMedium, top = SpacingPartTiny)) {
        Text(
            text = stringResource(id = R.string.UV),
            style = TextStyle(
                fontSize = SmallFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.tertiary
            ),
        )
        Text(
            text = weatherState?.current?.uv.toString(),
            style = TextStyle(
                fontSize = RegularFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
    Column(modifier = Modifier.padding(start = SpacingMedium, top = SpacingPartTiny)) {
        Text(
            text = stringResource(id = R.string.feels_like),
            style = TextStyle(
                fontSize = SmallFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.tertiary,
            ),
        )
        Text(
            text = weatherState?.current?.feelsLikeCelsius?.toInt().toString()
                    + stringResource(id = R.string.celsius),
            style = TextStyle(
                fontSize = RegularFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
    Column(modifier = Modifier.padding(start = SpacingMedium, top = SpacingPartTiny)) {
        Text(
            text = stringResource(id = R.string.pressure),
            style = TextStyle(
                fontSize = SmallFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.tertiary,
            ),
        )
        Text(
            text = weatherState?.current?.pressureMb.toString() +
                    Constants.SHORT_SPACE + stringResource(id = R.string.pressure_mb),
            style = TextStyle(
                fontSize = RegularFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.secondary,
            ),
        )
    }
}