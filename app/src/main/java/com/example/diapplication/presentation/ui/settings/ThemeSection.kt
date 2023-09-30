package com.example.diapplication.presentation.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.diapplication.R
import com.example.diapplication.presentation.UserViewModel
import com.example.diapplication.ui.theme.MediumFont
import com.example.diapplication.ui.theme.PartSmallFont
import com.example.diapplication.ui.theme.SpacingMedium
import com.example.diapplication.ui.theme.SpacingSmall
import com.example.diapplication.ui.theme.SpacingTiny
import com.example.diapplication.ui.theme.ThickFont
import com.example.diapplication.ui.theme.UbuntuBold
import com.example.diapplication.presentation.ui.utils.IconManager

@Composable
fun ThemeSection(
    userDataViewModel: UserViewModel,
    darkTheme: Boolean
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SpacingMedium, start = SpacingMedium, end = SpacingMedium),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(id = R.string.theme_label),
            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
            fontWeight = FontWeight(UbuntuBold),
            fontSize = MediumFont,
            color = MaterialTheme.colorScheme.secondary,
        )
        Column(modifier = Modifier.clickable { userDataViewModel.updateUserTheme(true) }) {
            Spacer(modifier = Modifier.height(SpacingSmall))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.theme_dark),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
                    fontSize = PartSmallFont,
                    color = MaterialTheme.colorScheme.tertiary,
                )
                if (darkTheme) {
                    IconManager(tintColor = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(SpacingTiny))
            Text(
                text = stringResource(id = R.string.dark_side),
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                fontSize = ThickFont,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
        Spacer(modifier = Modifier.height(SpacingSmall))
        Column(modifier = Modifier.clickable { userDataViewModel.updateUserTheme(false) }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.theme_light),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
                    fontSize = PartSmallFont,
                    color = MaterialTheme.colorScheme.tertiary,
                )
                if (!darkTheme) {
                    IconManager(tintColor = Color.Black)
                }
            }
            Text(
                text = stringResource(id = R.string.light_side),
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(UbuntuBold),
                fontSize = ThickFont,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}