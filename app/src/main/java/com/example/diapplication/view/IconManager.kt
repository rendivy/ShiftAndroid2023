package com.example.diapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.diapplication.R

@Composable
fun IconManager(tintColor: Color){
    Image(
        painter = painterResource(id = R.drawable.check), contentDescription = "Sunny weather",
        modifier = Modifier.size(32.dp),
        colorFilter = ColorFilter.tint(color = tintColor),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
    )
}