package com.piusvelte.nwsweather.data.gridpoints.model

import com.piusvelte.nwsweather.data.common.model.UnitValue

data class ForecastProperties(
    val updated: String,
    val units: String,
    val forecastGenerator: String,
    val generatedAt: String,
    val updateTime: String,
    val validTimes: String,
    val elevation: UnitValue,
    val periods: List<ForecastPeriod>,
)
