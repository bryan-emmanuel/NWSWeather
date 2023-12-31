package com.piusvelte.nwsweather.data.repository

import com.piusvelte.nwsweather.api.service.NwsGridPointsService
import com.piusvelte.nwsweather.data.dto.ForecastDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.mapper.mapEntity
import com.piusvelte.nwsweather.database.dao.ForecastDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RefreshForecastUseCase @Inject constructor(
    private val forecastDao: ForecastDao,
    private val service: NwsGridPointsService,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(refresh: MutableSharedFlow<ForecastRefreshParams>): Flow<ResourceDto<ForecastDto>> =
        refresh
            .distinctUntilChanged()
            .flatMapLatest {
                try {
                    val forecast = service.forecast(it.office, it.gridX, it.gridY)
                    val entity = forecast.mapEntity()
                    forecastDao.insert(entity)
                    emptyFlow()
                } catch (e: Exception) {
                    flowOf(ResourceDto.Error(e))
                }
            }
}