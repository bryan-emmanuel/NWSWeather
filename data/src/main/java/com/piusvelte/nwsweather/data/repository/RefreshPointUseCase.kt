package com.piusvelte.nwsweather.data.repository

import com.piusvelte.nwsweather.api.service.NwsPointsService
import com.piusvelte.nwsweather.data.dto.CoordinateDto
import com.piusvelte.nwsweather.data.dto.PointDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.mapper.mapEntity
import com.piusvelte.nwsweather.database.dao.PointDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RefreshPointUseCase @Inject constructor(
    private val pointDao: PointDao,
    private val service: NwsPointsService,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(refresh: MutableSharedFlow<CoordinateDto>): Flow<ResourceDto<PointDto>> =
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