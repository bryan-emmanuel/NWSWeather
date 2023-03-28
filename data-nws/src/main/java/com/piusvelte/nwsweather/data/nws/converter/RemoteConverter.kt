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

interface RemoteConverter {
    fun convert(point: NwsPoint): Point

    fun convert(forecast: NwsForecast): Forecast

    fun convert(period: NwsForecastPeriod): ForecastPeriod

    fun convert(properties: NwsForecastProperties): ForecastProperties

    fun convert(direction: NwsCardinalDirection): CardinalDirection

    fun convert(value: NwsUnitValue): UnitValue

    fun convert(unit: NwsTemperatureUnit): TemperatureUnit

    fun convert(geometry: NwsGeometryPoint): GeometryPoint

    fun convert(geometry: NwsGeometryPolygon): GeometryPolygon

    fun convert(coordinate: NwsCoordinate): Coordinate
}
