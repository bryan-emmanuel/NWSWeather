package com.piusvelte.nwsweather.data.repository

import com.piusvelte.nwsweather.data.dto.CoordinateDto
import com.piusvelte.nwsweather.data.dto.PointDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.mapper.mapDto
import com.piusvelte.nwsweather.database.dao.PointDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.cos

class LocalPointUseCase @Inject constructor(
    private val pointDao: PointDao,
) {

    operator fun invoke(
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
                    null
                }
            }.filterNotNull()
    }
}

private const val EARTH_RADIUS_KM = 6378
private const val NWS_GRID_SIZE_KM = 2.5
private const val NWS_GRID_SIZE_LATITUDE = (NWS_GRID_SIZE_KM / EARTH_RADIUS_KM) * (180 / Math.PI)
private fun CoordinateDto.nwsGridSizeLongitude() =
    NWS_GRID_SIZE_LATITUDE / cos(latitude * Math.PI / 180)