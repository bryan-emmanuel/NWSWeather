package com.piusvelte.nwsweather.data.repository

import com.piusvelte.nwsweather.api.service.NwsPointsService
import com.piusvelte.nwsweather.data.dto.PointDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.mapper.mapDto
import com.piusvelte.nwsweather.data.mapper.mapEntity
import com.piusvelte.nwsweather.database.dao.PointDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PointsRepository @Inject constructor(
    private val service: NwsPointsService,
    private val pointDao: PointDao,
    private val dispatcher: CoroutineDispatcher,
) {

    fun getPoint(latitude: Float, longitude: Float): Flow<ResourceDto<PointDto>> {
        return merge(
//            localPoint(latitude, longitude),
            remotePoint(latitude, longitude),
        )
            .onStart { emit(ResourceDto.Loading) }
            .flowOn(dispatcher)
    }

    private fun localPoint(latitude: Float, longitude: Float): Flow<ResourceDto<PointDto>> {
        return pointDao.get(pointId(latitude, longitude))
            .filterNotNull()
            .map { ResourceDto.Success(it.mapDto()) }
    }

    private fun remotePoint(latitude: Float, longitude: Float): Flow<ResourceDto<PointDto>> {
        return flow {
            try {
                // TODO data should flow from the DAO, updated from API
                val point = service.points(latitude, longitude)
                val dto = point.mapDto()
                val entity = dto.mapEntity()
                pointDao.insert(entity)
                emit(ResourceDto.Success(dto))
            } catch (e: Exception) {
                emit(ResourceDto.Error(e))
            }
        }
    }

    companion object {
        private fun pointId(latitude: Float, longitude: Float): String {
            return "https://api.weather.gov/points/$latitude,$longitude"
        }
    }
}
