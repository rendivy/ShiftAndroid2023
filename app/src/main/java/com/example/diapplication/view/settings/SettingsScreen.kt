package com.example.diapplication.view.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diapplication.R
import com.example.diapplication.presentation.UserViewModel
import com.example.diapplication.ui.theme.RegularFont
import com.example.diapplication.ui.theme.UbuntuBold
import com.example.diapplication.view.utils.WeatherIconButton


@Composable
fun SettingsScreen(
    navController: NavController,
    userDataViewModel: UserViewModel,
    darkTheme: Boolean
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 32.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherIconButton(id = R.drawable.back_icon) {
                    navController.popBackStack()
                }
                Text(
                    text = stringResource(id = R.string.settings_label),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
                    fontSize = RegularFont,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
            ThemeSection(userDataViewModel = userDataViewModel, darkTheme = darkTheme)
            AboutSection(context = context)
            FeedbackSection(context = context)
        }
    }
}



