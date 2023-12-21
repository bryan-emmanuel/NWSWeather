package com.piusvelte.nwsweather.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coordinates_containers")
data class CoordinateContainerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("forecast_id") val forecastId: Long,
)
