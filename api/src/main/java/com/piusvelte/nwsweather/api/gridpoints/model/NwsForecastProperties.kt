package com.piusvelte.nwsweather.api.gridpoints.model

import com.google.gson.annotations.SerializedName
import com.piusvelte.nwsweather.api.common.model.NwsUnitValue

data class NwsForecastProperties(
    @SerializedName("updated") val updated: String,
    @SerializedName("units") val units: String,
    @SerializedName("forecastGenerator") val forecastGenerator: String,
    @SerializedName("generatedAt") val generatedAt: String,
    @SerializedName("updateTime") val updateTime: String,
    @SerializedName("validTimes") val validTimes: String,
    @SerializedName("elevation") val elevation: NwsUnitValue,
    @SerializedName("periods") val periods: List<NwsForecastPeriod>,
)
