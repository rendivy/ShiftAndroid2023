@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.diapplication.view


import android.Manifest
import android.content.Context
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.diapplication.R
import com.example.diapplication.presentation.WeatherState
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.view.buttons.WeatherIconButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient



@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel,
    fusedLocationProviderClient: FusedLocationProviderClient,
    navController: NavController
) {
    val context: Context = LocalContext.current
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val weatherState by weatherViewModel.weatherState.collectAsStateWithLifecycle()
    val permissionDenied = rememberSaveable { mutableStateOf(false) }


    LaunchedEffect(locationPermissionState) {
        weatherViewModel.updateUserGeolocation(
            context = context,
            fusedLocationProviderClient = fusedLocationProviderClient,
            locationPermissionState = locationPermissionState,
            permissionDenied = permissionDenied
        )
    }

    if (!permissionDenied.value) {
        when (weatherState) {

            is WeatherState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(120.dp),
                        color = Color.White
                    )
                }
            }

            is WeatherState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize().background(Color.Black),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.error_message),
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Gray,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        modifier = Modifier.size(128.dp),
                        painter = painterResource(id = R.drawable.wifi_off),
                        contentDescription = "bad connection",
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = stringResource(id = R.string.applucation_label),
                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                        fontWeight = FontWeight(400),
                        fontSize = 32.sp,
                        textAlign = TextAlign.End,
                        color = Color.White,
                    )
                }
            }

            is WeatherState.Content -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black),

                    ) {
                    item {
                        val content = weatherState as WeatherState.Content
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(32.dp)
                        ) {
                            Column {
                                Text(
                                    text = content.weather.location.name,
                                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                    fontWeight = FontWeight(400),
                                    fontSize = 24.sp,
                                    color = Color(0xFFFFFFFF),
                                )
                                Text(
                                    text = "Current location",
                                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                    fontWeight = FontWeight(400),
                                    fontSize = 18.sp,
                                    color = Color(0xFF414141),
                                )
                            }
                            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                                WeatherIconButton(id = R.drawable.location_button_icon)
                                { navController.navigate("search") }
                                WeatherIconButton(id = R.drawable.settings_icon)
                                {navController.navigate("settings")}
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            MainWeatherData(weatherState = content.weather)
                        }
                        WeatherForeCastScreen(weatherState = content.weather)

                        //Spacer(modifier = Modifier.padding(16.dp))
                        //if (weatherState?.alerts?.alertList?.isNotEmpty() == true) {
                        //GovernmentAlertButton(weatherState = weatherState)
                        //}
                        //OtherDaysScreen(weatherState = weatherState)

                    }
                }
            }
        }
    }

}

