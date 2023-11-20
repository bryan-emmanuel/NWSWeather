package com.piusvelte.nwsweather.api.model

import com.google.gson.annotations.SerializedName

data class NwsPoint(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("geometry") val geometry: NwsGeometryPoint,
    @SerializedName("properties") val properties: NwsPointProperties,
)
