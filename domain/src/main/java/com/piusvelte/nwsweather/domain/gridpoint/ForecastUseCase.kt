package com.piusvelte.nwsweather.domain.gridpoint

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.piusvelte.nwsweather.data.dto.PointDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.repository.GridPointsRepository
import com.piusvelte.nwsweather.data.repository.PointsRepository
import com.piusvelte.nwsweather.domain.common.Hideable
import com.piusvelte.nwsweather.domain.common.mapHideable
import com.piusvelte.nwsweather.domain.mapper.mapPeriods
import com.piusvelte.nwsweather.domain.model.ForecastPeriod
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ForecastUseCase @Inject constructor(
    private val pointsRepository: PointsRepository,
    repository: GridPointsRepository,
) {

    private val location: MutableSharedFlow<Location> = MutableSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val forecastLocation: Flow<ForecastLocation> = location
        .flatMapLatest { pointsRepository.getPoint(it.latitude, it.longitude) }
        .filterIsInstance<ResourceDto.Success<PointDto>>()
        .map { it.data.forecastLocation() }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val forecast = forecastLocation
        .flatMapLatest { repository.getForecast(it.office, it.gridX, it.gridY) }
        .asLiveData()

    suspend fun onLocation(latitude: Float, longitude: Float) {
        // TODO loading is not propagated through
        location.emit(Location(latitude, longitude))
    }

    fun isLoading(): LiveData<Boolean> {
        return forecast.map { it is ResourceDto.Loading }
    }

    fun forecast(): LiveData<Hideable<List<ForecastPeriod>>> {
        return forecast.mapHideable { it.mapPeriods() }
    }

    data class Location(val latitude: Float, val longitude: Float)

    data class ForecastLocation(val office: String, val gridX: Int, val gridY: Int)

    private fun PointDto.forecastLocation(): ForecastLocation {
        return ForecastLocation(properties.gridId, properties.gridX, properties.gridY)
    }
}
