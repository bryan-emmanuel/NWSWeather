package com.piusvelte.nwsweather.api.model

enum class NwsTemperatureUnit(val rawValue: String) {
    CELSIUS("C"), FAHRENHEIT("F"), UNKNOWN("")
}

fun String.toTemperatureUnit(): NwsTemperatureUnit {
    return NwsTemperatureUnit.values().firstOrNull { it.rawValue == this } ?: NwsTemperatureUnit.UNKNOWN
}
