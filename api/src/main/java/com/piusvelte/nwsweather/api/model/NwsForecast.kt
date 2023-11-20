package com.piusvelte.nwsweather.api.model

import com.google.gson.annotations.SerializedName

data class NwsForecast(
    @SerializedName("type") val type: String,
    @SerializedName("geometry") val geometry: NwsGeometryPolygon,
    @SerializedName("properties") val properties: NwsForecastProperties,
)
