package com.example.diapplication.view.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.diapplication.R

@Composable
fun WeatherIconButton(id: Int){
    IconButton(
        onClick = { /*TODO*/ }

    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = "search button",
            modifier = Modifier.size(24.dp),
            contentScale = ContentScale.Crop
        )
    }
}