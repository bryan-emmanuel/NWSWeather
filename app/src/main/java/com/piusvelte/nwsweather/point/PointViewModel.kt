package com.piusvelte.nwsweather.point

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piusvelte.nwsweather.domain.model.GeoLocation
import com.piusvelte.nwsweather.domain.point.GetPointUseCase
import com.piusvelte.nwsweather.domain.point.PointState
import com.piusvelte.nwsweather.location.GetLocationUseCase
import com.piusvelte.nwsweather.mapper.mapDomain
import com.piusvelte.nwsweather.mapper.mapLocation
import com.piusvelte.nwsweather.mapper.mapState
import com.piusvelte.nwsweather.ui.NoOp
import com.piusvelte.nwsweather.ui.mapConsumable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
internal class PointViewModel @Inject constructor(
    private val getPoint: GetPointUseCase,
    private val getLocation: GetLocationUseCase,
    private val handle: SavedStateHandle,
) : ViewModel() {

    val location = handle.getStateFlow(LOCATION, INITIAL_LOCATION.mapState())

    init {
        viewModelScope.launch {
            getLocation()
                .collect {
                    onLocation(it.mapDomain())
                }
        }
    }

    private fun onLocation(location: GeoLocation) {
        handle[LOCATION] = location.mapState()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<PointUiState> = location
        .flatMapLatest { getPoint(it.mapLocation()) }
        .map {
            val point = (it as? PointState.Success)?.point
            PointUiState(
                isLoading = it is PointState.Loading,
                pointId = point?.id ?: "",
                pointGridId = point?.properties?.gridId ?: "",
                pointGridX = point?.properties?.gridX ?: 0,
                pointGridY = point?.properties?.gridY ?: 0,
                error = (it as? PointState.Failure)?.message?.mapConsumable() ?: NoOp,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            initialValue = PointUiState(),
        )

    private companion object {
        private val INITIAL_LOCATION = GeoLocation(
            39.7456F,
            -97.0892F
        )

        private const val LOCATION = "state:location"
    }
}
