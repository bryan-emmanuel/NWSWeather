package com.piusvelte.nwsweather.point

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piusvelte.domain.point.PointUseCase
import com.piusvelte.nwsweather.location.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PointViewModel @Inject constructor(
    private val pointUseCase: PointUseCase,
    private val lastLocation: LocationUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    init {
        viewModelScope.launch {
            lastLocation.location()
                .collect { pointUseCase.onLocation(it.latitude.toFloat(), it.longitude.toFloat()) }
        }
    }

    fun onLocation(latitude: Float, longitude: Float) {
        viewModelScope.launch {
            pointUseCase.onLocation(latitude, longitude)
        }
    }

    val isLoading = pointUseCase.isLoading()

    val pointId = pointUseCase.pointId()

    val pointGridId = pointUseCase.pointGridId()

    val pointGridX = pointUseCase.pointGridX()

    val pointGridY = pointUseCase.pointGridY()

    val error = pointUseCase.pointError()

    companion object {
        private const val DEMO_LATITUDE = 39.7456F
        private const val DEMO_LONGITUDE = -97.0892F
    }
}
