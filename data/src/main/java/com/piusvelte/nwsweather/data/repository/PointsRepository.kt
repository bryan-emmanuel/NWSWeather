package com.piusvelte.nwsweather.data.repository

import com.piusvelte.nwsweather.data.dto.CoordinateDto
import com.piusvelte.nwsweather.data.dto.PointDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PointsRepository @Inject constructor(
    private val points: LocalPointUseCase,
    private val refresher: RefreshPointUseCase,
    private val dispatcher: CoroutineDispatcher,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPoint(coordinate: CoordinateDto): Flow<ResourceDto<PointDto>> {
        val refresh = MutableSharedFlow<CoordinateDto>()

        return flowOf(
            flowOf(ResourceDto.Loading),
            merge(
                points(coordinate, refresh),
                refresher(refresh),
            ),
        )
            .flattenConcat()
            .flowOn(dispatcher)
    }
}
