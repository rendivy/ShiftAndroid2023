package com.example.diapplication.view

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.diapplication.R

@Composable
fun WeatherConditionImage(weatherCondition: String?, modifier: Modifier) {
    when {
        weatherCondition?.contains("Clear") == true || weatherCondition?.contains("Sunny") == true -> Image(
            painter = painterResource(id = R.drawable.sunny), contentDescription = "Sunny weather",
            modifier = modifier,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )

        weatherCondition?.contains("Thunder") == true  -> Image(
            painter = painterResource(id = R.drawable.cloud_lightning), contentDescription = "Sunny weather",
            modifier = modifier,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )


        weatherCondition?.contains("drizzle") == true -> Image(
            painter = painterResource(id = R.drawable.drizzle),
            contentDescription = "Cloudy weather",
            modifier = modifier,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )

        weatherCondition?.contains("rain") == true -> Image(
            painter = painterResource(id = R.drawable.cloud_rain),
            contentDescription = "Cloudy weather",
            modifier = modifier,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )


        weatherCondition?.contains("snow") == true || weatherCondition?.contains("sleet") == true ||
                weatherCondition?.contains("blizzard") == true || weatherCondition?.contains("ice") == true -> Image(
            painter = painterResource(id = R.drawable.cloud_snow),
            contentDescription = "Sunny weather",
            modifier = modifier,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )



        else -> Image(
            painter = painterResource(id = R.drawable.cloud), contentDescription = "Cloudy weather",
            modifier = modifier,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )

    }
}