package com.piusvelte.nwsweather.api.gridpoints.model

import com.google.gson.annotations.SerializedName
import com.piusvelte.nwsweather.api.common.model.NwsGeometryPolygon

data class NwsForecast(
    @SerializedName("type") val type: String,
    @SerializedName("geometry") val geometry: NwsGeometryPolygon,
    @SerializedName("properties") val properties: NwsForecastProperties,
)
