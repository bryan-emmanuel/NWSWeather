package com.piusvelte.nwsweather.data.points.model

import com.piusvelte.nwsweather.data.common.model.GeometryPoint

data class Point(
    val id: String,
    val type: String,
    val geometry: GeometryPoint,
    val properties: Properties,
)
