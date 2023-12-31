package com.piusvelte.nwsweather.data.repository

import com.piusvelte.nwsweather.data.dto.ForecastDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GridPointsRepository @Inject constructor(
    private val forecasts: LocalForecastUseCase,
    private val refresher: RefreshForecastUseCase,
    private val dispatcher: CoroutineDispatcher,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getForecast(office: String, gridX: Int, gridY: Int): Flow<ResourceDto<ForecastDto>> {
        val params = ForecastRefreshParams(office, gridX, gridY)
        val refresh = MutableSharedFlow<ForecastRefreshParams>()

        return flowOf(
            flowOf(ResourceDto.Loading),
            merge(
                forecasts(params, refresh),
                refresher(refresh),
            ),
        )
            .flattenConcat()
            .flowOn(dispatcher)
    }
}
