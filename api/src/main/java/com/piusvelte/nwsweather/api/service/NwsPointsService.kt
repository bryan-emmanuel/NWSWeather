package com.piusvelte.nwsweather.api.service

import com.piusvelte.nwsweather.api.model.NwsPoint
import retrofit2.http.GET
import retrofit2.http.Path

interface NwsPointsService {

    /** Called to retrieve the exact grid endpoint by coordinates, eg. 39.7456,-97.0892 */
    @GET("points/{latitude},{longitude}")
    suspend fun points(
        @Path("latitude") latitude: Float,
        @Path("longitude") longitude: Float,
    ): NwsPoint

}
