package com.example.diapplication.presentation.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.diapplication.presentation.ui.theme.MediumIconSize

@Composable
fun WeatherIconButton(id: Int, onClick: () -> Unit){
    IconButton(
        onClick = { onClick() }

    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = "search button",
            modifier = Modifier.size(MediumIconSize),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.tertiary),
            contentScale = ContentScale.Crop
        )
    }
}