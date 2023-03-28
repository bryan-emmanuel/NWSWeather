package com.piusvelte.nwsweather.forecast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piusvelte.domain.gridpoint.ForecastUseCase
import com.piusvelte.nwsweather.location.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ForecastViewModel @Inject constructor(
    private val location: LocationUseCase,
    private val useCase: ForecastUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    init {
        viewModelScope.launch {
            location.location()
                .collect {
                    useCase.onLocation(it.latitude.toFloat(), it.longitude.toFloat())
                }
        }
    }

    val isLoading = useCase.isLoading()

    val forecast = useCase.forecast()
}
