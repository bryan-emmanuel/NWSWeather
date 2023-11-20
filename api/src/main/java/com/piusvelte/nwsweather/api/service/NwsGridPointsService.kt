package com.piusvelte.nwsweather.api.service

import com.piusvelte.nwsweather.api.model.NwsForecast
import retrofit2.http.GET
import retrofit2.http.Path

interface NwsGridPointsService {

    @GET("gridpoints/{office}/{gridX},{gridY}/forecast")
    suspend fun forecast(
        @Path("office") office: String,
        @Path("gridX") gridX: Int,
        @Path("gridY") gridY: Int,
    ): NwsForecast
}
