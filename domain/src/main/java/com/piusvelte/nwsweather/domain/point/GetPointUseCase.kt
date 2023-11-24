package com.piusvelte.nwsweather.domain.point

import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.repository.PointsRepository
import com.piusvelte.nwsweather.domain.mapper.mapDomain
import com.piusvelte.nwsweather.domain.mapper.mapDto
import com.piusvelte.nwsweather.domain.model.GeoLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPointUseCase @Inject constructor(
    private val repository: PointsRepository
) {

    operator fun invoke(location: GeoLocation): Flow<PointState> =
        repository.getPoint(location.mapDto())
            .map {
                when (it) {
                    is ResourceDto.Loading -> PointState.Loading
                    is ResourceDto.Error -> PointState.Failure(it.exception.message)
                    is ResourceDto.Success -> PointState.Success(it.data.mapDomain())
                }
            }
}
