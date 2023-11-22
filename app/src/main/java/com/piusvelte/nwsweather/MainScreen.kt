package com.piusvelte.nwsweather

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.piusvelte.nwsweather.forecast.ForecastScreen
import com.piusvelte.nwsweather.point.PointScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    Column {
        PointScreen()
        ForecastScreen()
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}