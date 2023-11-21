package com.piusvelte.nwsweather.domain.point

import com.piusvelte.nwsweather.domain.model.Point

interface PointState {
    data object Loading : PointState
    data class Failure(val message: String?) : PointState
    data class Success(val point: Point) : PointState
}
