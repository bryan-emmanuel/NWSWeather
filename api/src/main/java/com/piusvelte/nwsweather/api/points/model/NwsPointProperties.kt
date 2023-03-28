package com.piusvelte.nwsweather.api.points.model

import com.google.gson.annotations.SerializedName

data class NwsPointProperties(
    @SerializedName("@id") val id: String,
    @SerializedName("@type") val type: String,
    @SerializedName("cwa") val cwa: String,
    @SerializedName("forecastOffice") val forecastOffice: String,
    @SerializedName("gridId") val gridId: String,
    @SerializedName("gridX") val gridX: Int,
    @SerializedName("gridY") val gridY: Int,
    @SerializedName("forecast") val forecast: String,
    @SerializedName("forecastHourly") val forecastHourly: String,
    @SerializedName("forecastGridData") val forecastGridData: String,
    @SerializedName("observationStations") val observationStations: String,
    @SerializedName("relativeLocation") val relativeLocation: NwsRelativeLocation,
    @SerializedName("forecastZone") val forecastZone: String,
    @SerializedName("county") val county: String,
    @SerializedName("fireWeatherZone") val fireWeatherZone: String,
    @SerializedName("timeZone") val timeZone: String,
    @SerializedName("radarStation") val radarStation: String,
)
