package com.piusvelte.nwsweather.data.dto

data class ForecastPropertiesDto(
    val updated: String,
    val units: String,
    val forecastGenerator: String,
    val generatedAt: String,
    val updateTime: String,
    val validTimes: String,
    val elevation: UnitValueDto,
    val periods: List<ForecastPeriodDto>,
)
