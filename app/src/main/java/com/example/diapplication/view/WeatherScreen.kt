package com.example.diapplication.view


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.diapplication.R
import com.example.diapplication.ui.theme.SfProDisplay
import com.example.diapplication.view.buttons.WeatherIconButton
import com.example.diapplication.viewModel.WeatherViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel,
    fusedLocationProviderClient: FusedLocationProviderClient
) {
    val context = LocalContext.current
    val locationPermissionState =
        rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)

    val cityName = remember { mutableStateOf("") }
    val weatherState by weatherViewModel.weather.collectAsStateWithLifecycle()
    val userInfoState by weatherViewModel.userGeocoding.collectAsStateWithLifecycle()
    val isLoading by weatherViewModel.isLoading.collectAsStateWithLifecycle()
    val error by weatherViewModel.error.collectAsStateWithLifecycle(null)
    fusedLocationProviderClient.lastLocation.addOnSuccessListener {
        weatherViewModel.updateUserInfo(it.latitude, it.longitude)

        println("City name: ${cityName.value}")
    }
    weatherViewModel.updateWeatherData(userInfoState?.city.toString())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            if (error == true) {
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
            } else if (isLoading) {
                CircularProgressIndicator(modifier = Modifier)
            } else {
                weatherState?.location?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp)
                    ) {
                        Column() {
                            Text(
                                text = weatherState?.location?.name.toString(),
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
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        MainWeatherData(weatherState = weatherState)
                    }
                    //Spacer(modifier = Modifier.padding(8.dp))
                    //WeatherForeCastScreen(weatherState = weatherState)
                    //Spacer(modifier = Modifier.padding(16.dp))
                    //HourScreen(weatherState = weatherState)
                    //Spacer(modifier = Modifier.padding(16.dp))
                    AstroScreen(weatherState = weatherState)
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