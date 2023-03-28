package com.piusvelte.domain.gridpoint

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.piusvelte.domain.common.Hideable
import com.piusvelte.domain.common.mapHideable
import com.piusvelte.nwsweather.data.common.model.Resource
import com.piusvelte.nwsweather.data.gridpoints.GridPointsRepository
import com.piusvelte.nwsweather.data.gridpoints.model.ForecastPeriod
import com.piusvelte.nwsweather.data.points.PointsRepository
import com.piusvelte.nwsweather.data.points.model.Point
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
        .filterIsInstance<Resource.Success<Point>>()
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
        return forecast.map { it is Resource.Loading }
    }

    fun forecast(): LiveData<Hideable<List<ForecastPeriod>>> {
        return forecast.mapHideable { it.properties.periods }
    }

    data class Location(val latitude: Float, val longitude: Float)

    data class ForecastLocation(val office: String, val gridX: Int, val gridY: Int)

    private fun Point.forecastLocation(): ForecastLocation {
        return ForecastLocation(properties.gridId, properties.gridX, properties.gridY)
    }
}
