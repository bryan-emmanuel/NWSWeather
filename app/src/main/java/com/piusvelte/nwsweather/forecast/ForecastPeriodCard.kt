package com.piusvelte.nwsweather.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.piusvelte.nwsweather.domain.model.ForecastPeriod
import com.piusvelte.nwsweather.domain.model.mapTemperatureUnit

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ForecastPeriodCard(
    modifier: Modifier = Modifier,
    state: ForecastPeriod,
) {
    ElevatedCard(
        modifier = modifier,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = modifier.weight(1F),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = state.name)
                Text(text = state.shortForecast)
            }
            Row {
                GlideImage(
                    model = state.icon,
                    contentDescription = "Forecast icon",
                    modifier = modifier
                        .width(72.dp)
                        .height(72.dp)
                )
                Row(
                    modifier = modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 16.dp),
                ) {
                    Text(text = state.temperature.toString())
                    Text(text = state.temperatureUnit.toString().substring(0, 1))
                }
            }
        }
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
    ForecastPeriodCard(state = state)
}