package com.piusvelte.nwsweather.api.model

import com.google.gson.annotations.SerializedName

data class NwsUnitValue(
    @SerializedName("unitCode") val unitCode: String,
    @SerializedName("value") val value: Float,
)
