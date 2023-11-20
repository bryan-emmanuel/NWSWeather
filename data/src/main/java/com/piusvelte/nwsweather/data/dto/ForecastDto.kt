package com.piusvelte.nwsweather.data.dto

data class ForecastDto(
    val type: String,
    val geometry: GeometryPolygonDto,
    val properties: ForecastPropertiesDto,
)
