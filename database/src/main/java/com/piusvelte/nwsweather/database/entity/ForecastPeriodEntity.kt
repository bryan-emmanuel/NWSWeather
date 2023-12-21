package com.piusvelte.nwsweather.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast_periods")
data class ForecastPeriodEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("forecast_id") val forecastId: Long,
    @ColumnInfo("number") val number: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("start_time") val startTime: String,
    @ColumnInfo("end_time") val endTime: String,
    @ColumnInfo("is_daytime") val isDaytime: Boolean,
    @ColumnInfo("temperature") val temperature: Float,
    @ColumnInfo("temperature_unit") val temperatureUnit: String,
    @ColumnInfo("temperature_trend") val temperatureTrend: String?,
    @ColumnInfo("wind_speed") val windSpeed: String,
    @ColumnInfo("wind_direction") val windDirection: String,
    @ColumnInfo("icon") val icon: String,
    @ColumnInfo("short_forecast") val shortForecast: String,
    @ColumnInfo("detailed_forecast") val detailedForecast: String,
)
