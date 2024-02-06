package com.example.diapplication.presentation.ui


import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.diapplication.R
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.presentation.state.WeatherState
import com.example.diapplication.presentation.ui.navigation.WeatherNavRoutes
import com.example.diapplication.presentation.ui.theme.MediumFont
import com.example.diapplication.presentation.ui.theme.PartLargeIconSize
import com.example.diapplication.presentation.ui.theme.RegularFont
import com.example.diapplication.presentation.ui.theme.SpacingMedium
import com.example.diapplication.presentation.ui.theme.ThickFont
import com.example.diapplication.presentation.ui.theme.UbuntuBold
import com.example.diapplication.presentation.ui.utils.WeatherIconButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherScreen(
    navController: NavController,
    weatherViewModel: WeatherViewModel,
    fusedLocationProviderClient: FusedLocationProviderClient
) {
    val weatherState by weatherViewModel.weatherState.collectAsStateWithLifecycle()
    val permissionState = rememberPermissionState(permission = ACCESS_FINE_LOCATION)
    val context = LocalContext.current




    when (weatherState) {
        is WeatherState.PermissionDenied -> {
            Text(text = "Permission Denied", color = MaterialTheme.colorScheme.secondary)
        }

        is WeatherState.Initial -> {
            LaunchedEffect(key1 = permissionState.status) {
                if (permissionState.status.isGranted) {
                    weatherViewModel.updateUserGeolocation(
                        context = context,
                        fusedLocationProviderClient = fusedLocationProviderClient
                    )
                } else {
                    permissionState.launchPermissionRequest()
                }
            }
        }

        is WeatherState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Loading...",
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
                    fontSize = RegularFont,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }

        is WeatherState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.error_message),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
                    fontSize = RegularFont,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.tertiary,
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Image(
                    modifier = Modifier.size(PartLargeIconSize),
                    painter = painterResource(id = R.drawable.wifi_off),
                    contentDescription = "bad connection",
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
                )
                Spacer(modifier = Modifier.height(SpacingMedium))
                Text(
                    text = stringResource(id = R.string.applucation_label),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
                    fontSize = MediumFont,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }

        is WeatherState.Content -> {
            val content = weatherState as WeatherState.Content
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),

                ) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(SpacingMedium)
                    ) {
                        Column {
                            Text(
                                text = content.weather.location.name,
                                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                fontWeight = FontWeight(UbuntuBold),
                                fontSize = RegularFont,
                                color = MaterialTheme.colorScheme.secondary,
                            )
                            Text(
                                text = stringResource(id = R.string.currentLocation_label),
                                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                fontWeight = FontWeight(UbuntuBold),
                                fontSize = ThickFont,
                                color = MaterialTheme.colorScheme.tertiary,
                            )
                        }
                        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                            WeatherIconButton(id = R.drawable.location_button_icon) {
                                navController.navigate(
                                    "search" +
                                            "/${content.weather.current.temperatureCelsius.toInt()}" +
                                            "/${content.weather.current.weatherCondition.text}" +
                                            "/${content.weather.location.name}"
                                )
                            }
                            WeatherIconButton(id = R.drawable.settings_icon) {
                                navController.navigate(
                                    WeatherNavRoutes.Settings.route
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        MainWeatherData(weatherState = content.weather)
                    }
                    WeatherForeCastScreen(content = content.weather)

                }
            }
        }
    }
}


