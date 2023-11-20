package com.piusvelte.nwsweather.domain.mapper

import com.piusvelte.nwsweather.data.dto.ForecastDto
import com.piusvelte.nwsweather.data.dto.ForecastPeriodDto
import com.piusvelte.nwsweather.data.dto.PointDto
import com.piusvelte.nwsweather.data.dto.PropertiesDto
import com.piusvelte.nwsweather.data.dto.TemperatureUnitDto
import com.piusvelte.nwsweather.domain.model.ForecastPeriod
import com.piusvelte.nwsweather.domain.model.Point
import com.piusvelte.nwsweather.domain.model.Properties
import com.piusvelte.nwsweather.domain.model.TemperatureUnit

fun PropertiesDto.mapDomain() = Properties(
    gridId = gridId,
    gridX = gridX,
    gridY = gridY,
)

fun PointDto.mapDomain() = Point(
    id = id,
    properties = properties.mapDomain(),
)

fun ForecastDto.mapPeriods() = properties.periods.map { it.mapDomain() }

fun TemperatureUnitDto.mapDomain() = when (this) {
    TemperatureUnitDto.CELSIUS -> TemperatureUnit.CELSIUS
    TemperatureUnitDto.FAHRENHEIT -> TemperatureUnit.FAHRENHEIT
    TemperatureUnitDto.UNKNOWN -> TemperatureUnit.UNKNOWN
}

fun ForecastPeriodDto.mapDomain() = ForecastPeriod(
    number = number,
    name = name,
    icon = icon,
    temperature = temperature,
    temperatureUnit = temperatureUnit.mapDomain(),
    shortForecast = shortForecast,
)