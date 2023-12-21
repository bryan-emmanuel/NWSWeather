package com.piusvelte.nwsweather.data.mapper

import com.piusvelte.nwsweather.api.model.NwsCardinalDirection
import com.piusvelte.nwsweather.api.model.NwsCoordinate
import com.piusvelte.nwsweather.api.model.NwsForecast
import com.piusvelte.nwsweather.api.model.NwsForecastPeriod
import com.piusvelte.nwsweather.api.model.NwsGeometryPolygon
import com.piusvelte.nwsweather.api.model.NwsPoint
import com.piusvelte.nwsweather.api.model.NwsTemperatureUnit
import com.piusvelte.nwsweather.database.entity.CoordinateContainerEntity
import com.piusvelte.nwsweather.database.entity.CoordinateEntity
import com.piusvelte.nwsweather.database.entity.CoordinateWithChildren
import com.piusvelte.nwsweather.database.entity.ForecastEntity
import com.piusvelte.nwsweather.database.entity.ForecastPeriodEntity
import com.piusvelte.nwsweather.database.entity.ForecastWithRelations
import com.piusvelte.nwsweather.database.entity.PointEntity

fun NwsPoint.mapEntity() = PointEntity(
    id = id,
    type = type,
    geometryType = geometry.type,
    geometryCoordinateLatitude = geometry.coordinates.latitude,
    geometryCoordinateLongitude = geometry.coordinates.longitude,
    propertyId = properties.id,
    propertyType = properties.type,
    propertyCwa = properties.cwa,
    propertyForecastOffice = properties.forecastOffice,
    propertyGridId = properties.gridId,
    propertyGridX = properties.gridX,
    propertyGridY = properties.gridY,
    propertyForecast = properties.forecast,
    propertyForecastHourly = properties.forecastHourly,
    propertyForecastGridData = properties.forecastGridData,
    propertyObservationStations = properties.observationStations,
    propertyRelativeLocationType = properties.relativeLocation.type,
    propertyRelativeLocationGeometryType = properties.relativeLocation.geometry.type,
    propertyRelativeLocationGeometryCoordinateLatitude = properties.relativeLocation.geometry.coordinates.latitude,
    propertyRelativeLocationGeometryCoordinateLongitude = properties.relativeLocation.geometry.coordinates.longitude,
    propertyForecastZone = properties.forecastZone,
    propertyCounty = properties.county,
    propertyFireWeatherZone = properties.fireWeatherZone,
    propertyTimeZone = properties.timeZone,
    propertyRadarStation = properties.radarStation,
)

fun NwsForecast.mapEntity() = ForecastWithRelations(
    forecast = mapForecast(),
    coordinates = geometry.mapCoordinates(),
    periods = mapPeriods(),
)

fun NwsCoordinate.mapEntity() = CoordinateEntity(
    id = 0,
    coordinateId = 0,
    latitude = latitude,
    longitude = longitude,
)

fun NwsTemperatureUnit.mapEntity() = when (this) {
    NwsTemperatureUnit.CELSIUS -> "celsius"
    NwsTemperatureUnit.FAHRENHEIT -> "fahrenheit"
    NwsTemperatureUnit.UNKNOWN -> "unknown"
}

fun NwsForecast.mapForecast() = ForecastEntity(
    id = 0,
    type = type,
    geometryType = geometry.type,
    updated = properties.updated,
    units = properties.units,
    forecastGenerator = properties.forecastGenerator,
    generatedAt = properties.generatedAt,
    updateTime = properties.updateTime,
    validTimes = properties.validTimes,
    elevationUnitCode = properties.elevation.unitCode,
    elevationValue = properties.elevation.value,
)

fun NwsGeometryPolygon.mapCoordinates() =
    coordinates.map { container ->
        CoordinateWithChildren(
            containerEntity = CoordinateContainerEntity(
                id = 0,
                forecastId = 0,
            ),
            coordinates = container.map { it.mapEntity() },
        )
    }

fun NwsForecast.mapPeriods() = properties.periods.map { it.mapEntity() }

fun NwsForecastPeriod.mapEntity() = ForecastPeriodEntity(
    id = 0,
    forecastId = 0,
    number = number,
    name = name,
    startTime = startTime,
    endTime = endTime,
    isDaytime = isDaytime,
    temperature = temperature,
    temperatureUnit = temperatureUnit.mapEntity(),
    temperatureTrend = temperatureTrend,
    windSpeed = windSpeed,
    windDirection = windDirection.mapEntity(),
    icon = icon,
    shortForecast = shortForecast,
    detailedForecast = detailedForecast,
)

fun NwsCardinalDirection.mapEntity() = when (this) {
    NwsCardinalDirection.N -> "north"
    NwsCardinalDirection.NNE -> "north_north_east"
    NwsCardinalDirection.NE -> "north_east"
    NwsCardinalDirection.ENE -> "east_north_east"
    NwsCardinalDirection.E -> "east"
    NwsCardinalDirection.ESE -> "east_south_east"
    NwsCardinalDirection.SE -> "south_east"
    NwsCardinalDirection.SSE -> "south_south_east"
    NwsCardinalDirection.S -> "south"
    NwsCardinalDirection.SSW -> "south_south_west"
    NwsCardinalDirection.SW -> "south_west"
    NwsCardinalDirection.WSW -> "west_south_west"
    NwsCardinalDirection.W -> "west"
    NwsCardinalDirection.WNW -> "west_north_west"
    NwsCardinalDirection.NW -> "north_west"
    NwsCardinalDirection.NNW -> "north_north_west"
}
