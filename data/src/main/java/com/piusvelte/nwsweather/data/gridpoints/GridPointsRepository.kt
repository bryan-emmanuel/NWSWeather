package com.piusvelte.nwsweather.data.gridpoints

import com.piusvelte.nwsweather.data.common.model.Resource
import com.piusvelte.nwsweather.data.gridpoints.model.Forecast
import kotlinx.coroutines.flow.Flow

interface GridPointsRepository {

    fun getForecast(office: String, gridX: Int, gridY: Int): Flow<Resource<Forecast>>
}
