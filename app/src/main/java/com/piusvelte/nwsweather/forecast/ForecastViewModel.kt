package com.piusvelte.nwsweather.forecast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piusvelte.nwsweather.domain.gridpoint.ForecastState
import com.piusvelte.nwsweather.domain.gridpoint.GetForecastUseCase
import com.piusvelte.nwsweather.domain.model.GeoLocation
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
internal class ForecastViewModel @Inject constructor(
    private val getLocation: GetLocationUseCase,
    private val getForecast: GetForecastUseCase,
    private val handle: SavedStateHandle,
) : ViewModel() {

    val location = handle.getStateFlow(LOCATION, INITIAL_LOCATION.mapState())

    init {
        viewModelScope.launch {
            getLocation()
                .collect {
                    handle[LOCATION] = it.mapDomain().mapState()
                }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<ForecastUiState> = location
        .flatMapLatest { getForecast(it.mapLocation()) }
        .map {
            val periods = (it as? ForecastState.Success)?.periods ?: emptyList()
            ForecastUiState(
                isLoading = it is ForecastState.Loading,
                periods = periods,
                error = (it as? ForecastState.Failure)?.message?.mapConsumable() ?: NoOp,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            initialValue = ForecastUiState(),
        )

    private companion object {
        private val INITIAL_LOCATION = GeoLocation(
            39.7456F,
            -97.0892F
        )

        private const val LOCATION = "state:location"
    }
}
