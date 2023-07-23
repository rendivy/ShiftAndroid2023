package com.example.diapplication.view


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.diapplication.R
import com.example.diapplication.presentation.WeatherState
import com.example.diapplication.presentation.WeatherViewModel
import com.example.diapplication.ui.theme.SfProDisplay
import com.example.diapplication.view.buttons.WeatherIconButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel,
    fusedLocationProviderClient: FusedLocationProviderClient
) {
    val context: Context = LocalContext.current
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val weatherState by weatherViewModel.weatherState.collectAsStateWithLifecycle()
    val permissionDenied = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(locationPermissionState) {
        if (!locationPermissionState.status.isGranted) {
            locationPermissionState.launchPermissionRequest()
        } else {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
                    weatherViewModel.updateWeatherData("${location?.latitude},${location?.longitude}")
                    permissionDenied.value = false
                }
            } else {
                permissionDenied.value = true
            }
        }
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
    ) {
        item {
            if (!permissionDenied.value) {
                when (weatherState) {
                    is WeatherState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(120.dp),
                                color = Color.White
                            )
                        }
                    }

                    is WeatherState.Error -> {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = stringResource(id = R.string.error_message),
                                fontFamily = SfProDisplay,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                fontSize = 22.sp,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }

                    is WeatherState.Content -> {
                        val content = weatherState as WeatherState.Content
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(32.dp)
                        ) {
                            Column() {
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
                                WeatherIconButton(id = R.drawable.settings_icon)
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

                        //Spacer(modifier = Modifier.padding(16.dp))

                        //Spacer(modifier = Modifier.padding(16.dp))
                        //if (weatherState?.alerts?.alertList?.isNotEmpty() == true) {
                        //GovernmentAlertButton(weatherState = weatherState)
                        //}
                        //OtherDaysScreen(weatherState = weatherState)

                    }
                }
            } else {
                Text(
                    text = "Please give location permission",
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

