package com.piusvelte.nwsweather.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.piusvelte.nwsweather.api.common.adapter.CardinalDirectionAdapter
import com.piusvelte.nwsweather.api.common.adapter.CoordinateAdapter
import com.piusvelte.nwsweather.api.common.adapter.TemperatureUnitAdapter
import com.piusvelte.nwsweather.api.common.model.NwsCardinalDirection
import com.piusvelte.nwsweather.api.common.model.NwsCoordinate
import com.piusvelte.nwsweather.api.common.model.NwsTemperatureUnit
import com.piusvelte.nwsweather.api.gridpoints.NwsGridPointsService
import com.piusvelte.nwsweather.api.points.NwsPointsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NwsApiModule {
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(NwsCardinalDirection::class.java, CardinalDirectionAdapter())
            .registerTypeAdapter(NwsCoordinate::class.java, CoordinateAdapter())
            .registerTypeAdapter(NwsTemperatureUnit::class.java, TemperatureUnitAdapter())
            .create()
    }

    @Provides
    fun providesRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.weather.gov/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun providesPointsService(retrofit: Retrofit): NwsPointsService {
        return retrofit.create(NwsPointsService::class.java)
    }

    @Provides
    fun providesGridPointsService(retrofit: Retrofit): NwsGridPointsService {
        return retrofit.create(NwsGridPointsService::class.java)
    }
}
