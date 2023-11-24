package com.piusvelte.nwsweather.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.piusvelte.nwsweather.api.adapter.CardinalDirectionAdapter
import com.piusvelte.nwsweather.api.adapter.CoordinateAdapter
import com.piusvelte.nwsweather.api.adapter.TemperatureUnitAdapter
import com.piusvelte.nwsweather.api.model.NwsCardinalDirection
import com.piusvelte.nwsweather.api.model.NwsCoordinate
import com.piusvelte.nwsweather.api.model.NwsTemperatureUnit
import com.piusvelte.nwsweather.api.service.NwsGridPointsService
import com.piusvelte.nwsweather.api.service.NwsPointsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NwsApiModule {

    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(NwsCardinalDirection::class.java, CardinalDirectionAdapter())
            .registerTypeAdapter(NwsCoordinate::class.java, CoordinateAdapter())
            .registerTypeAdapter(NwsTemperatureUnit::class.java, TemperatureUnitAdapter())
            .create()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.weather.gov/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun providesPointsService(retrofit: Retrofit): NwsPointsService {
        return retrofit.create(NwsPointsService::class.java)
    }

    @Provides
    @Singleton
    fun providesGridPointsService(retrofit: Retrofit): NwsGridPointsService {
        return retrofit.create(NwsGridPointsService::class.java)
    }
}
