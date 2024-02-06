package com.example.diapplication.presentation.ui.forecast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.diapplication.R
import com.example.diapplication.presentation.DetailsViewModel
import com.example.diapplication.presentation.state.PredictedCityState
import com.example.diapplication.presentation.ui.theme.MediumFont
import com.example.diapplication.presentation.ui.theme.SmallFont
import com.example.diapplication.presentation.ui.theme.SpacingMedium
import com.example.diapplication.presentation.ui.theme.SpacingPartTiny
import com.example.diapplication.presentation.ui.theme.SpacingRegular
import com.example.diapplication.presentation.ui.theme.UbuntuBold
import com.example.diapplication.presentation.ui.utils.WeatherIconButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLocationSection(
    detailsViewModel: DetailsViewModel,
    navController: NavController,
) {

    val predictedCityState = detailsViewModel.predictedCitiesState.collectAsStateWithLifecycle()
    val cityState = detailsViewModel.cityNameState.value
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = SpacingMedium, start = SpacingMedium, end = SpacingMedium)
            ) {
                WeatherIconButton(id = R.drawable.back_icon) {
                    navController.popBackStack()
                }
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier.padding(SpacingMedium),
                text = stringResource(id = R.string.add_placeholder),
                style = TextStyle(
                    fontSize = MediumFont,
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
                    color = MaterialTheme.colorScheme.secondary,
                ),
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = cityState.location,
                onValueChange = { newValue ->
                    detailsViewModel.setCityName(newValue)
                    detailsViewModel.getPredicted(newValue)
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.city_placeholder),
                        style = TextStyle(
                            fontSize = SmallFont,
                            fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                            fontWeight = FontWeight(UbuntuBold),
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(
                    fontSize = SmallFont,
                    fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                    fontWeight = FontWeight(UbuntuBold),
                    color = MaterialTheme.colorScheme.secondary,
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = MaterialTheme.colorScheme.secondary,
                    unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = MaterialTheme.colorScheme.secondary
                ),
            )
            when (predictedCityState.value) {
                is PredictedCityState.Content -> {
                    val content = predictedCityState.value as PredictedCityState.Content
                    val predictedCities = content.citiesList
                    AnimatedVisibility(
                        visible = predictedCities.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(SpacingRegular),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            items(predictedCities.size) {
                                Text(
                                    text = predictedCities[it].name, modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(SpacingPartTiny)
                                        .clickable {
                                            detailsViewModel.setCityName(predictedCities[it].name)
                                            detailsViewModel.addNewCity(predictedCities[it].name)
                                            navController.popBackStack()
                                        },
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = SmallFont,
                                        fontFamily = FontFamily(Font(R.font.ubuntu_condensed)),
                                        fontWeight = FontWeight(UbuntuBold),
                                        color = MaterialTheme.colorScheme.secondary,
                                    )
                                )
                            }
                        }
                    }
                }

                else -> {}
            }
        }
    }

}




