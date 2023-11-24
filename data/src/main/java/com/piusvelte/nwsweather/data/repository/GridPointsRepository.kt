package com.piusvelte.nwsweather.data.repository

import com.piusvelte.nwsweather.api.service.NwsGridPointsService
import com.piusvelte.nwsweather.data.dto.ForecastDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.mapper.mapDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GridPointsRepository @Inject constructor(
    private val service: NwsGridPointsService,
    private val dispatcher: CoroutineDispatcher,
) {

    fun getForecast(office: String, gridX: Int, gridY: Int): Flow<ResourceDto<ForecastDto>> {
        return flow {
            emit(ResourceDto.Loading)

            try {
                val forecast = service.forecast(office, gridX, gridY)
                val dto = forecast.mapDto()
                emit(ResourceDto.Success(dto))
            } catch (e: Exception) {
                emit(ResourceDto.Error(e))
            }
        }.flowOn(dispatcher)
    }
}
