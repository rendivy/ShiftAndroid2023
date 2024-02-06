package com.example.diapplication.presentation.ui.forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.diapplication.R
import com.example.diapplication.presentation.DetailsViewModel
import com.example.diapplication.presentation.state.CitiesState
import com.example.diapplication.presentation.ui.component.DetailsWeatherCard
import com.example.diapplication.presentation.ui.theme.SpacingMedium
import com.example.diapplication.presentation.ui.utils.WeatherIconButton


@Composable
fun DetailsScreen(
    temperatureCelsius: String,
    condition: String,
    location: String,
    navController: NavController,
    detailsViewModel: DetailsViewModel
) {

    val citiesState = detailsViewModel.citiesState.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                DetailsWeatherCard(
                    temperatureCelsius = temperatureCelsius,
                    condition = condition,
                    location = location
                )
            }
            when (citiesState.value) {
                is CitiesState.Content -> {
                    val content = citiesState.value as CitiesState.Content
                    val citiesList = content.citiesList
                    items(citiesList.size) { itemIndex ->
                        DetailsWeatherCard(
                            temperatureCelsius = citiesList[itemIndex].current.temperatureCelsius.toInt()
                                .toString(),
                            condition = citiesList[itemIndex].current.weatherCondition.text,
                            location = citiesList[itemIndex].location.name
                        )
                    }
                }

                is CitiesState.Error -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                is CitiesState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                is CitiesState.Initial -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        detailsViewModel.getSavedCities()
                    }
                }

            }
        }
    }
}
