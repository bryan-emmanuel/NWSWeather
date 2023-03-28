package com.piusvelte.nwsweather.data.gridpoints.model

import com.piusvelte.nwsweather.data.common.model.GeometryPolygon

data class Forecast(
    val type: String,
    val geometry: GeometryPolygon,
    val properties: ForecastProperties,
)
