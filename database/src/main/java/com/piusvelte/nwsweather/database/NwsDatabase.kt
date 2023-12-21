package com.piusvelte.nwsweather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piusvelte.nwsweather.database.dao.ForecastDao
import com.piusvelte.nwsweather.database.dao.PointDao
import com.piusvelte.nwsweather.database.entity.CoordinateContainerEntity
import com.piusvelte.nwsweather.database.entity.CoordinateEntity
import com.piusvelte.nwsweather.database.entity.ForecastEntity
import com.piusvelte.nwsweather.database.entity.ForecastPeriodEntity
import com.piusvelte.nwsweather.database.entity.PointEntity

@Database(
    entities = [
        CoordinateContainerEntity::class,
        CoordinateEntity::class,
        ForecastEntity::class,
        ForecastPeriodEntity::class,
        PointEntity::class,
    ],
    version = 3,
    exportSchema = false,
)
abstract class NwsDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    abstract fun pointDao(): PointDao
}
