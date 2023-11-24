package com.piusvelte.nwsweather.forecast

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
            LazyRow() {
                items(items = state.periods) {
                    ForecastPeriodColumn(state = it)
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
        ),
        error = NoOp,
    )
    ForecastScreen(state = state)
}