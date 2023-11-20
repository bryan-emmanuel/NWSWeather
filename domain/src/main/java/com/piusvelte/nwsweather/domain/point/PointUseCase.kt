package com.piusvelte.nwsweather.domain.point

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.piusvelte.nwsweather.data.dto.CoordinateDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.repository.PointsRepository
import com.piusvelte.nwsweather.domain.common.Consumable
import com.piusvelte.nwsweather.domain.common.ConsumableEvent
import com.piusvelte.nwsweather.domain.common.Hideable
import com.piusvelte.nwsweather.domain.common.NoOp
import com.piusvelte.nwsweather.domain.common.mapHideable
import com.piusvelte.nwsweather.domain.mapper.mapDomain
import com.piusvelte.nwsweather.domain.model.Point
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PointUseCase @Inject constructor(
    private val repository: PointsRepository
) {

    private val location: MutableSharedFlow<CoordinateDto> = MutableSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val point: LiveData<ResourceDto<Point>> = location
        .flatMapLatest { coordinate ->
            repository.getPoint(coordinate.latitude, coordinate.longitude).map {
                when (it) {
                    is ResourceDto.Loading -> ResourceDto.Loading
                    is ResourceDto.Error -> ResourceDto.Error(it.exception)
                    is ResourceDto.Success -> ResourceDto.Success(it.data.mapDomain())
                }
            }
        }
        .asLiveData()

    suspend fun onLocation(latitude: Float, longitude: Float) {
        val coordinate = CoordinateDto(latitude, longitude)
        location.emit(coordinate)
    }

    fun isLoading(): LiveData<Boolean> {
        return point.map { it is ResourceDto.Loading }
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
            val error = resource as? ResourceDto.Error ?: return@map NoOp()
            val message = error.exception.message ?: return@map NoOp()
            Consumable(message)
        }
    }
}
