package com.piusvelte.nwsweather.data.nws.points

import com.piusvelte.data.local.dao.PointDao
import com.piusvelte.nwsweather.api.points.NwsPointsService
import com.piusvelte.nwsweather.data.common.model.Resource
import com.piusvelte.nwsweather.data.nws.converter.LocalConverter
import com.piusvelte.nwsweather.data.nws.converter.RemoteConverter
import com.piusvelte.nwsweather.data.points.PointsRepository
import com.piusvelte.nwsweather.data.points.model.Point
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

internal class NwsPointsRepository @Inject constructor(
    private val service: NwsPointsService,
    private val localConverter: LocalConverter,
    private val remoteConverter: RemoteConverter,
    private val pointDao: PointDao,
    private val dispatcher: CoroutineDispatcher,
) : PointsRepository {

    override fun getPoint(latitude: Float, longitude: Float): Flow<Resource<Point>> {
        return merge(
//            localPoint(latitude, longitude),
            remotePoint(latitude, longitude),
        )
            .onStart { emit(Resource.Loading) }
            .flowOn(dispatcher)
    }

    private fun localPoint(latitude: Float, longitude: Float): Flow<Resource<Point>> {
        return pointDao.get(pointId(latitude, longitude))
            .filterNotNull()
            .map { Resource.Success(localConverter.convert(it)) }
    }

    private fun remotePoint(latitude: Float, longitude: Float): Flow<Resource<Point>> {
        return flow {
            try {
                val point = service.points(latitude, longitude)
                val local = remoteConverter.convert(point)
                pointDao.insert(local)
                emit(Resource.Success(localConverter.convert(local)))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    companion object {
        private fun pointId(latitude: Float, longitude: Float): String {
            return "https://api.weather.gov/points/$latitude,$longitude"
        }
    }
}
