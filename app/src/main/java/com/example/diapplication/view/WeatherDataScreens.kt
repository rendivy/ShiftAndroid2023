package com.example.diapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diapplication.R
import com.example.diapplication.data.model.DateConverter
import com.example.diapplication.domain.entity.Weather
import com.example.diapplication.ui.theme.LargeFont
import com.example.diapplication.ui.theme.LargeIconSize
import com.example.diapplication.ui.theme.RegularFont
import com.example.diapplication.ui.theme.SpacingMedium
import com.example.diapplication.ui.theme.SpacingSmall
import com.example.diapplication.ui.theme.UbuntuBold
import com.example.diapplication.view.forecast.AdditionalDetailsScreen
import com.example.diapplication.view.forecast.ForecastWeatherScreen
import com.example.diapplication.view.utils.WeatherConditionImage


@Composable
fun WeatherForeCastScreen(weatherState: Weather?) {
    val isForecastClicked = rememberSaveable { mutableStateOf(false) }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = SpacingMedium, bottom = SpacingSmall, top = SpacingSmall, end = SpacingMedium)
        ) {
            Text(
                text = stringResource(id = R.string.details),
                style = TextStyle(
                    fontSize = RegularFont,
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
                    color = if (isForecastClicked.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary,
                ),
                modifier = Modifier.clickable { isForecastClicked.value = false }
            )
            Text(
                text = stringResource(id = R.string.forecast),
                style = TextStyle(
                    fontSize = RegularFont,
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
                    color = if (!isForecastClicked.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary,
                ),
                modifier = Modifier.clickable { isForecastClicked.value = true }
            )
        }
        when (isForecastClicked.value) {
            false -> AdditionalDetailsScreen(weatherState = weatherState)
            else -> ForecastWeatherScreen(weatherState = weatherState)
        }

    }
}


@Composable
fun MainWeatherData(weatherState: Weather?) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        Text(
            text = DateConverter.convertDateTime(weatherState?.current?.lastUpdated.toString()),
            style = TextStyle(
                fontSize = RegularFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.tertiary,
            )
        )
        Text(
            text = weatherState?.current?.temperatureCelsius?.toInt()
                .toString() + stringResource(id = R.string.celsius),
            style = TextStyle(
                fontSize = LargeFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.secondary,
                letterSpacing = 0.37.sp,
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.down_arrow),
                    contentDescription = "down_arrow",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = weatherState?.forecast?.forecastDayList?.get(0)?.day?.minimumTemperature
                        .toString() + stringResource(id = R.string.celsius),
                    style = TextStyle(
                        fontSize = RegularFont,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(UbuntuBold),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.up_arrow),
                    contentDescription = "up_arrow",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = weatherState?.forecast?.forecastDayList?.get(0)?.day?.maximumTemperature
                        .toString() + stringResource(id = R.string.celsius),
                    style = TextStyle(
                        fontSize = RegularFont,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(UbuntuBold),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                )
            }

        }
        WeatherConditionImage(
            weatherCondition = weatherState?.current?.weatherCondition?.text.toString(),
            modifier = Modifier
                .padding(top = 32.dp, bottom = 8.dp)
                .width(LargeIconSize)
                .height(LargeIconSize)
                .padding(16.dp)
        )
        Text(
            text = weatherState?.current?.weatherCondition?.text.toString(),
            style = TextStyle(
                fontSize = RegularFont,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                color = MaterialTheme.colorScheme.tertiary,
            )
        )
        AstropyScreen(weatherState = weatherState)
    }
}

@Composable
fun AstropyScreen(weatherState: Weather?) {
    Box(contentAlignment = Alignment.Center) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 32.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sunrise), contentDescription = null,
                    modifier = Modifier.size(20.dp), contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = weatherState?.forecast?.forecastDayList?.get(0)?.astro?.sunrise.toString(),
                    style = TextStyle(
                        fontSize = RegularFont,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(UbuntuBold),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sunset), contentDescription = null,
                    modifier = Modifier.size(20.dp), contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text =
                    weatherState?.forecast?.forecastDayList?.get(0)?.astro?.sunset.toString(),
                    style = TextStyle(
                        fontSize = RegularFont,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(UbuntuBold),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                )
            }
        }
    }
}






