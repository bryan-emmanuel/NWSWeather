package com.piusvelte.nwsweather.data.repository

data class ForecastRefreshParams(
    val office: String,
    val gridX: Int,
    val gridY: Int,
)
