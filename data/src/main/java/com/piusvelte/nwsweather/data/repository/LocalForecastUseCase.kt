package com.piusvelte.nwsweather.data.repository

import com.piusvelte.nwsweather.data.dto.ForecastDto
import com.piusvelte.nwsweather.data.dto.ResourceDto
import com.piusvelte.nwsweather.data.mapper.mapDto
import com.piusvelte.nwsweather.database.dao.ForecastDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalForecastUseCase @Inject constructor(
    private val forecastDao: ForecastDao,
) {

    operator fun invoke(
        params: ForecastRefreshParams,
        refresh: MutableSharedFlow<ForecastRefreshParams>
    ): Flow<ResourceDto<ForecastDto>> {
        return forecastDao.get(params.office, params.gridX, params.gridY)
            .map {
                if (it != null) {
                    ResourceDto.Success(it.mapDto())
                } else {
                    refresh.emit(params)
                    null
                }
            }.filterNotNull()
    }
}