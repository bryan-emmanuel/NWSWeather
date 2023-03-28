package com.piusvelte.nwsweather.data.nws.gridpoints

import com.piusvelte.nwsweather.api.gridpoints.NwsGridPointsService
import com.piusvelte.nwsweather.data.common.model.Resource
import com.piusvelte.nwsweather.data.gridpoints.GridPointsRepository
import com.piusvelte.nwsweather.data.gridpoints.model.Forecast
import com.piusvelte.nwsweather.data.nws.converter.RemoteConverter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class NwsGridPointsRepository @Inject constructor(
    private val service: NwsGridPointsService,
    private val converter: RemoteConverter,
    private val dispatcher: CoroutineDispatcher,
) : GridPointsRepository {

    override fun getForecast(office: String, gridX: Int, gridY: Int): Flow<Resource<Forecast>> {
        return flow {
            emit(Resource.Loading)

            try {
                val forecast = service.forecast(office, gridX, gridY)
                val converted = converter.convert(forecast)
                emit(Resource.Success(converted))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }.flowOn(dispatcher)
    }
}
