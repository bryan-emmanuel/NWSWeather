package com.piusvelte.nwsweather.data.common.model

data class GeometryPolygon(
    val type: String,
    val coordinates: List<List<Coordinate>>,
)
