package com.piusvelte.nwsweather.domain.model

data class ForecastPeriod(
    val number: Int,
    val name: String,
    val icon: String,
    val temperature: Float,
    val temperatureUnit: TemperatureUnit,
    val shortForecast: String,
)