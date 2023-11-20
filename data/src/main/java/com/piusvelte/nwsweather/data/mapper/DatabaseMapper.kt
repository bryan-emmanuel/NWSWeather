package com.piusvelte.nwsweather.data.mapper

import com.piusvelte.nwsweather.data.dto.CoordinateDto
import com.piusvelte.nwsweather.data.dto.GeometryPointDto
import com.piusvelte.nwsweather.data.dto.PointDto
import com.piusvelte.nwsweather.data.dto.PropertiesDto
import com.piusvelte.nwsweather.data.dto.RelativeLocationDto
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

fun PointDto.mapEntity() = PointEntity(
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
