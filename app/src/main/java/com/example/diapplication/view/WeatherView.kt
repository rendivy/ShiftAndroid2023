package com.example.diapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diapplication.R
import com.example.diapplication.data.Weather
import com.example.diapplication.data.model.DateConverter


@Composable
fun WeatherForeCastScreen(weatherState: Weather?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = weatherState?.location?.country.toString(),
            fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
            fontWeight = FontWeight(400),
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = weatherState?.location?.region.toString() + " region",
            fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
            fontWeight = FontWeight(400),
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Last update",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                fontWeight = FontWeight(600),
                color = Color(0x99EBEBF5),
                textAlign = TextAlign.Center,
                letterSpacing = 0.38.sp,
            )
        )
        Text(
            text = DateConverter.invertDateTime(weatherState?.current?.lastUpdated.toString()),
            fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
            fontWeight = FontWeight(400),
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }
}


@Composable
fun MainWeatherData(weatherState: Weather?) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(51.dp)
    ) {
        Text(
            text = weatherState?.location?.name.toString(),
            fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
            fontWeight = FontWeight(400),
            fontSize = 34.sp,
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = weatherState?.current?.temperatureCelsius?.toInt()
                .toString() + "\u00B0",
            style = TextStyle(
                fontSize = 96.sp,
                lineHeight = 70.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                fontWeight = FontWeight(200),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                letterSpacing = 0.37.sp,
            )
        )
        Text(
            text = weatherState?.current?.weatherCondition?.text.toString(),
            style = TextStyle(
                fontSize = 32.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                fontWeight = FontWeight(600),
                color = Color(0x99EBEBF5),
                textAlign = TextAlign.Center,
                letterSpacing = 0.38.sp,
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "H:" + weatherState?.forecast?.forecastDayList?.get(0)?.day?.maximumTemperature.toString() + "\u00B0",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_meduim)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.38.sp,
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "L:" + weatherState?.forecast?.forecastDayList?.get(0)?.day?.minimumTemperature.toString() + "\u00B0",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_meduim)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.38.sp,
                )
            )
        }
    }
}


@Composable
fun OtherDaysScreen(weatherState: Weather?) {
    LazyRow {
        for (i in 0 until weatherState?.forecast?.forecastDayList!!.size) {
            item {
                WeatherPainter(imageUrl = "https:" + weatherState.forecast.forecastDayList[i].day.condition.icon)
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = weatherState.forecast.forecastDayList[i].day.averageTemperature.toInt()
                            .toString() + "°",
                        fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                        fontWeight = FontWeight(400),
                        fontSize = 34.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = DateConverter.invertDate(weatherState.forecast.forecastDayList[i].date),
                        fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                        fontWeight = FontWeight(400),
                        fontSize = 34.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }


            }
        }
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
            text = "Government Alert",
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
                    text = "Government Alert",
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
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Sunrise",
                    fontFamily = FontFamily(Font(R.font.sf_pro_regular)),
                    fontWeight = FontWeight(400),
                    fontSize = 24.sp,
                    color = Color(216, 175, 71, 255),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = DateConverter.convertTo24HourFormat(
                        weatherState?.forecast?.forecastDayList?.get(
                            0
                        )?.astro?.sunrise.toString()
                    ),
                    fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                    fontWeight = FontWeight(400),
                    fontSize = 20.sp,
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Sunset",
                    fontFamily = FontFamily(Font(R.font.sf_pro_regular)),
                    fontWeight = FontWeight(400),
                    fontSize = 24.sp,
                    color = Color(255, 255, 255, 255),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = DateConverter.convertTo24HourFormat(
                        weatherState?.forecast?.forecastDayList?.get(
                            0
                        )?.astro?.sunset.toString()
                    ),
                    fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                    fontWeight = FontWeight(400),
                    fontSize = 20.sp,
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Moonrise",
                    fontFamily = FontFamily(Font(R.font.sf_pro_regular)),
                    fontWeight = FontWeight(400),
                    fontSize = 24.sp,
                    color = Color(255, 255, 255, 255),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = DateConverter.convertTo24HourFormat(
                        weatherState?.forecast?.forecastDayList?.get(
                            0
                        )?.astro?.moonrise.toString()
                    ),
                    fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                    fontWeight = FontWeight(400),
                    fontSize = 20.sp,
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Moonset",
                    fontFamily = FontFamily(Font(R.font.sf_pro_regular)),
                    fontWeight = FontWeight(400),
                    fontSize = 24.sp,
                    color = Color(238, 93, 30, 255),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = DateConverter.convertTo24HourFormat(
                        weatherState?.forecast?.forecastDayList?.get(
                            0
                        )?.astro?.moonset.toString()
                    ),
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

@Composable
fun HourScreen(weatherState: Weather?) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        item {
            for (i in weatherState?.forecast?.forecastDayList?.get(0)?.hourList?.indices!!) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = "$i:00",
                        fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
                        fontWeight = FontWeight(400),
                        fontSize = 20.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                    WeatherPainter(
                        imageUrl = "https:" + weatherState.forecast.forecastDayList.get(0).hourList
                            .get(i).condition.icon
                    )
                    Text(
                        text = weatherState.forecast.forecastDayList.get(0).hourList.get(i).tempC.toString() + "°",
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

}
