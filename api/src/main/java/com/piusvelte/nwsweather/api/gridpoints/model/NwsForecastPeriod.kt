package com.piusvelte.nwsweather.api.gridpoints.model

import com.google.gson.annotations.SerializedName
import com.piusvelte.nwsweather.api.common.model.NwsCardinalDirection
import com.piusvelte.nwsweather.api.common.model.NwsTemperatureUnit

data class NwsForecastPeriod(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("isDaytime") val isDaytime: Boolean,
    @SerializedName("temperature") val temperature: Float,
    @SerializedName("temperatureUnit") val temperatureUnit: NwsTemperatureUnit,
    @SerializedName("temperatureTrend") val temperatureTrend: String?,
    @SerializedName("windSpeed") val windSpeed: String,
    @SerializedName("windDirection") val windDirection: NwsCardinalDirection,
    @SerializedName("icon") val icon: String,
    @SerializedName("shortForecast") val shortForecast: String,
    @SerializedName("detailedForecast") val detailedForecast: String,
)
