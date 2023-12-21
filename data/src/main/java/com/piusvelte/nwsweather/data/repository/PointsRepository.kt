package com.piusvelte.nwsweather.data.repository

import com.piusvelte.nwsweather.api.service.NwsPointsService
import com.piusvelte.nwsweather.data.dto.CoordinateDto
import com.piusvelte.nwsweather.data.dto.PointDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.mapper.mapDto
import com.piusvelte.nwsweather.data.mapper.mapEntity
import com.piusvelte.nwsweather.database.dao.PointDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.cos

@Singleton
class PointsRepository @Inject constructor(
    private val pointDao: PointDao,
    private val service: NwsPointsService,
    private val dispatcher: CoroutineDispatcher,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPoint(coordinate: CoordinateDto): Flow<ResourceDto<PointDto>> {
        val refresh = MutableSharedFlow<CoordinateDto>()

        return flowOf(
            flowOf(ResourceDto.Loading),
            merge(
                localPoint(coordinate, refresh),
                remotePoint(refresh),
            ),
        )
            .flattenConcat()
            .flowOn(dispatcher)
    }

    private fun localPoint(
        coordinate: CoordinateDto,
        refresh: MutableSharedFlow<CoordinateDto>
    ): Flow<ResourceDto<PointDto>> {
        val minLongitude = coordinate.longitude - coordinate.nwsGridSizeLongitude()
        val maxLongitude = coordinate.longitude + coordinate.nwsGridSizeLongitude()
        val minLatitude = coordinate.latitude - NWS_GRID_SIZE_LATITUDE
        val maxLatitude = coordinate.latitude + NWS_GRID_SIZE_LATITUDE

        return pointDao.search(
            minLongitude.toFloat(),
            maxLongitude.toFloat(),
            minLatitude.toFloat(),
            maxLatitude.toFloat(),
        )
            .map {
                if (it != null) {
                    ResourceDto.Success(it.mapDto())
                } else {
                    refresh.emit(coordinate)
                    //e: kotlinx.coroutines.flow.internal.ChildCancelledException: Child of the scoped flow was cancelled
                    null
                }
            }.filterNotNull()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun remotePoint(refresh: MutableSharedFlow<CoordinateDto>): Flow<ResourceDto<PointDto>> =
        refresh
            .distinctUntilChanged()
            .flatMapLatest {
                try {
                    val point = service.points(it.latitude, it.longitude)
                    val entity = point.mapEntity()
                    pointDao.insert(entity)
                    emptyFlow()
                } catch (e: Exception) {
                    flowOf(ResourceDto.Error(e))
                }
            }
}


private const val EARTH_RADIUS_KM = 6378
private const val NWS_GRID_SIZE_KM = 2.5
private const val NWS_GRID_SIZE_LATITUDE = (NWS_GRID_SIZE_KM / EARTH_RADIUS_KM) * (180 / Math.PI)
private fun CoordinateDto.nwsGridSizeLongitude() =
    NWS_GRID_SIZE_LATITUDE / cos(latitude * Math.PI / 180)
