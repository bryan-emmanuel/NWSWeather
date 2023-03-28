package com.piusvelte.nwsweather.data.points.model

import com.piusvelte.nwsweather.data.common.model.GeometryPoint

data class RelativeLocation(
    val type: String,
    val geometry: GeometryPoint,
)
