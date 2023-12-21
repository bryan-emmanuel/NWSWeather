package com.piusvelte.nwsweather

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.piusvelte.nwsweather.forecast.ForecastScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ForecastScreen(modifier = modifier)
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}