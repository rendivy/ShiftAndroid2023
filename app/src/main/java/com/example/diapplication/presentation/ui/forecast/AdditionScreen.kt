@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.diapplication.presentation.ui.forecast

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.diapplication.R
import com.example.diapplication.common.Constants
import com.example.diapplication.common.utils.DateConverter
import com.example.diapplication.data.entity.Weather
import com.example.diapplication.presentation.ui.theme.PartSmallFont
import com.example.diapplication.presentation.ui.theme.PlusMediumIconSize
import com.example.diapplication.presentation.ui.theme.RegularFont
import com.example.diapplication.presentation.ui.theme.SmallFont
import com.example.diapplication.presentation.ui.theme.SpacingMedium
import com.example.diapplication.presentation.ui.theme.SpacingPartTiny
import com.example.diapplication.presentation.ui.theme.SpacingSmall
import com.example.diapplication.presentation.ui.theme.ThickFont
import com.example.diapplication.presentation.ui.theme.UbuntuBold
import com.example.diapplication.presentation.ui.theme.tinyIconSize
import com.example.diapplication.presentation.ui.utils.WeatherConditionImage

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

        items(weatherState?.forecast?.forecastDayList!!.size) { index ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = DateConverter.invertDate(weatherState.forecast.forecastDayList[index].date),
                    style = TextStyle(
                        fontSize = SmallFont,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(UbuntuBold),
                        color = MaterialTheme.colorScheme.secondary,
                    ),
                )
                WeatherConditionImage(
                    weatherCondition = weatherState.forecast.forecastDayList[index].day.condition.text,
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
                        text = weatherState.forecast.forecastDayList[index].day.minimumTemperature.toInt()
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
                        text = weatherState.forecast.forecastDayList[index].day.maximumTemperature.toInt()
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


@Composable
fun AdditionalDetailsScreen(content: Weather?) {
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
            text = content?.forecast?.forecastDayList?.get(0)?.totalPrecipitationMm.toString() +
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
            text = content?.current?.windDirection.toString() +
                    Constants.SHORT_SPACE + stringResource(id = R.string.wind),
            style = TextStyle(
                fontSize = SmallFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.tertiary,
            ),
        )
        Text(
            text = content?.current?.windKph.toString() +
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
            text = content?.current?.humidity.toString() +
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
            text = content?.current?.uv.toString(),
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
            text = content?.current?.feelsLikeCelsius?.toInt().toString()
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
            text = content?.current?.pressureMb.toString() +
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