package com.piusvelte.nwsweather.data.mapper

import com.piusvelte.nwsweather.api.model.NwsCardinalDirection
import com.piusvelte.nwsweather.api.model.NwsCoordinate
import com.piusvelte.nwsweather.api.model.NwsForecast
import com.piusvelte.nwsweather.api.model.NwsForecastPeriod
import com.piusvelte.nwsweather.api.model.NwsForecastProperties
import com.piusvelte.nwsweather.api.model.NwsGeometryPoint
import com.piusvelte.nwsweather.api.model.NwsGeometryPolygon
import com.piusvelte.nwsweather.api.model.NwsPoint
import com.piusvelte.nwsweather.api.model.NwsPointProperties
import com.piusvelte.nwsweather.api.model.NwsRelativeLocation
import com.piusvelte.nwsweather.api.model.NwsTemperatureUnit
import com.piusvelte.nwsweather.api.model.NwsUnitValue
import com.piusvelte.nwsweather.data.dto.CardinalDirectionDto
import com.piusvelte.nwsweather.data.dto.CoordinateDto
import com.piusvelte.nwsweather.data.dto.ForecastDto
import com.piusvelte.nwsweather.data.dto.ForecastPeriodDto
import com.piusvelte.nwsweather.data.dto.ForecastPropertiesDto
import com.piusvelte.nwsweather.data.dto.GeometryPointDto
import com.piusvelte.nwsweather.data.dto.GeometryPolygonDto
import com.piusvelte.nwsweather.data.dto.PointDto
import com.piusvelte.nwsweather.data.dto.PropertiesDto
import com.piusvelte.nwsweather.data.dto.RelativeLocationDto
import com.piusvelte.nwsweather.data.dto.TemperatureUnitDto
import com.piusvelte.nwsweather.data.dto.UnitValueDto

fun NwsCoordinate.mapDto() = CoordinateDto(
    latitude,
    longitude,
)

fun NwsGeometryPoint.mapDto() = GeometryPointDto(
    type,
    coordinates.mapDto(),
)

fun NwsGeometryPolygon.mapDto() = GeometryPolygonDto(
    type,
    coordinates.map { nested -> nested.map { it.mapDto() } }
)

fun NwsRelativeLocation.mapDto() = RelativeLocationDto(
    type = type,
    geometry = geometry.mapDto(),
)

fun NwsPointProperties.mapDto() = PropertiesDto(
    id = id,
    type = type,
    cwa = cwa,
    forecastOffice = forecastOffice,
    gridId = gridId,
    gridX = gridX,
    gridY = gridY,
    forecast = forecast,
    forecastHourly = forecastHourly,
    forecastGridData = forecastGridData,
    observationStations = observationStations,
    relativeLocation = relativeLocation.mapDto(),
    forecastZone = forecastZone,
    county = county,
    fireWeatherZone = fireWeatherZone,
    timeZone = timeZone,
    radarStation = radarStation,
)

fun NwsPoint.mapDto() = PointDto(
    id = id,
    type = type,
    geometry = geometry.mapDto(),
    properties = properties.mapDto(),
)


fun NwsTemperatureUnit.mapDto() = when (this) {
    NwsTemperatureUnit.CELSIUS -> TemperatureUnitDto.CELSIUS
    NwsTemperatureUnit.FAHRENHEIT -> TemperatureUnitDto.FAHRENHEIT
    NwsTemperatureUnit.UNKNOWN -> TemperatureUnitDto.UNKNOWN
}

fun NwsUnitValue.mapDto() = UnitValueDto(
    unitCode,
    value,
)

fun NwsForecast.mapDto() = ForecastDto(
    type = type,
    geometry = geometry.mapDto(),
    properties = properties.mapDto(),
)

fun NwsForecastPeriod.mapDto() = ForecastPeriodDto(
    number = number,
    name = name,
    startTime = startTime,
    endTime = endTime,
    isDaytime = isDaytime,
    temperature = temperature,
    temperatureUnit = temperatureUnit.mapDto(),
    temperatureTrend = temperatureTrend,
    windSpeed = windSpeed,
    windDirection = windDirection.mapDto(),
    icon = icon,
    shortForecast = shortForecast,
    detailedForecast = detailedForecast,
)

fun NwsForecastProperties.mapDto() = ForecastPropertiesDto(
    updated = updated,
    units = units,
    forecastGenerator = forecastGenerator,
    generatedAt = generatedAt,
    updateTime = updateTime,
    validTimes = validTimes,
    elevation = elevation.mapDto(),
    periods = periods.map { it.mapDto() },
)

fun NwsCardinalDirection.mapDto() = when (this) {
    NwsCardinalDirection.N -> CardinalDirectionDto.NORTH
    NwsCardinalDirection.NNE -> CardinalDirectionDto.NORTH_NORTH_EAST
    NwsCardinalDirection.NE -> CardinalDirectionDto.NORTH_EAST
    NwsCardinalDirection.ENE -> CardinalDirectionDto.EAST_NORTH_EAST
    NwsCardinalDirection.E -> CardinalDirectionDto.EAST
    NwsCardinalDirection.ESE -> CardinalDirectionDto.EAST_SOUTH_EAST
    NwsCardinalDirection.SE -> CardinalDirectionDto.SOUTH_EAST
    NwsCardinalDirection.SSE -> CardinalDirectionDto.SOUTH_SOUTH_EAST
    NwsCardinalDirection.S -> CardinalDirectionDto.SOUTH
    NwsCardinalDirection.SSW -> CardinalDirectionDto.SOUTH_SOUTH_WEST
    NwsCardinalDirection.SW -> CardinalDirectionDto.SOUTH_WEST
    NwsCardinalDirection.WSW -> CardinalDirectionDto.WEST_SOUTH_WEST
    NwsCardinalDirection.W -> CardinalDirectionDto.WEST
    NwsCardinalDirection.WNW -> CardinalDirectionDto.WEST_NORTH_WEST
    NwsCardinalDirection.NW -> CardinalDirectionDto.NORTH_WEST
    NwsCardinalDirection.NNW -> CardinalDirectionDto.NORTH_NORTH_WEST
}
