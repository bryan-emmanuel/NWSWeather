package com.piusvelte.nwsweather.forecast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.piusvelte.nwsweather.domain.model.ForecastPeriod
import com.piusvelte.nwsweather.domain.model.mapTemperatureUnit

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ForecastPeriodColumn(
    state: ForecastPeriod,
    modifier: Modifier = Modifier.width(96.dp),
) {
    Column {
        Text(text = state.name)
        GlideImage(
            model = state.icon,
            contentDescription = "Forecast icon",
            modifier = modifier
                .width(72.dp)
                .height(72.dp)
        )
        Row {
            Text(text = state.temperature.toString())
            Text(text = state.temperatureUnit.toString().substring(0, 1))
        }
        Text(text = state.shortForecast)
    }
}

@Composable
@Preview
fun ForecastPeriodColumnPreview() {
    val state = ForecastPeriod(
        number = 0,
        name = "Tonight",
        icon = "",
        temperature = 66F,
        temperatureUnit = "F".mapTemperatureUnit(),
        shortForecast = "Chance Showers And Thunderstorms",
    )
    ForecastPeriodColumn(state = state)
}