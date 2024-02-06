package com.example.diapplication.presentation.ui.settings

/*
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
}*/
