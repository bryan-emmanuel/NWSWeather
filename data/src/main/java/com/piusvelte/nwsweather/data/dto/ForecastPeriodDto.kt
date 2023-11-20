package com.piusvelte.nwsweather.data.dto

data class ForecastPeriodDto(
    val number: Int,
    val name: String,
    val startTime: String,
    val endTime: String,
    val isDaytime: Boolean,
    val temperature: Float,
    val temperatureUnit: TemperatureUnitDto,
    val temperatureTrend: String?,
    val windSpeed: String,
    val windDirection: CardinalDirectionDto,
    val icon: String,
    val shortForecast: String,
    val detailedForecast: String,
)
