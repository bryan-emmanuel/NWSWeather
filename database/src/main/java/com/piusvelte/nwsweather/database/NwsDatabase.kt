package com.piusvelte.nwsweather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piusvelte.nwsweather.database.dao.PointDao
import com.piusvelte.nwsweather.database.entity.PointEntity

@Database(
    entities = [PointEntity::class],
    version = 2,
    exportSchema = false,
)
abstract class NwsDatabase : RoomDatabase() {
    abstract fun pointDao(): PointDao
}
