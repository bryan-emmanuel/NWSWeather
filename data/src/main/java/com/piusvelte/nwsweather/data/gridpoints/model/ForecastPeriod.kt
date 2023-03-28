package com.piusvelte.nwsweather.data.gridpoints.model

import com.piusvelte.nwsweather.data.common.model.CardinalDirection
import com.piusvelte.nwsweather.data.common.model.TemperatureUnit

data class ForecastPeriod(
    val number: Int,
    val name: String,
    val startTime: String,
    val endTime: String,
    val isDaytime: Boolean,
    val temperature: Float,
    val temperatureUnit: TemperatureUnit,
    val temperatureTrend: String?,
    val windSpeed: String,
    val windDirection: CardinalDirection,
    val icon: String,
    val shortForecast: String,
    val detailedForecast: String,
)
