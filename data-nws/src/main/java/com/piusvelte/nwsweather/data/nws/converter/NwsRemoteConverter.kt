package com.piusvelte.nwsweather.data.nws.converter

import com.piusvelte.data.local.entity.Point
import com.piusvelte.nwsweather.api.common.model.*
import com.piusvelte.nwsweather.api.gridpoints.model.NwsForecast
import com.piusvelte.nwsweather.api.gridpoints.model.NwsForecastPeriod
import com.piusvelte.nwsweather.api.gridpoints.model.NwsForecastProperties
import com.piusvelte.nwsweather.api.points.model.NwsPoint
import com.piusvelte.nwsweather.data.common.model.*
import com.piusvelte.nwsweather.data.gridpoints.model.Forecast
import com.piusvelte.nwsweather.data.gridpoints.model.ForecastPeriod
import com.piusvelte.nwsweather.data.gridpoints.model.ForecastProperties
import javax.inject.Inject

internal class NwsRemoteConverter @Inject constructor() : RemoteConverter {

    override fun convert(coordinate: NwsCoordinate): Coordinate {
        return Coordinate(coordinate.latitude, coordinate.longitude)
    }

    override fun convert(geometry: NwsGeometryPoint): GeometryPoint {
        return GeometryPoint(
            geometry.type,
            convert(geometry.coordinates),
        )
    }

    override fun convert(geometry: NwsGeometryPolygon): GeometryPolygon {
        return GeometryPolygon(
            geometry.type,
            geometry.coordinates.map { subList -> subList.map { convert(it) } }
        )
    }

    override fun convert(point: NwsPoint): Point {
        return Point(
            point.id,
            point.type,
            point.geometry.type,
            point.geometry.coordinates.latitude,
            point.geometry.coordinates.longitude,
            point.properties.id,
            point.properties.type,
            point.properties.cwa,
            point.properties.forecastOffice,
            point.properties.gridId,
            point.properties.gridX,
            point.properties.gridY,
            point.properties.forecast,
            point.properties.forecastHourly,
            point.properties.forecastGridData,
            point.properties.observationStations,
            point.properties.relativeLocation.type,
            point.properties.relativeLocation.geometry.type,
            point.properties.relativeLocation.geometry.coordinates.latitude,
            point.properties.relativeLocation.geometry.coordinates.longitude,
            point.properties.forecastZone,
            point.properties.county,
            point.properties.fireWeatherZone,
            point.properties.timeZone,
            point.properties.radarStation,
        )
    }

    override fun convert(unit: NwsTemperatureUnit): TemperatureUnit {
        return when (unit) {
            NwsTemperatureUnit.CELSIUS -> TemperatureUnit.CELSIUS
            NwsTemperatureUnit.FAHRENHEIT -> TemperatureUnit.FAHRENHEIT
            NwsTemperatureUnit.UNKNOWN -> TemperatureUnit.UNKNOWN
        }
    }

    override fun convert(value: NwsUnitValue): UnitValue {
        return UnitValue(value.unitCode, value.value)
    }

    override fun convert(forecast: NwsForecast): Forecast {
        return Forecast(
            forecast.type,
            convert(forecast.geometry),
            convert(forecast.properties),
        )
    }

    override fun convert(period: NwsForecastPeriod): ForecastPeriod {
        return ForecastPeriod(
            period.number,
            period.name,
            period.startTime,
            period.endTime,
            period.isDaytime,
            period.temperature,
            convert(period.temperatureUnit),
            period.temperatureTrend,
            period.windSpeed,
            convert(period.windDirection),
            period.icon,
            period.shortForecast,
            period.detailedForecast,
        )
    }

    override fun convert(properties: NwsForecastProperties): ForecastProperties {
        return ForecastProperties(
            properties.updated,
            properties.units,
            properties.forecastGenerator,
            properties.generatedAt,
            properties.updateTime,
            properties.validTimes,
            convert(properties.elevation),
            properties.periods.map { convert(it) },
        )
    }

    override fun convert(direction: NwsCardinalDirection): CardinalDirection {
        return when (direction) {
            NwsCardinalDirection.N -> CardinalDirection.NORTH
            NwsCardinalDirection.NNE -> CardinalDirection.NORTH_NORTH_EAST
            NwsCardinalDirection.NE -> CardinalDirection.NORTH_EAST
            NwsCardinalDirection.ENE -> CardinalDirection.EAST_NORTH_EAST
            NwsCardinalDirection.E -> CardinalDirection.EAST
            NwsCardinalDirection.ESE -> CardinalDirection.EAST_SOUTH_EAST
            NwsCardinalDirection.SE -> CardinalDirection.SOUTH_EAST
            NwsCardinalDirection.SSE -> CardinalDirection.SOUTH_SOUTH_EAST
            NwsCardinalDirection.S -> CardinalDirection.SOUTH
            NwsCardinalDirection.SSW -> CardinalDirection.SOUTH_SOUTH_WEST
            NwsCardinalDirection.SW -> CardinalDirection.SOUTH_WEST
            NwsCardinalDirection.WSW -> CardinalDirection.WEST_SOUTH_WEST
            NwsCardinalDirection.W -> CardinalDirection.WEST
            NwsCardinalDirection.WNW -> CardinalDirection.WEST_NORTH_WEST
            NwsCardinalDirection.NW -> CardinalDirection.NORTH_WEST
            NwsCardinalDirection.NNW -> CardinalDirection.NORTH_NORTH_WEST
        }
    }
}
