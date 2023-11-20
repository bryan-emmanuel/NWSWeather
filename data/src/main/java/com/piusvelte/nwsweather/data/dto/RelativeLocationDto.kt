package com.piusvelte.nwsweather.data.dto

import com.piusvelte.nwsweather.data.dto.GeometryPointDto

data class RelativeLocationDto(
    val type: String,
    val geometry: GeometryPointDto,
)
