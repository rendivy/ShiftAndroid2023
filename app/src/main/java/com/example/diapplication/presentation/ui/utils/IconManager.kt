package com.example.diapplication.presentation.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.diapplication.R
import com.example.diapplication.ui.theme.RegularIconSize

@Composable
fun IconManager(tintColor: Color){
    Image(
        painter = painterResource(id = R.drawable.check), contentDescription = "Sunny weather",
        modifier = Modifier.size(RegularIconSize),
        colorFilter = ColorFilter.tint(color = tintColor),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
    )
}