package com.example.diapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest


@Composable
fun LoadImageFromUrl(imageUrl: String?) {
    Box(
        modifier = Modifier
            .height(25.dp)
            .width(25.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl).crossfade(true).build(),
            contentDescription = "Weather icon",
            contentScale = ContentScale.Crop,
        )
    }
}

