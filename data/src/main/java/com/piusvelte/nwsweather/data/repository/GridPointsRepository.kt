package com.piusvelte.nwsweather.data.repository

import com.piusvelte.nwsweather.api.service.NwsGridPointsService
import com.piusvelte.nwsweather.data.dto.ForecastDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.mapper.mapDto
import com.piusvelte.nwsweather.data.mapper.mapEntity
import com.piusvelte.nwsweather.data.mapper.mapForecast
import com.piusvelte.nwsweather.database.dao.PointDao
import com.piusvelte.nwsweather.database.entity.CoordinateContainerEntity
import com.piusvelte.nwsweather.database.entity.CoordinateWithChildren
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GridPointsRepository @Inject constructor(
    private val pointDao: PointDao,
    private val service: NwsGridPointsService,
    private val dispatcher: CoroutineDispatcher,
) {

    fun getForecast(office: String, gridX: Int, gridY: Int): Flow<ResourceDto<ForecastDto>> {
        return flow {
            emit(ResourceDto.Loading)

            try {
                val forecast = service.forecast(office, gridX, gridY)
                val entity = forecast.mapEntity()
                // TODO insert entity, and observe flow from dao
                val dto = entity.mapDto()
                emit(ResourceDto.Success(dto))
            } catch (e: Exception) {
                emit(ResourceDto.Error(e))
            }
        }.flowOn(dispatcher)
    }
}
