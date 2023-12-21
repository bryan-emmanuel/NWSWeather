package com.piusvelte.nwsweather.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coordinates")
data class CoordinateEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("coordinate_id") val coordinateId: Long,
    @ColumnInfo("latitude") val latitude: Float,
    @ColumnInfo("longitude") val longitude: Float,
)
