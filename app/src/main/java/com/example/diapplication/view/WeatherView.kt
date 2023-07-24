package com.example.diapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diapplication.R
import com.example.diapplication.data.model.DateConverter
import com.example.diapplication.domain.entity.Weather


@Composable
fun WeatherForeCastScreen(weatherState: Weather?) {
    val isForecastClicked = remember { mutableStateOf(false) }
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, bottom = 16.dp, top = 16.dp, end = 32.dp)
        ) {
            Text(
                text = "Details",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(400),
                    color = if (isForecastClicked.value) Color(0xFF616161) else Color.White,
                ),
                modifier = Modifier.clickable { isForecastClicked.value = false }
            )
            Text(
                text = "Forecast",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(400),
                    color = if (!isForecastClicked.value) Color(0xFF616161) else Color.White,
                ),
                modifier = Modifier.clickable { isForecastClicked.value = true }
            )
        }
        when (isForecastClicked.value) {
            false -> DetailsScreen(weatherState = weatherState)
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
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = Color(0xFF616161),
            )
        )
        Text(
            text = weatherState?.current?.temperatureCelsius?.toInt()
                .toString() + "°C",
            style = TextStyle(
                fontSize = 96.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
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
                        .toString() + "°C",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF616161),
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
                        .toString() + "°C",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF616161),
                    )
                )
            }

        }
        WeatherConditionImage(
            weatherCondition = weatherState?.current?.weatherCondition?.text.toString(),
            modifier = Modifier
                .padding(top = 32.dp, bottom = 8.dp)
                .width(148.dp)
                .height(148.dp)
                .padding(16.dp)
        )
        Text(
            text = weatherState?.current?.weatherCondition?.text.toString(),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                color = Color(0xFF616161),
            )
        )
        AstroScreen(weatherState = weatherState)
    }
}

@Composable
fun GovernmentAlertButton(weatherState: Weather?) {
    val showDialog = remember { mutableStateOf(false) }

    Button(
        onClick = { showDialog.value = true },
        modifier = Modifier
            .padding(2.dp)
            .background(color = Color.Transparent, shape = RoundedCornerShape(size = 18.dp))
            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB200)),
    ) {
        Text(
            text = stringResource(id = R.string.goverment_alert),
            fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
            fontWeight = FontWeight(400),
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
        )
    }

    if (showDialog.value) {
        AlertDialog(
            modifier = Modifier.background(color = Color.Black),
            onDismissRequest = { showDialog.value = false },
            title = {
                Text(
                    text = stringResource(id = R.string.goverment_alert),
                    fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                    fontWeight = FontWeight(400),
                    fontSize = 45.sp,
                    color = Color(0xFFFFB200),
                    textAlign = TextAlign.Center,
                )
            },
            text = {
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.background(color = Color.Black)
                ) {
                    item {
                        Text(
                            text = weatherState?.alerts?.alertList?.get(0)?.event.toString(),
                            fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                            fontWeight = FontWeight(400),
                            fontSize = 20.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = weatherState?.alerts?.alertList?.get(0)?.description.toString(),
                            fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                            fontWeight = FontWeight(400),
                            fontSize = 20.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,

                            )
                    }

                }
            },
            confirmButton = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { showDialog.value = false },
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(size = 18.dp)
                            )
                            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB200)),
                    ) {
                        Text(
                            text = "OK",
                            fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                            fontWeight = FontWeight(400),
                            fontSize = 20.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                        )
                    }
                }

            }
        )
    }
}


@Composable
fun AstroScreen(weatherState: Weather?) {
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
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF616161),
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
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF616161),
                    )
                )
            }
        }
    }
}






