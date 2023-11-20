package com.piusvelte.nwsweather.api.model

import com.google.gson.annotations.SerializedName
import com.piusvelte.nwsweather.api.model.NwsGeometryPoint

data class NwsRelativeLocation(
    @SerializedName("type") val type: String,
    @SerializedName("geometry") val geometry: NwsGeometryPoint,
)
