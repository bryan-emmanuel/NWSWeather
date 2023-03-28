package com.piusvelte.nwsweather.data.points

import com.piusvelte.nwsweather.data.common.model.Resource
import com.piusvelte.nwsweather.data.points.model.Point
import kotlinx.coroutines.flow.Flow

interface PointsRepository {

    fun getPoint(latitude: Float, longitude: Float): Flow<Resource<Point>>
}
