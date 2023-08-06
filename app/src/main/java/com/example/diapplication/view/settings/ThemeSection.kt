package com.example.diapplication.view.settings

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diapplication.R
import com.example.diapplication.presentation.UserViewModel
import com.example.diapplication.view.IconManager

@Composable
fun ThemeSection(
    userDataViewModel: UserViewModel,
    darkTheme: Boolean
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(id = R.string.theme_label),
            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
            fontWeight = FontWeight(400),
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.secondary,
        )
        Column(modifier = Modifier.clickable { userDataViewModel.updateUserTheme(true) }) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.theme_dark),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(400),
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                )
                if (darkTheme) {
                    IconManager(tintColor = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.dark_side),
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.clickable { userDataViewModel.updateUserTheme(false) }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.theme_light),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(400),
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                )
                if (!darkTheme) {
                    IconManager(tintColor = Color.Black)
                }
            }
            Text(
                text = stringResource(id = R.string.light_side),
                fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                fontWeight = FontWeight(400),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}