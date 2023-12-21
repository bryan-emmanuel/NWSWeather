package com.piusvelte.nwsweather.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecasts")
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "geometry_type") val geometryType: String,
    @ColumnInfo(name = "updated") val updated: String,
    @ColumnInfo(name = "units") val units: String,
    @ColumnInfo(name = "forecast_generator") val forecastGenerator: String,
    @ColumnInfo(name = "generated_at") val generatedAt: String,
    @ColumnInfo(name = "update_time") val updateTime: String,
    @ColumnInfo(name = "valid_times") val validTimes: String,
    @ColumnInfo(name = "elevation_unit_code") val elevationUnitCode: String,
    @ColumnInfo(name = "elevation_value") val elevationValue: Float,
)