package com.example.diapplication.view.settings

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.diapplication.R
import com.example.diapplication.data.model.Redirected
import com.example.diapplication.ui.theme.MediumFont
import com.example.diapplication.ui.theme.PartSmallFont
import com.example.diapplication.ui.theme.ThickFont
import com.example.diapplication.ui.theme.UbuntuBold


@Composable
fun AboutSection(
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 32.dp)
            .clickable {
                ContextCompat.startActivity(
                    context,
                    Redirected.redirectUser("gitHub"),
                    null
                )
            },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(id = R.string.about_label),
            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
            fontWeight = FontWeight(UbuntuBold),
            fontSize = MediumFont,
            color = MaterialTheme.colorScheme.secondary,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.team_label),
            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
            fontWeight = FontWeight(UbuntuBold),
            fontSize = PartSmallFont,
            color = Color.Gray,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.about_column_description),
            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
            fontWeight = FontWeight(UbuntuBold),
            fontSize = ThickFont,
            color = MaterialTheme.colorScheme.tertiary,
        )
    }
}