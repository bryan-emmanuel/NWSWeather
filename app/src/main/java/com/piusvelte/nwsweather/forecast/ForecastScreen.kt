package com.piusvelte.nwsweather.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piusvelte.nwsweather.domain.model.ForecastPeriod
import com.piusvelte.nwsweather.domain.model.mapTemperatureUnit
import com.piusvelte.nwsweather.ui.NoOp

@Composable
internal fun ForecastScreen(
    modifier: Modifier = Modifier,
    viewModel: ForecastViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ForecastScreen(
        state = state,
        modifier = modifier,
    )
}

@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    state: ForecastUiState,
) {
    if (state.isLoading) {
        CircularProgressIndicator()
    } else {
        if (state.periods.isNotEmpty()) {
            LazyColumn(
                modifier = modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(items = state.periods) {
                    ForecastPeriodCard(
                        modifier = modifier,
                        state = it,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun ForecastScreenPreview() {
    val state = ForecastUiState(
        isLoading = false,
        periods = listOf(
            ForecastPeriod(
                number = 0,
                name = "Tonight",
                icon = "",
                temperature = 66F,
                temperatureUnit = "F".mapTemperatureUnit(),
                shortForecast = "Chance Showers And Thunderstorms",
            ),
            ForecastPeriod(
                number = 0,
                name = "Tomorrow",
                icon = "",
                temperature = 68F,
                temperatureUnit = "F".mapTemperatureUnit(),
                shortForecast = "Sunny",
            ),
        ),
        error = NoOp,
    )
    ForecastScreen(state = state)
}
