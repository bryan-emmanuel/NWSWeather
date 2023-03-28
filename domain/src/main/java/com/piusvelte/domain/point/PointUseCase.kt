package com.piusvelte.domain.point

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.piusvelte.domain.common.*
import com.piusvelte.nwsweather.data.common.model.Coordinate
import com.piusvelte.nwsweather.data.common.model.Resource
import com.piusvelte.nwsweather.data.points.PointsRepository
import com.piusvelte.nwsweather.data.points.model.Point
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class PointUseCase @Inject constructor(private val repository: PointsRepository) {

    private val location: MutableSharedFlow<Coordinate> = MutableSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val point: LiveData<Resource<Point>> = location
        .flatMapLatest { repository.getPoint(it.latitude, it.longitude) }
        .asLiveData()

    suspend fun onLocation(latitude: Float, longitude: Float) {
        val coordinate = Coordinate(latitude, longitude)
        location.emit(coordinate)
    }

    fun isLoading(): LiveData<Boolean> {
        return point.map { it is Resource.Loading }
    }

    fun pointId(): LiveData<Hideable<String>> {
        return point.mapHideable { it.id }
    }

    fun pointGridId(): LiveData<Hideable<String>> {
        return point.mapHideable { it.properties.gridId }
    }

    fun pointGridX(): LiveData<Hideable<Int>> {
        return point.mapHideable { it.properties.gridX }
    }

    fun pointGridY(): LiveData<Hideable<Int>> {
        return point.mapHideable { it.properties.gridY }
    }

    fun pointError(): LiveData<ConsumableEvent<out String>> {
        return point.map { resource ->
            val error = resource as? Resource.Error ?: return@map NoOp()
            val message = error.exception.message ?: return@map NoOp()
            Consumable(message)
        }
    }
}
