package com.piusvelte.nwsweather.data.mapper

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
import com.piusvelte.nwsweather.database.entity.CoordinateEntity
import com.piusvelte.nwsweather.database.entity.ForecastEntity
import com.piusvelte.nwsweather.database.entity.ForecastPeriodEntity
import com.piusvelte.nwsweather.database.entity.ForecastWithRelations
import com.piusvelte.nwsweather.database.entity.PointEntity

fun PointEntity.mapCoordinateDto() = CoordinateDto(
    latitude = geometryCoordinateLatitude,
    longitude = geometryCoordinateLongitude,
)

fun PointEntity.mapGeometryPointDto() = GeometryPointDto(
    type = geometryType,
    coordinates = mapCoordinateDto(),
)

fun PointEntity.mapPropertiesRelativeLocationGeometryCoordinateDto() = CoordinateDto(
    latitude = geometryCoordinateLatitude,
    longitude = geometryCoordinateLongitude,
)

fun PointEntity.mapPropertiesRelativeLocationGeometryDto() = GeometryPointDto(
    type = propertyRelativeLocationGeometryType,
    coordinates = mapPropertiesRelativeLocationGeometryCoordinateDto(),
)

fun PointEntity.mapPropertiesRelativeLocationDto() = RelativeLocationDto(
    type = propertyRelativeLocationType,
    geometry = mapPropertiesRelativeLocationGeometryDto(),
)

fun PointEntity.mapPropertiesDto() = PropertiesDto(
    id = propertyId,
    type = propertyType,
    cwa = propertyCwa,
    forecastOffice = propertyForecastOffice,
    gridId = propertyGridId,
    gridX = propertyGridX,
    gridY = propertyGridY,
    forecast = propertyForecast,
    forecastHourly = propertyForecastHourly,
    forecastGridData = propertyForecastGridData,
    observationStations = propertyObservationStations,
    relativeLocation = mapPropertiesRelativeLocationDto(),
    forecastZone = propertyForecastZone,
    county = propertyCounty,
    fireWeatherZone = propertyFireWeatherZone,
    timeZone = propertyTimeZone,
    radarStation = propertyRadarStation,
)

fun PointEntity.mapDto() = PointDto(
    id = id,
    type = type,
    geometry = mapGeometryPointDto(),
    properties = mapPropertiesDto(),
)

fun ForecastWithRelations.mapDto() = ForecastDto(
    type = forecast.type,
    geometry = mapGeometry(),
    properties = mapProperties(),
)

fun ForecastWithRelations.mapGeometry() = GeometryPolygonDto(
    type = forecast.geometryType,
    coordinates = coordinates.map { nested -> nested.coordinates.map { it.mapCoordinate() } }
)

fun CoordinateEntity.mapCoordinate() = CoordinateDto(latitude, longitude)

fun ForecastWithRelations.mapProperties() = ForecastPropertiesDto(
    updated = forecast.updated,
    units = forecast.units,
    forecastGenerator = forecast.forecastGenerator,
    generatedAt = forecast.generatedAt,
    updateTime = forecast.updateTime,
    validTimes = forecast.validTimes,
    elevation = forecast.mapElevation(),
    periods = mapPeriods(),
)

fun ForecastEntity.mapElevation() = UnitValueDto(elevationUnitCode, elevationValue)

fun ForecastWithRelations.mapPeriods() = periods.map { it.mapPeriod() }

fun ForecastPeriodEntity.mapPeriod() = ForecastPeriodDto(
    number = number,
    name = name,
    startTime = startTime,
    endTime = endTime,
    isDaytime = isDaytime,
    temperature = temperature,
    temperatureUnit = mapTemperatureUnit(),
    temperatureTrend = temperatureTrend,
    windSpeed = windSpeed,
    windDirection = mapDirection(),
    icon = icon,
    shortForecast = shortForecast,
    detailedForecast = detailedForecast,
)

fun ForecastPeriodEntity.mapTemperatureUnit() = when (temperatureUnit) {
    "celsius" -> TemperatureUnitDto.CELSIUS
    "fahrenheit" -> TemperatureUnitDto.FAHRENHEIT
    else -> TemperatureUnitDto.UNKNOWN
}

fun ForecastPeriodEntity.mapDirection() = when (windDirection) {
    "north" -> CardinalDirectionDto.NORTH
    "north_north_east" -> CardinalDirectionDto.NORTH_NORTH_EAST
    "north_east" -> CardinalDirectionDto.NORTH_EAST
    "east_north_east" -> CardinalDirectionDto.EAST_NORTH_EAST
    "east" -> CardinalDirectionDto.EAST
    "east_south_east" -> CardinalDirectionDto.EAST_SOUTH_EAST
    "south_east" -> CardinalDirectionDto.SOUTH_EAST
    "south_south_east" -> CardinalDirectionDto.SOUTH_SOUTH_EAST
    "south" -> CardinalDirectionDto.SOUTH
    "south_south_west" -> CardinalDirectionDto.SOUTH_SOUTH_WEST
    "south_west" -> CardinalDirectionDto.SOUTH_WEST
    "west_south_west" -> CardinalDirectionDto.WEST_SOUTH_WEST
    "west" -> CardinalDirectionDto.WEST
    "west_north_west" -> CardinalDirectionDto.WEST_NORTH_WEST
    "north_west" -> CardinalDirectionDto.NORTH_WEST
    "north_north_west" -> CardinalDirectionDto.NORTH_NORTH_WEST
    else -> CardinalDirectionDto.NORTH
}
