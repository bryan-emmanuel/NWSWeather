package com.piusvelte.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piusvelte.data.local.dao.PointDao
import com.piusvelte.data.local.entity.Point

@Database(
    entities = [Point::class],
    version = 1,
)
abstract class NwsDatabase : RoomDatabase() {
    abstract fun pointDao(): PointDao
}
