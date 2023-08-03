package com.example.diapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.diapplication.R
import com.example.diapplication.data.model.Redirected
import com.example.diapplication.view.buttons.WeatherIconButton


@Composable
fun SettingsScreen(
    navController: NavController,
    darkTheme: MutableState<Boolean>
){
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
                    fontWeight = FontWeight(400),
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
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
                Column(modifier = Modifier.clickable { darkTheme.value = true}){
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(id = R.string.theme_dark),
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(400),
                            fontSize = 22.sp,
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                        if (darkTheme.value){
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
                Column(modifier = Modifier.clickable { darkTheme.value = false}) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(id = R.string.theme_light),
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(400),
                            fontSize = 22.sp,
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                        if (!darkTheme.value){
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
                    fontWeight = FontWeight(400),
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.team_label),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(400),
                    fontSize = 22.sp,
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.about_column_description),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(400),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 32.dp)
                    .clickable {
                        ContextCompat.startActivity(
                            context,
                            Redirected.redirectUser("form"),
                            null
                        )
                    },
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = stringResource(id = R.string.feedback_label),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(400),
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.report_label),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(400),
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.issue_description),
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(400),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }
        }
    }
}


