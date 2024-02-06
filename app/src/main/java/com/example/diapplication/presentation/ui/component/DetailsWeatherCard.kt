package com.example.diapplication.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.diapplication.R
import com.example.diapplication.presentation.ui.theme.BigIconSize
import com.example.diapplication.presentation.ui.theme.RegularFont
import com.example.diapplication.presentation.ui.theme.SmallFont
import com.example.diapplication.presentation.ui.theme.SpacingMedium
import com.example.diapplication.presentation.ui.theme.SpacingPartTiny
import com.example.diapplication.presentation.ui.theme.SpacingSmall
import com.example.diapplication.presentation.ui.theme.TinyFont
import com.example.diapplication.presentation.ui.theme.UbuntuBold
import com.example.diapplication.presentation.ui.utils.WeatherConditionImage

@Composable
fun DetailsWeatherCard(
    temperatureCelsius: String,
    condition: String,
    location: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = SpacingMedium, end = SpacingMedium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = location,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                fontSize = RegularFont,
                color = MaterialTheme.colorScheme.secondary,
            )
            Text(
                text = temperatureCelsius.toInt()
                    .toString()
                        + stringResource(id = R.string.celsius),
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                fontSize = SmallFont,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = condition,
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                fontSize = TinyFont,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        WeatherConditionImage(
            weatherCondition = condition,
            modifier = Modifier
                .padding(top = SpacingMedium, bottom = SpacingPartTiny)
                .width(BigIconSize)
                .height(BigIconSize)
                .padding(SpacingSmall)
                .align(Alignment.CenterVertically)
        )
    }
}