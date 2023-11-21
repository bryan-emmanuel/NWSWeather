package com.piusvelte.nwsweather.forecast

import com.piusvelte.nwsweather.domain.model.ForecastPeriod
import com.piusvelte.nwsweather.ui.ConsumableEvent
import com.piusvelte.nwsweather.ui.NoOp

data class ForecastUiState(
    val isLoading: Boolean = false,
    val periods: List<ForecastPeriod> = emptyList(),
    val error: ConsumableEvent<String> = NoOp,
)