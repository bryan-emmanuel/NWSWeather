package com.piusvelte.nwsweather.data.dto

import com.piusvelte.nwsweather.data.dto.CoordinateDto

data class GeometryPolygonDto(
    val type: String,
    val coordinates: List<List<CoordinateDto>>,
)
