package com.piusvelte.nwsweather.data.dto

data class PointDto(
    val id: String,
    val type: String,
    val geometry: GeometryPointDto,
    val properties: PropertiesDto,
)
