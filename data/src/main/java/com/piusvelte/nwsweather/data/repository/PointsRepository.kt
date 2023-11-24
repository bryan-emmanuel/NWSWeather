package com.piusvelte.nwsweather.data.repository

import com.piusvelte.nwsweather.api.service.NwsPointsService
import com.piusvelte.nwsweather.data.dto.CoordinateDto
import com.piusvelte.nwsweather.data.dto.PointDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.mapper.mapDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PointsRepository @Inject constructor(
    private val service: NwsPointsService,
    private val dispatcher: CoroutineDispatcher,
) {

    fun getPoint(coordinate: CoordinateDto): Flow<ResourceDto<PointDto>> {
        return flow {
            emit(ResourceDto.Loading)

            try {
                val point = service.points(coordinate.latitude, coordinate.longitude)
                val dto = point.mapDto()
                emit(ResourceDto.Success(dto))
            } catch (e: Exception) {
                flowOf(ResourceDto.Error(e))
            }
        }.flowOn(dispatcher)
    }
}
