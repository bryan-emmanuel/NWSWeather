package com.piusvelte.nwsweather.domain.gridpoint

import com.piusvelte.nwsweather.data.dto.PointDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.repository.GridPointsRepository
import com.piusvelte.nwsweather.data.repository.PointsRepository
import com.piusvelte.nwsweather.domain.mapper.mapDto
import com.piusvelte.nwsweather.domain.mapper.mapPeriods
import com.piusvelte.nwsweather.domain.model.GeoLocation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetForecastUseCase @Inject constructor(
    private val pointsRepository: PointsRepository,
    private val repository: GridPointsRepository,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(location: GeoLocation): Flow<ForecastState> =
        pointsRepository.getPoint(location.mapDto())
            .filterIsInstance<ResourceDto.Success<PointDto>>()
            .flatMapLatest {
                val properties = it.data.properties
                repository.getForecast(properties.gridId, properties.gridX, properties.gridY)
                    .map { resource ->
                        when (resource) {
                            is ResourceDto.Error -> ForecastState.Failure(resource.exception.message)
                            is ResourceDto.Loading -> ForecastState.Loading
                            is ResourceDto.Success -> ForecastState.Success(resource.data.mapPeriods())
                        }
                    }
            }
}
