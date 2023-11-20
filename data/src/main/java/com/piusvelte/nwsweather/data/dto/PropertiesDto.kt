package com.piusvelte.nwsweather.data.dto

data class PropertiesDto(
    val id: String,
    val type: String,
    val cwa: String,
    val forecastOffice: String,
    val gridId: String,
    val gridX: Int,
    val gridY: Int,
    val forecast: String,
    val forecastHourly: String,
    val forecastGridData: String,
    val observationStations: String,
    val relativeLocation: RelativeLocationDto,
    val forecastZone: String,
    val county: String,
    val fireWeatherZone: String,
    val timeZone: String,
    val radarStation: String,
)
