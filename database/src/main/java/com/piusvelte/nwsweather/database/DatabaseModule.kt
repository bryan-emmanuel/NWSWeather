package com.piusvelte.nwsweather.database

import android.content.Context
import androidx.room.Room
import com.piusvelte.nwsweather.database.dao.PointDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): NwsDatabase {
        return Room.databaseBuilder(
            context,
            NwsDatabase::class.java,
            "nws-database",
        ).build()
    }

    @Provides
    @Singleton
    fun providesPointDao(db: NwsDatabase): PointDao {
        return db.pointDao()
    }
}
