package com.piusvelte.nwsweather.domain.model

enum class TemperatureUnit {
    CELSIUS,
    FAHRENHEIT,
    UNKNOWN,
}

fun String.mapTemperatureUnit(): TemperatureUnit = when (this) {
    "C" -> TemperatureUnit.CELSIUS
    "F" -> TemperatureUnit.FAHRENHEIT
    else -> TemperatureUnit.UNKNOWN
}