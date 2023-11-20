package com.piusvelte.nwsweather.data.dto

import com.piusvelte.nwsweather.data.dto.CoordinateDto

data class GeometryPointDto(
    val type: String,
    val coordinates: CoordinateDto,
)
