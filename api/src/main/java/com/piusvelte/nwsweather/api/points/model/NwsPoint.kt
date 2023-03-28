package com.piusvelte.nwsweather.api.points.model

import com.google.gson.annotations.SerializedName
import com.piusvelte.nwsweather.api.common.model.NwsGeometryPoint

data class NwsPoint(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("geometry") val geometry: NwsGeometryPoint,
    @SerializedName("properties") val properties: NwsPointProperties,
)
