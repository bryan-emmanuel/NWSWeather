package com.piusvelte.nwsweather.data.nws.converter

import com.piusvelte.nwsweather.data.points.model.Point

typealias LocalPoint = com.piusvelte.data.local.entity.Point

interface LocalConverter {
    fun convert(point: LocalPoint): Point
}
