package com.piusvelte.nwsweather.data.nws

import com.piusvelte.nwsweather.data.gridpoints.GridPointsRepository
import com.piusvelte.nwsweather.data.nws.converter.LocalConverter
import com.piusvelte.nwsweather.data.nws.converter.NwsLocalConverter
import com.piusvelte.nwsweather.data.nws.converter.NwsRemoteConverter
import com.piusvelte.nwsweather.data.nws.converter.RemoteConverter
import com.piusvelte.nwsweather.data.nws.gridpoints.NwsGridPointsRepository
import com.piusvelte.nwsweather.data.nws.points.NwsPointsRepository
import com.piusvelte.nwsweather.data.points.PointsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NwsDataBindersModule {
    @Binds
    fun bindsLocalConverter(converter: NwsLocalConverter): LocalConverter

    @Binds
    fun bindsRemoteConverter(converter: NwsRemoteConverter): RemoteConverter

    @Binds
    fun bindsGridPointsRepository(repository: NwsGridPointsRepository): GridPointsRepository

    @Binds
    fun bindsPointsRepository(repository: NwsPointsRepository): PointsRepository
}
