package com.example.diapplication.presentation.ui.forecast

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.diapplication.R
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.ui.theme.BigIconSize
import com.example.diapplication.ui.theme.RegularFont
import com.example.diapplication.ui.theme.SmallFont
import com.example.diapplication.ui.theme.SpacingMedium
import com.example.diapplication.ui.theme.SpacingPartTiny
import com.example.diapplication.ui.theme.SpacingSmall
import com.example.diapplication.ui.theme.TinyFont
import com.example.diapplication.ui.theme.UbuntuBold
import com.example.diapplication.presentation.ui.utils.WeatherConditionImage
import com.example.diapplication.presentation.ui.utils.WeatherIconButton


@Composable
fun DetailsScreen(
    navController: NavController,
    weatherViewModel: WeatherViewModel
) {

    val additionCitiesList by weatherViewModel.citiesWeatherState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpacingMedium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherIconButton(id = R.drawable.back_icon) {
                    navController.popBackStack()
                }
                WeatherIconButton(id = R.drawable.add_location) {
                    navController.navigate("add_location_screen")
                }
            }
            for (i in additionCitiesList.indices) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = SpacingMedium, end = SpacingMedium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = additionCitiesList[i].weather.location.name,
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(UbuntuBold),
                            fontSize = RegularFont,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                        Text(
                            text = additionCitiesList[i].weather.current.temperatureCelsius.toInt()
                                .toString()
                                    + stringResource(id = R.string.celsius),
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(UbuntuBold),
                            fontSize = SmallFont,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = additionCitiesList[i].weather.current.weatherCondition.text,
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(UbuntuBold),
                            fontSize = TinyFont,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    WeatherConditionImage(
                        weatherCondition = additionCitiesList[i].weather.current.weatherCondition.text,
                        modifier = Modifier
                            .padding(top = SpacingMedium, bottom = SpacingPartTiny)
                            .width(BigIconSize)
                            .height(BigIconSize)
                            .padding(SpacingSmall)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}