package com.example.diapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.diapplication.R


@Composable
fun WeatherPainter(imageUrl: String?) {
    Box(
        modifier = Modifier
            .height(75.dp)
            .width(75.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(65.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl).crossfade(true).build(),
            contentDescription = "Weather icon",
            contentScale = ContentScale.Crop,
        )
    }
}


@Composable
fun WeatherConditionImage(weatherCondition: String?, modifier: Modifier) {
    when (weatherCondition) {
        "Clear", "Sunny" -> Image(
            painter = painterResource(id = R.drawable.sunny), contentDescription = "Sunny weather",
            modifier = modifier,
            colorFilter = ColorFilter.tint(color = Color.White),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )
        "Cloudy", "Partly cloudy"  -> Image(
            painter = painterResource(id = R.drawable.cloud), contentDescription = "Cloudy weather",
            modifier = modifier,
            colorFilter = ColorFilter.tint(color = Color.White),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )
    }

}