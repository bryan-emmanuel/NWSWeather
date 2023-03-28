package com.piusvelte.nwsweather.api.common.model

import com.google.gson.annotations.SerializedName

data class NwsGeometryPoint(
    @SerializedName("type") val type: String,
    @SerializedName("coordinates") val coordinates: NwsCoordinate,
)
