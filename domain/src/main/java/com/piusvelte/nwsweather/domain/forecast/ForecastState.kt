package com.piusvelte.nwsweather.domain.forecast

import com.piusvelte.nwsweather.domain.model.ForecastPeriod

interface ForecastState {
    data object Loading : ForecastState
    data class Failure(val message: String?) : ForecastState
    data class Success(val periods: List<ForecastPeriod>) : ForecastState
}